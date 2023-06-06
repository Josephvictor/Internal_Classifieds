package com.amazon.Classifieds;

import com.amazon.Classifieds.db.DB;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("--------------Welcome to Amazon Classifieds-------------");
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	
    	Menu menu = new Menu();
    	
    	//Gets the file path of the DBConfig file which contains the URL to create database connection
    	if(args.length > 0) {
    		DB.FILEPATH = args[0];
        }
    	
    	menu.mainMenu();
    }
}
