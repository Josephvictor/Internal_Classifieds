package com.amazon.Classifieds.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.amazon.Classifieds.ClassifiedsSessionHandler;
import com.amazon.Classifieds.db.UserDAO;
import com.amazon.Classifieds.models.Users;

public class AuthenticationService {
	
	Scanner scanner = new Scanner(System.in);
	UserDAO userDAO = new UserDAO();
	
	private static AuthenticationService authenticationService = new AuthenticationService();
	
	public static AuthenticationService getInstance() {
		return authenticationService;
	}
	
	private AuthenticationService() {
		
	}
	
	
	public Users login(Users object) {
		
		try {
			
			System.out.println("Enter the Email ID :");
			object.email = scanner.nextLine();
			if(object.email.isBlank() || !object.email.contains("@") || !object.email.endsWith(".com")) {
				return null;
			}
			
			System.out.println("Enter the Password :");
			object.password = scanner.nextLine();
			if(object.password.length() < 8) {
				return null;
			}
			
			if(object.type == 2) {
				object.password = PasswordHashingService.hashPassword(object.password);
			}
			
			String sql = "SELECT * FROM Users WHERE email = '"+object.email+"' AND password = '"+object.password+"'";
			List<Users> objects = userDAO.retrieve(sql);
			
			if(objects.size() > 0) {
				
				object.id = objects.get(0).id;
				object.name = objects.get(0).name;
				object.phone = objects.get(0).phone;
				object.address = objects.get(0).address;
				object.department = objects.get(0).department;
				object.type = objects.get(0).type;
				object.isActive = objects.get(0).isActive;
				object.lastUpdatedOn = objects.get(0).lastUpdatedOn;
				
				return object;
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
		
		return null;
	}
	
	
	//FOR USER
	public Users registerNewAcc() {
		
		Users object = new Users();
		
		if(object.setDetails()){
		
			object.password = PasswordHashingService.hashPassword(object.password);
			
			int result = userDAO.insert(object);
			
			if(result > 0) {
				System.out.println("User Account created successfully");
				return object;
			}else {
				System.err.println("User Account Creation Failed..");
				return null;
			}
		}else {
			System.err.println("User Account Creation Failed..");
			return null;
		}
	}
	
	//For ADMIN 
	public void activateUser() {
		
		String sql = "SELECT * FROM Users WHERE type = 2 AND isActive = 0";
		List<Users> userObjects = userDAO.retrieve(sql);
		
		if(userObjects.size() > 0) {
			for(Users object : userObjects) {
				object.id = userObjects.get(0).id;
				object.getDetails();
				System.out.println();
				System.out.println("1. Approve User (Activate)");
				System.out.println("2. Deactivate Existing User");
				object.isActive = Integer.parseInt(scanner.nextLine());
				
				int result = userDAO.activateUser(object);
				if(result > 0) {
					System.out.println("User "+object.name+" isActive status is "+object.isActive);
				}else {
					System.err.println("isActive updation failed");
				}
			}
		}
	}
	
	//FOR USER TO UPDATE HIS PROFILE
	public boolean updateProfile(Users user) {
		
		try {
			if(ClassifiedsSessionHandler.user.type == 2)
			{
				System.out.println("***********Press Enter to SKIP any field***********");
				System.out.println();
				System.out.println("Update name :");
				String name = scanner.nextLine();
				if(!name.isEmpty()) {
					user.name = name;
				}
				
				System.out.println("Update Phone Number :");
				String phone = scanner.nextLine();
				if(phone.length() >= 10) {
					user.phone = phone;
				}
				
				System.out.println("Update Address :");
				String address = scanner.nextLine();
				if(!address.isEmpty()) {
					user.address = address;
				}
				
				System.out.println("Update Department :");
				String department = scanner.nextLine();
				if(!department.isEmpty()) {
						user.department = department;
				}
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
			return false;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		user.lastUpdatedOn = formatter.format(date);
		
		int result = userDAO.update(user);
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void updatePassword(Users object) {
		
		try {
			System.out.println("Update Password :");
			String password = scanner.nextLine();
			if(!password.isEmpty() || password.length() >= 8) {
				object.password = password;
			}
			
			object.password = PasswordHashingService.hashPassword(object.password);

			int result = userDAO.update(object);
			if(result > 0) {
				System.out.println("Password updated successfully");
			}else {
				System.err.println("Password updation failed");
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
	}
}
