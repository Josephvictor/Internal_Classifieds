package com.amazon.Classifieds;

import java.util.Scanner;
import com.amazon.Classifieds.db.DB;

public class Menu {
	
	Scanner scanner = new Scanner(System.in);
	
	public void mainMenu() {
		
		DB db = DB.getInstance();
		
		while(true) {
			System.out.println("1. Admin");
			System.out.println("2. User");
			System.out.println("0. Quit");
			System.out.println("Proceed as select");
			
			int choice = 0;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			}catch(Exception e) {
				System.err.println("Something went wrong "+e);
			}
			
		
			if(choice == 0) {
				System.out.println("Thank You.. Come back Again!!");
				db.closeConnection();
				scanner.close();
				break;
			}
			
			//A Factory design to create admin and user menu is constructed
			//MenuFactory factory = new MenuFactory();
			Menu menu = MenuFactory.getMenu(choice);
		
			if(menu != null) {
				menu.showMenu();
			}else {
				System.err.println("Incorrect Input..Try Again");
			}
		}
		
	}

	public void showMenu() {
		System.out.println("Showing the Menu...");
	}
}
