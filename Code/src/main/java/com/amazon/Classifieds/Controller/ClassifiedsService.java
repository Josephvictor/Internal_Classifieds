package com.amazon.Classifieds.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.amazon.Classifieds.ClassifiedsSessionHandler;
import com.amazon.Classifieds.db.ClassifiedsDAO;
import com.amazon.Classifieds.models.Classifieds;

public class ClassifiedsService {
		
	Scanner scanner = new Scanner(System.in);
	ClassifiedsDAO classifiedsDAO = new ClassifiedsDAO();
	
	private static ClassifiedsService classifiedsService = new ClassifiedsService();
	
	private ClassifiedsService() {
		
	}
	
	public static ClassifiedsService getInstance() {
		return classifiedsService;
	}
	
	public void createClassified() {
		
		Classifieds object = new Classifieds();
		
		if(object.setDetails(false)) {
			
			object.uid = ClassifiedsSessionHandler.user.id;
			
			int result = classifiedsDAO.insert(object);
			if(result > 0) {
				System.out.println("Classified "+object.productName+" created successfully");
			}else {
				System.err.println("Failed to create classified "+object.productName);
			}
		}else {
			System.err.println("Failed to create classified ");
		}		
	}
	
	
	public void approveRejectClassifieds() {
		
		while(true) {
			
			System.out.println("Please enter the userId to view classifieds by an user");
			System.out.println("enter 0 to view all classifieds");
			int uid = 0;
			try {
				uid = Integer.parseInt(scanner.nextLine());
			}catch(Exception e) {
				System.err.println("Something went wrong "+e);
			}
			
			boolean isEmpty = false;
			if(uid == 0) {
				//Will return true if there are no classifieds that are pending
				isEmpty = viewClassifieds(0);
			}else{
					String sql = "SELECT * FROM Classifieds WHERE uid = "+uid+" AND status = 0";
					List<Classifieds> objects = null;
					objects = classifiedsDAO.retrieve(sql);
					
					if(objects.size() > 0) {
						for(Classifieds object : objects) {
							object.getDetails();
						}
					}else{
						System.err.println("No Classifieds found ");
						isEmpty = true;
				}
			}
					
			if(!isEmpty) {
				
				try {
					Classifieds object = new Classifieds();
					System.out.println();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Enter the Classified ID to proceed :");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					object.id = Integer.parseInt(scanner.nextLine());
					System.out.println();
					System.out.println("1. Approve");
					System.out.println("2. Reject");
					System.out.println("Please Enter the ");
					object.status = Integer.parseInt(scanner.nextLine());
					

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date date = new Date();
					object.lastUpdatedOn = formatter.format(date);
					
					if(object.status == 1 || object.status == 2) {
						int result = classifiedsDAO.update(object);
						
						if(result > 0) {
							System.out.println("Classified Status updated.");
						}else {
							System.err.println("Failed to Update.. Something went wrong");
						}
					}
				}catch(Exception e) {
					System.err.println("Something went wrong "+e);
				}
		}
			
			System.out.println();
			System.out.println("Press 0 to Continue or Any other key to Exit");
			
			int exitChoice = 1;
			try {
				exitChoice = Integer.parseInt(scanner.nextLine());
			}catch(Exception e) {
				System.err.println("Something went wrong "+e);
			}
			
			if(exitChoice != 0) {
				break;
			}
		}
	}
	
	public boolean viewClassifieds(int status) {
		
		List<Classifieds> objects = null;
		
		String sql = "SELECT * FROM Classifieds WHERE status = "+status+" AND uid <> "+ClassifiedsSessionHandler.user.id+"";
		objects = classifiedsDAO.retrieve(sql);
		
		if(objects.size() > 0) {
			for(Classifieds object : objects) {
				object.getDetails();
			}
			return false;
		}else {
			System.err.println("No Classifieds in the status "+status);
			return true;
		}
	}
	
	public void viewClassifiedsByUID(int uid) {
		
		List<Classifieds> objects = null;
		
		String sql = "SELECT * FROM Classifieds WHERE uid = "+uid+" AND status <> 3";
		objects = classifiedsDAO.retrieve(sql);
		
		if(objects.size() > 0) {
			for(Classifieds object : objects) {
				object.getDetails();
			}
		}else {
			System.err.println("No classifieds found posted by User Id :"+uid);
		}
	}
	

}
