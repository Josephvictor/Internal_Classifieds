package com.amazon.Classifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.amazon.Classifieds.models.Orders;

public class OrdersDAO implements DAO<Orders> {
	
	DB db = DB.getInstance();

	public int insert(Orders object) {
		String sql = "INSERT INTO Orders (classifiedId,classifiedName,fromUserId,toUserId,proposedPrice,status) VALUES ("+object.classifiedId+", '"+object.classifiedName+"',"+object.fromUserId+", "+object.toUserId+", "+object.proposedPrice+", "+object.status+")";
		return db.executeSQL(sql);
	}

	public int update(Orders object) {
		
		if(object.status == 3) {
			String sql1 = "UPDATE Orders SET status = 4 WHERE classifiedId = "+object.classifiedId+" AND id <> "+object.id+"";
			db.executeSQL(sql1);
		}
		String sql = "UPDATE Orders SET status = "+object.status+", proposedPrice = "+object.proposedPrice+",lastUpdatedOn = CURRENT_TIMESTAMP WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public int delete(Orders object) {
		String sql = "DELETE FROM Orders WHERE id = "+object.id+"";
		return db.executeSQL(sql);
	}

	public List<Orders> retrieve() {
		
		ArrayList<Orders> objects = new ArrayList<Orders>();
		
		String sql = "SELECT * FROM Orders";
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Orders object = new Orders();
				
				object.id = set.getInt("id");
				object.classifiedId = set.getInt("classifiedId");
				object.fromUserId = set.getInt("fromUserId");
				object.toUserId = set.getInt("toUserId");
				object.proposedPrice = set.getInt("proposedPrice");
				object.status = set.getInt("status");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

	public List<Orders> retrieve(String sql) {
		
		ArrayList<Orders> objects = new ArrayList<Orders>();
		
		ResultSet set = db.executeQuery(sql);
		
		try {
			while(set.next()) {
				
				Orders object = new Orders();
				
				object.id = set.getInt("id");
				object.classifiedId = set.getInt("classifiedId");
				object.classifiedName = set.getString("classifiedName");
				object.fromUserId = set.getInt("fromUserId");
				object.toUserId = set.getInt("toUserId");
				object.proposedPrice = set.getInt("proposedPrice");
				object.status = set.getInt("status");
				object.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				objects.add(object);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return objects;
	}

}
