package com.amazon.Classifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.Classifieds.models.Category;

public class CategoryDAO implements DAO<Category> {
	
	DB db = DB.getInstance();

	public int insert(Category object) {
		String sql = "INSERT INTO Category (title) VALUES ('"+object.title+"')";
		return db.executeSQL(sql);
	}

	public int update(Category object) {
		String sql = "UPDATE Category SET title = '"+object.title+"', lastUpdatedOn = '"+object.lastUpdatedOn+"' WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public int delete(Category object) {
		String sql = "DELETE FROM Category WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public List<Category> retrieve() {
		
		ArrayList<Category> objects = new ArrayList<Category>();
		
		String sql = "SELECT * FROM Category";
		
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Category object = new Category();
				object.id = set.getInt("id");
				object.title = set.getString("title");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		
		return objects;
	}

	public List<Category> retrieve(String sql) {
		
		ArrayList<Category> objects = new ArrayList<Category>();
		
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Category object = new Category();
				object.id = set.getInt("id");
				object.title = set.getString("title");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		
		return objects;
	}

}
