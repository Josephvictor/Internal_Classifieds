package com.amazon.Classifieds.models;

import java.util.Scanner;

import com.amazon.Classifieds.Controller.CategoryService;

/*
CREATE TABLE Classifieds(
 	id INT PRIMARY KEY IDENTITY(1,1),
 	categoryId INT NOT NULL,
 	uid INT NOT NULL,
 	status INT NOT NULL,						--(0 - pending, 1 - approved, 2 - rejected, 3 - sold)
 	headline NVARCHAR(100) NOT NULL,
 	productName NVARCHAR(50) NOT NULL,
 	brand NVARCHAR(25),
 	condition INT NOT NULL,						--(1- Brand New(Seal Packed), 2- Lightly Used, 3 - Moderately Used, 4 - Heavily Used, 5 - Damaged/Dented, 6 - Not Working)
 	description NVARCHAR(500) NOT NULL,
 	price INT NOT NULL,
 	isRecurrence INT NOT NULL,					--( 1.YES || 0.NO)
 	pictures NVARCHAR(50),
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMEsTAMP
	FOREIGN KEY (uid) REFERENCES Users(id) ON DELETE CASCADE,
 	FOREIGN KEY (categoryId) REFERENCES Category(id)
)
 */

public class Classifieds {
	
	CategoryService categoryService = CategoryService.getInstance();
	Scanner scanner = new Scanner(System.in);
	
	public int id;
	public int categoryId;
	public int uid;
	public int status;				// 0 - pending, 1 - approved, 2 - rejected, 3 - sold
	public String headline;
	public String productName;
	public String brand;
	public int condition;			// 1- Brand New(Seal Packed), 2- Lightly Used, 3 - Moderately Used, 4 - Heavily Used, 5 - Damaged/Dented, 6 - Not Working
	public String description;
	public int price;
	public int isRecurrence;		// 1.YES || 0.NO
	public String pictures;
	public String lastUpdatedOn;	//default creation datetimestamp
	
	public Classifieds() {	
	}
	
	
	
	public Classifieds(int id, int categoryId, int uid,int status, String headline, String productName, String brand,
			int condition, String description, int price, int isRecurrence,String pictures, String lastUpdatedOn) {
		this.id = id;
		this.categoryId = categoryId;
		this.uid = uid;
		this.status = status;
		this.headline = headline;
		this.productName = productName;
		this.brand = brand;
		this.condition = condition;
		this.description = description;
		this.price = price;
		this.isRecurrence = isRecurrence;
		this.pictures = pictures;
		this.lastUpdatedOn = lastUpdatedOn;
	}



	public boolean setDetails(boolean updateMode) {
		
		try {
			System.out.println("Enter the Category Id : ");
			categoryService.viewCategories();
			categoryId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter the Product Name : ");
			productName = scanner.nextLine();
			if(productName.isBlank() || productName.isEmpty()) {
				return false;
			}
			
			System.out.println("Enter the HeadLine :(More than 5 characters) ");
			headline = scanner.nextLine();
			if(headline.length() <= 5) {
				return false;
			}
			
			System.out.println("Enter the Brand : ");
			brand = scanner.nextLine();
			
			System.out.println("Give detailed Description : (More than 10 characters)");
			description = scanner.nextLine();
			if(description.length() < 10) {
				return false;
			}
			
			
			System.out.println("1.Brand New(Sealed) \n2.Lightly Used \n3.Moderately Used \n4.Heavily Used \n5.Damaged/Dented \n6.Not Working");
			System.out.println("Please select the condition of the product ");
			condition = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter the Price : ");
			price = Integer.parseInt(scanner.nextLine());
			System.out.println("Is this a recurring price: 0.No 1.Yes");
			isRecurrence = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter the Product Pictures URL : (OPTIONAL. Press Enter to Skip) ");
			pictures = scanner.nextLine();
			
			if(updateMode) {
				System.out.println("Enter the Product Id : ");
				id = Integer.parseInt(scanner.nextLine());
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
		
		return true;

	}
	
	public void getDetails() {
		
		System.out.println("--------------------------");
		System.out.println("Id :"+id);
		System.out.println("UID :"+uid);
		System.out.println("Product Name : "+productName);
		System.out.println("category Id : "+categoryId);
		System.out.println("Brand : "+brand);
		
		String statusText = "";
		if(status == 0) {
			statusText = "pending";
		}else if(status == 1) {
			statusText = "approved";
		}else if(status == 2) {
			statusText = "rejected";
		}else if(status == 3) {
			statusText = "sold";
		}else {
			statusText = null;
		}
		System.out.println("Status : "+statusText);
		
		System.out.println("HeadLine : "+headline);
		
		String conditionText = "";
		if(condition == 1) {
			conditionText = "Brand New(Sealed)";
		}else if(condition == 2) {
			conditionText = "Lightly Used";
		}else if(condition == 3) {
			conditionText = "Moderately Used";
		}else if(condition == 4) {
			conditionText = "Heavily Used";
		}else if(condition == 5) {
			conditionText = "Damaged/Dented";
		}else if(condition == 6) {
			conditionText = "Not Working";
		}else {
			conditionText = null;
		}
		
		System.out.println("Condition : "+conditionText);
		System.out.println("Description : "+description);
		
		if(isRecurrence == 1) {
			System.out.println("Price :"+price+" per Month");
		}else if(isRecurrence == 0) {
			System.out.println("Price : "+price);
		}
		
		System.out.println("Picture URL : "+pictures);
		System.out.println("Last Updated On : "+lastUpdatedOn);
		System.out.println("--------------------------");

	}
	
}
