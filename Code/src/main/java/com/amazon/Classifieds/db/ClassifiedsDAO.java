	package com.amazon.Classifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.amazon.Classifieds.models.Classifieds;

public class ClassifiedsDAO implements DAO<Classifieds>{
	
	DB db = DB.getInstance();

	public int insert(Classifieds object) {
		String sql = "INSERT INTO Classifieds (categoryId,uid,headline,productName,brand,condition,description,price,isRecurrence,pictures,status) VALUES ("+object.categoryId+", "+object.uid +",'"+object.headline+"', '"+object.productName+"', '"+object.brand+"', "+object.condition+", '"+object.description+"', "+object.price+", "+object.isRecurrence+",'"+object.pictures+"',"+object.status+")";
		return db.executeSQL(sql);
	}

	public int update(Classifieds object) {
		String sql = "UPDATE Classifieds SET status = "+object.status+", lastUpdatedOn = CURRENT_TIMESTAMP WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public int delete(Classifieds object) {
		String sql = "DELETE FROM Classifieds WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public List<Classifieds> retrieve() {
		
		ArrayList<Classifieds> objects = new ArrayList<Classifieds>();
		
		String sql = "SELECT * FROM Classifieds";
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Classifieds object = new Classifieds();
				
				object.id = set.getInt("id");
				object.categoryId = set.getInt("categoryId");
				object.uid = set.getInt("uid");
				object.status = set.getInt("status");
				object.headline = set.getString("headline");
				object.productName = set.getString("productName");
				object.brand = set.getString("brand");
				object.condition = set.getInt("condition");
				object.description = set.getString("description");
				object.price = set.getInt("price");
				object.isRecurrence = set.getInt("isRecurrence");
				object.pictures = set.getString("pictures");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

	public List<Classifieds> retrieve(String sql) {
		
		ArrayList<Classifieds> objects = new ArrayList<Classifieds>();
		
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Classifieds object = new Classifieds();
				
				object.id = set.getInt("id");
				object.categoryId = set.getInt("categoryId");
				object.uid = set.getInt("uid");
				object.status = set.getInt("status");
				object.headline = set.getString("headline");
				object.productName = set.getString("productName");
				object.brand = set.getString("brand");
				object.condition = set.getInt("condition");
				object.description = set.getString("description");
				object.price = set.getInt("price");
				object.isRecurrence = set.getInt("isRecurrence");
				object.pictures = set.getString("pictures");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
				System.out.println(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

}
