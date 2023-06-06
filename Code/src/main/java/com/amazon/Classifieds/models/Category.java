package com.amazon.Classifieds.models;

import java.util.Scanner;

/*
CREATE TABLE Category (
 	id INT PRIMARY KEY IDENTITY(1,1),
 	title NVARCHAR(30) NOT NULL UNIQUE,
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
 */

public class Category {
	
	Scanner scanner = new Scanner(System.in);
	
	public int id;
	public String title;
	public String lastUpdatedOn;
	
	public Category(int id, String title, String lastUpdatedOn) {
		
		this.id = id;
		this.title = title;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public Category() {
		
	}
	
	public void setDetails(boolean updateMode) {
		
		if(updateMode) {
			System.out.println("Enter the ID : ");
			id = Integer.parseInt(scanner.nextLine());
		}
		
		System.out.println("Enter the Title : ");
		title = scanner.nextLine();
		

	}
	
	public void getDetails() {
		
		System.out.println();
		System.out.println("Category ID : "+id);
		System.out.println("Title : "+title);
		System.out.println("Last UpdatedOn : "+lastUpdatedOn);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}
}
