package com.amazon.Classifieds.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.amazon.Classifieds.ClassifiedsSessionHandler;
import com.amazon.Classifieds.Payments.PaymentFactory;
import com.amazon.Classifieds.db.ClassifiedsDAO;
import com.amazon.Classifieds.db.OrdersDAO;
import com.amazon.Classifieds.models.Classifieds;
import com.amazon.Classifieds.models.Orders;

public class OrdersService {
	
	Scanner scanner = new Scanner(System.in);
	ClassifiedsDAO classifiedsDAO = new ClassifiedsDAO();
	OrdersDAO ordersDAO = new OrdersDAO();

	private static OrdersService ordersService = new OrdersService();
	
	private OrdersService() {
		
	}
	
	public static OrdersService getInstance() {
		return ordersService;
	}
	
	private Classifieds viewClassifiedsByID(int id) {
		
		Classifieds object = new Classifieds();
		
		List<Classifieds> objects = null;
		
		String sql = "SELECT * FROM Classifieds WHERE id = "+id+"";
		objects = classifiedsDAO.retrieve(sql);
		
		if(objects.size() > 0) {
			object = objects.get(0);
			return object;
		}else {
			System.err.println("No Classifieds with the Id "+id);
			return null;
		}
	}
	
	public void buyClassified(int id) {
		
		try {
			List<Orders> objects = null;
			
			String sql = "SELECT * FROM Orders WHERE fromUserId = "+ClassifiedsSessionHandler.user.id+" AND classifiedId = "+id+"";
			objects = ordersDAO.retrieve(sql);
			
			
			if(objects.size() == 0)
			{
				Classifieds object = viewClassifiedsByID(id);
				
				if(object != null) {
					object.getDetails();
					
					Orders order = new Orders();
					order.classifiedId = object.id;
					order.classifiedName = object.productName;
					order.toUserId = object.uid;
					order.fromUserId = ClassifiedsSessionHandler.user.id;
					order.status = 0;
					
					System.out.println("[New] Enter your price :");
					order.proposedPrice = Integer.parseInt(scanner.nextLine());
					
					int result = ordersDAO.insert(order);
					if(result > 0) {
						System.out.println("Order placed Successfully ");
					}else {
						System.err.println("Could not place your order ");
					}
				}
			}else {
				
				for(Orders order : objects) {
					
					order.getDetails();
					System.out.println("[Update] Enter new improved bid Price");
					order.proposedPrice = Integer.parseInt(scanner.nextLine());
					order.status = 0;
					
					int result = ordersDAO.update(order);
					if(result > 0) {
						System.out.println("Order updated Successfully "+order.id);
					}else {
						System.err.println("Could not update your order "+order.id);
					}
				}
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
	}
	
	public void manageBuyAndSell() {
		
		try {
			System.out.println("1. My Buy Orders");
			System.out.println("2. My Sell Orders");
			System.out.println("Enter your choice");
			int choice = Integer.parseInt(scanner.nextLine());
			
			if(choice == 1) {
				
				List<Orders> objects = null;
				ArrayList<Orders> approvedObjects = new ArrayList<Orders>();
				
				String sql = "SELECT * FROM Orders WHERE fromUserId = "+ClassifiedsSessionHandler.user.id+"";
				objects = ordersDAO.retrieve(sql);
				
				if(objects.size() > 0) {
					
					for(Orders order : objects) {
						order.getDetails();
						
						if(order.status == 1) {
							approvedObjects.add(order);
						}
					}
				}else {
					System.err.println("No Buy Orders made by You!!");
				}
				
				if(approvedObjects.size() > 0) {
					
					System.out.println("Do you want to proceed to Pay for the Approved classifieds? \tPRESS 1.Yes 0.No");
					int approveChoice = Integer.parseInt(scanner.nextLine());
					if(approveChoice == 1) {
						
						for(Orders object : approvedObjects) {
							
							object.getDetails();
							System.out.println("Press 1 to proceed to pay "+object.proposedPrice+"\t (or) 0 to cancel ");
							int payChoice = Integer.parseInt(scanner.nextLine());
							
							if(payChoice == 1) {
								System.out.println("1. Google Pay");
								System.out.println("2. PayTM");
								System.out.println("3. AmazonPay");
								System.out.println("Select any UPI payment option");
								int paymentChoice = Integer.parseInt(scanner.nextLine());
								
								boolean paymentStatus = PaymentFactory.paymentService(paymentChoice, object.proposedPrice);
								
								if(paymentStatus) {
									object.status = 3;
									Classifieds classified = new Classifieds();
									classified.status = 3;
									classified.id = object.classifiedId;
									int classifiedsResult = classifiedsDAO.update(classified);
									int orderResult = ordersDAO.update(object);
									if(orderResult > 0 && classifiedsResult > 0) {
										System.out.println("Order Successfully Completed");
										System.out.println("Please collect the product from the seller");
									}else {
										System.err.println("Order could not be completed");
									}
								}
							}
						}
					}else {
						System.out.println("Going back");
					}
				}
				
			}else if(choice == 2) {
				
				List<Orders> objects = null;
				
				String sql = "SELECT * FROM Orders WHERE toUserId = "+ClassifiedsSessionHandler.user.id+" AND status IN(0,3)";
				objects = ordersDAO.retrieve(sql);
				
				//for(Orders order : objects) {
				//	order.getDetails();
				//}
				
				if(objects.size() > 0) {
					for(Orders order : objects) {
						
						order.getDetails();

						if(order.status == 0) {
							//Orders object = new Orders();
							
							//System.out.println("Enter the Order Id :");
							//order.id = Integer.parseInt(scanner.nextLine());
							
							System.out.println("1. Approve the offer to Buy");
							System.out.println("2. Reject the offer to Buy");
							order.status = Integer.parseInt(scanner.nextLine());
							
							order.lastUpdatedOn = "CURRENT_TIMESTAMP";
							
							int result = ordersDAO.update(order);
							
							if(result > 0) {
								System.out.println("Order updated with status "+order.status);
							}else {
								System.err.println("Status updation failed.");
							}
						}
					}
				}else {
					System.err.println("Zero Sell Orders made by You!!");
				}

			}else {
				System.err.println("Wrong choice..going back");
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
	}
}
