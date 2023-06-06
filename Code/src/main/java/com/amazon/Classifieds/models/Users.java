package com.amazon.Classifieds.models;

import java.util.Scanner;

/*
  	CREATE TABLE Users(
 		id INT PRIMARY KEY IDENTITY(1,1),
 		name NVARCHAR(30) NOT NULL,
 		phone NVARCHAR(15) NOT NULL,
 		email NVARCHAR(50) NOT NULL UNIQUE,
 		password NVARCHAR(50) NOT NULL,
 		address NVARCHAR(50) NOT NULL,
 		department NVARCHAR(15) NOT NULL,
 		type INT NOT NULL,								--(1.Admin 2.User)
 		isActive INT NOT NULL,							--(0 De-active 1.Active)
 		lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
 */

public class Users {
	
	Scanner userScanner = new Scanner(System.in);
	
	 public int id;
	 public String name;
	 public String phone;
	 public String email;
	 public String password;
	 public String address;
	 public String department;
	 public int type;				// 1. Admin, 2.User
	 public int isActive;			// 0. De-active 1. Active
	 public String lastUpdatedOn;
	 
	 public Users() {	 
	 
	 } 
	 
	 public Users(int id, String name, String phone, String email, String password, String address, String department,
			int type, String lastUpdatedOn) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.department = department;
		this.type = type;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	 
	 public boolean setDetails() {
		 
		 try {
			 System.out.println("Enter the name :");
				name = userScanner.nextLine();
				if(name.isEmpty()) {
					System.err.println("Name is empty");
					return false;
				}
				
				System.out.println("Enter the Phone Number :");
				phone = userScanner.nextLine();
				if(phone.length() < 10) {
					System.err.println("Phone number is invalid");
					return false;
				}
				
				System.out.println("Enter the email :");
				email = userScanner.nextLine();
				if(email.isBlank() || !email.contains("@") || !email.endsWith(".com")) {
					System.err.println("Email is invalid");
					return false;
				}
				
				System.out.println("Enter the password (Length greater than or equal to 8):");
				password = userScanner.nextLine();
				if(password.length() < 8) {
					System.err.println("Password is invalid");
					return false;
				}
				
				System.out.println("Enter the address :");
				address = userScanner.nextLine();
				
				System.out.println("Enter the department :");
				department = userScanner.nextLine();
				
				type = 2;
				isActive = 0;
								
		 }catch(Exception e) {
			 System.err.println("[Users - Set Details] Something went wrong "+e);
			 return false;
		 }
		return true;	
	 }
	 
	 public void getDetails() {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Id:\t\t"+id);
			System.out.println("Name:\t\t"+name);
			System.out.println("Phone:\t\t"+phone);
			System.out.println("Email:\t\t"+email);
			System.out.println("Address:\t"+address);
			System.out.println("Department:\t"+department);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		}
	 
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", department=" + department + ", type=" + type + ", lastUpdatedOn="
				+ lastUpdatedOn + "]";
	}
	
}
