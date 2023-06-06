package com.amazon.Classifieds;

import com.amazon.Classifieds.Controller.AuthenticationService;
import com.amazon.Classifieds.Controller.ClassifiedsService;
import com.amazon.Classifieds.Controller.OrdersService;
import com.amazon.Classifieds.models.Users;

public class UserMenu extends Menu {
	
	AuthenticationService authenticationService = AuthenticationService.getInstance();
	ClassifiedsService classifiedsService = ClassifiedsService.getInstance();
	OrdersService ordersService = OrdersService.getInstance();
	
	private static UserMenu menu = new UserMenu();
	
	private UserMenu() {
		
	}
	
	public static UserMenu getInstance() {
		return menu;
	}
	
	public void showMenu() {
		
				Users user = new Users();
	
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("0. Quit");
				System.out.println("Please select to Proceed:");
				int choice = 0;
				
				try {
					choice = Integer.parseInt(scanner.nextLine());
				}catch(Exception e) {
					System.err.println("Something went wrong "+e);
				}
				
				//User type is 2 in the users table
				user.type = 2;
				
				if(choice == 1) {
					user = authenticationService.registerNewAcc();
					System.out.println();
					if(user != null) {
						System.out.println("Under Activation Process. We'll notify you after the activation process.");
					}
					System.out.println();
				}else if(choice == 2) {
					user = authenticationService.login(user);
				}else if(choice == 0){
					System.out.println("Thank You for Using our service. Please come back again!!");
				}else {
					System.err.println("Invalid Choice");
				}
				
				if(user != null && choice == 2) {
					if(user.type == 2 && user.isActive == 1) {
						
						//Holds the user which can be used anywhere in the application
						ClassifiedsSessionHandler.user = user;
						
						while(true) {
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("1. My Profile");
							System.out.println("2. Post a Classified");
							System.out.println("3. View My Classifieds");
							System.out.println("4. Manage My Buy and Sell Orders");
							System.out.println("5. Browse and Buy Classifieds");
							System.out.println("0. Quit the User App");
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("Please select to proceed");
							
							int userChoice = 101;
							try {
								userChoice = Integer.parseInt(scanner.nextLine());
							}catch(Exception e) {
								System.err.println("Something went wrong "+e);
							}
						
							if(userChoice == 0) {
								System.out.println("Thanks for using the App.. Please visit again");
								break;
							}else if(userChoice == 1) {
								while(true) {
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									System.out.println("1. Show Profile");
									System.out.println("2. Update Profile Details");
									System.out.println("3. Reset Password");
									System.out.println("0. Go Back");
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									System.out.println("Please select to Proceed");
									
									int profileChoice = 0;
									try {
										profileChoice = Integer.parseInt(scanner.nextLine());
									}catch(Exception e) {
										System.err.println("Something went wrong "+e);
									}
									
									switch(profileChoice) {
										case 1:
											user.getDetails();
											break;
										case 2:
											boolean result = authenticationService.updateProfile(user);
											
											if(result) {
												System.out.println("User Profile Updated Successfully");
											}else {
												System.err.println("Profile Updation Failed..");
											}
											break;
										case 3:
											authenticationService.updatePassword(user);
											break;
										case 0:
											System.out.println("going back....");
											break;
										default:
											System.err.println("[UserMenu] [Profile] Invalid Option.. Returing back");
											break;
										}
									
									if(profileChoice == 0) {
										break;
									}
								}		
							}else if(userChoice == 2) {
								classifiedsService.createClassified();
							}else if(userChoice == 3) {
								classifiedsService.viewClassifiedsByUID(ClassifiedsSessionHandler.user.id);
							}else if(userChoice == 4) {
								ordersService.manageBuyAndSell();
							}else if(userChoice == 5) {
								
								int status = 1;
								if(!classifiedsService.viewClassifieds(status)) {
									System.out.println("Do you want to buy a classified? If yes, Enter the Classified Id or 0 to go back");
									int classifiedId = 0;
									try {
										classifiedId = Integer.parseInt(scanner.nextLine());
									}catch(Exception e) {
										System.err.println("Something went wrong "+e);
									}
									
									if(classifiedId > 0) {
										ordersService.buyClassified(classifiedId);
									}else{
										System.out.println("Going Back...");
									}
								}
							}else {
								System.err.println("[UserMenu] Invalid Choice.. Try again");
							}
						}
					}else if(user.isActive == 0){
						System.out.println("Verification Under process..!!");
					}else {
						System.err.println("[UserMenu] User Login Failed");
				}
			}
			if(user == null) {
				System.err.println("Invalid Login Credentials");
			}
		}
}
