package com.amazon.Classifieds.models;

/*
CREATE TABLE Orders(
 	id INT PRIMARY KEY IDENTITY(1,1),
 	classifiedId INT NOT NULL,
 	classifiedName NVARCHAR(50) NOT NULL,
 	fromUserId INT NOT NULL,
 	toUserId INT NOT NULL,
 	proposedPrice INT NOT NULL,
 	status INT NOT NULL,		--( 0 - requested for purchase, 1 - approved i.e. agreed to sell, 2 - rejected i.e. not interested to in this price, 3 - payment processed -> Product sold, 4 - Item sold to someone else )
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
 	FOREIGN KEY (classifiedId) REFERENCES Classifieds(id),
 	FOREIGN KEY (fromUserId) REFERENCES Users(id),
 	FOREIGN KEY (toUserId) REFERENCES Users(id)
)
 */

public class Orders {
	
	public int id;
	public int classifiedId;
	public String classifiedName;
	public int fromUserId;
	public int toUserId;
	public int proposedPrice;
	public int status;					// 0 - requested for purchase, 1 - approved i.e. agreed to sell, 2 - rejected i.e. not interested to in this price, 3 - payment processed -> Product sold, 4 - Item sold to someone else 
	public String lastUpdatedOn;
	
	public Orders() {
		
	}

	public Orders(int id, int classifiedId, String classifiedName,int fromUserId, int toUserId, int proposedPrice, int status,
			String lastUpdatedOn) {
		this.id = id;
		this.classifiedId = classifiedId;
		this.classifiedName = classifiedName;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.proposedPrice = proposedPrice;
		this.status = status;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public void getDetails() {
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Order Id :\t\t"+id);
		System.out.println("Classified Id :\t\t"+classifiedId);
		System.out.println("Classified Name:\t"+classifiedName);
		System.out.println("Buyer uid :\t\t"+fromUserId);
		System.out.println("Seller uid :\t\t"+toUserId);
		System.out.println("Price proposed :\t"+proposedPrice);
		
		String statusText = "";
		if(status == 0) {
			statusText = "Requested to Purchase";
		}else if(status == 1) {
			statusText = "Approved to sell";
		}else if(status == 2) {
			statusText = "Rejected the offer";
		}else if(status == 3) {
			statusText = "Classified sold";
		}else if(status == 4) {
			statusText = "Classified sold to someone else";
		}else {
			statusText = null;
		}
		System.out.println("Status :\t\t"+statusText);
		System.out.println("Last Updated On :\t"+lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
