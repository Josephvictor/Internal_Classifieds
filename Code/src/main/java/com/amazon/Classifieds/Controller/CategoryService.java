package com.amazon.Classifieds.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.amazon.Classifieds.db.CategoryDAO;
import com.amazon.Classifieds.models.Category;

public class CategoryService {

	CategoryDAO categoryDAO = new CategoryDAO();
	Scanner scanner = new Scanner(System.in);
	
	private static CategoryService categoryService = new CategoryService();
	
	private CategoryService() {
		
	}
	
	public static CategoryService getInstance() {
		return categoryService;
	}
	
	
	public void addCategory() {
		
		Category object = new Category();
		
		object.setDetails(false);
		int result = categoryDAO.insert(object);
		
		if(result > 0) {
			System.out.println("Category Added Successfully");
		}else {
			System.err.println("Failed to add the Category "+object.title);
		}
	}
	
	public void updateCategory() {
		
		viewCategories();
		Category object = new Category();
		
		object.setDetails(true);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		object.lastUpdatedOn = formatter.format(date);
		
		int result = categoryDAO.update(object);
		
		String message = (result > 0) ? "Category Updated Successfully" : "Failed to Update the Category "+object.title;
		
		System.out.println(message);
	}
	
	public void deleteCategory() {
		
		try {
			
			Category object = new Category();
			
			System.out.println("Enter the ID : ");
			object.id = Integer.parseInt(scanner.nextLine());
			
			int result = categoryDAO.delete(object);
			
			if(result > 0) {
				System.out.println("Categroy Deleted SuccessFully");
			}else {
				System.err.println("Failed to Delete...");
			}
		}catch(Exception e) {
			System.err.println("Something went wrong "+e);
		}
		
	}
	
	public void viewCategories() {
		
		List<Category> objects = null;
		
		objects = categoryDAO.retrieve();
		if(objects != null) {
			for(Category object : objects) {
				object.getDetails();
			}
		}else {
			System.err.println("[CategoryService] Something Went Wrong");
		}
	}
}
