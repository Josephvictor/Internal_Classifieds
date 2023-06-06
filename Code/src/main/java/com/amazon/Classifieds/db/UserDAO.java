package com.amazon.Classifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.amazon.Classifieds.models.Users;

public class UserDAO implements DAO<Users>{
	
	DB db = DB.getInstance();

	public int insert(Users object) {
		String sql = "INSERT INTO Users (name,phone,email,password,address,department,type,isActive) VALUES ('"+object.name+"','"+object.phone+"','"+object.email+"', '"+object.password+"','"+object.address+"','"+object.department+"',"+object.type+","+object.isActive+")";
		return db.executeSQL(sql);
	}

	public int update(Users object) {
		String sql = "UPDATE Users SET name = '"+object.name+"', phone = '"+object.phone+"', password = '"+object.password+"', address = '"+object.address+"', department = '"+object.department+"', lastUpdatedOn = '"+object.lastUpdatedOn+"', isActive = "+object.isActive+" WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}
	
	public int activateUser(Users object) {
		String sql = "UPDATE Users SET isActive = "+object.isActive+" WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public int delete(Users object) {
		String sql = "DELETE FROM Users WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public List<Users> retrieve() {
		
		ArrayList<Users> objects = new ArrayList<Users>();
		
		String sql = "SELECT * FROM Users";
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				Users object = new Users();
				object.id = set.getInt("id");
				object.name = set.getString("name");
				object.phone = set.getString("phone");
				object.email = set.getString("email");
				object.address = set.getString("address");
				object.department = set.getString("department");
				object.type = set.getInt("type");
				object.isActive = set.getInt("isActive");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

	public List<Users> retrieve(String sql) {
		
		ArrayList<Users> objects = new ArrayList<Users>();
		
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				Users object = new Users();
				object.id = set.getInt("id");
				object.name = set.getString("name");
				object.phone = set.getString("phone");
				object.email = set.getString("email");
				object.address = set.getString("address");
				object.department = set.getString("department");
				object.type = set.getInt("type");
				object.isActive = set.getInt("isActive");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

}
