package com.amazon.Classifieds;

import com.amazon.Classifieds.Controller.AuthenticationService;
import com.amazon.Classifieds.Controller.CategoryService;
import com.amazon.Classifieds.Controller.ClassifiedsService;
import com.amazon.Classifieds.models.Users;

public class AdminMenu extends Menu {
	
private static AdminMenu menu = new AdminMenu();

	CategoryService categoryService = CategoryService.getInstance();
	AuthenticationService authenticationService = AuthenticationService.getInstance();
	ClassifiedsService classifiedsService = ClassifiedsService.getInstance();
	
	private AdminMenu() {
		
	}
	
	public static AdminMenu getInstance() {
		return menu;
	}
	
	public void showMenu() {
		
		Users adminUser = new Users();
		adminUser.type = 1;
		adminUser = authenticationService.login(adminUser);
			
		if(adminUser != null && adminUser.type == 1) {
			
			ClassifiedsSessionHandler.user = adminUser;
			
			boolean flag = true;
			
			while(flag) {
				
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("1. Approve/Reject Classifieds");
						System.out.println("2. Activate/Deactivate User");
						System.out.println("3. Manage Categories of Classifieds");
						System.out.println("0. Quit the Admin App");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("Select to proceed");
						
						int adminchoice = 0;
						try {
							adminchoice = Integer.parseInt(scanner.nextLine());
						}catch(Exception e) {
							System.err.println("Something went wrong "+e);
						}
					
						switch(adminchoice) {
						case 1:
							classifiedsService.approveRejectClassifieds();
							break;
						case 2:
							authenticationService.activateUser();
							break;
						case 3:
							System.out.println();
							System.out.println("1. Add a Category");
							System.out.println("2. Delete a Category");
							System.out.println("3. Update a Category");
							System.out.println("4. View All Categories");
							System.out.println("\n Enter Your Choice : ");
							int categoryChoice = Integer.parseInt(scanner.nextLine());
							
							if(categoryChoice == 1) {
								categoryService.addCategory();
							}else if(categoryChoice == 2) {
								categoryService.deleteCategory();
							}else if(categoryChoice == 3) {
								categoryService.updateCategory();
							}else if(categoryChoice == 4) {
								categoryService.viewCategories();
							}else {
								System.err.println("[Category] Invalid Choice..");
							}
							break;
						case 0:
							flag = false;
							System.out.println("Exiting the Admin App.");
							break;
						default:
							System.err.println("[AdminMenu] Invalid Choice..");
							break;
						}
				}
			}else {
			System.err.println("[AdminMenu] Login Failed..");
		}
	}
}
