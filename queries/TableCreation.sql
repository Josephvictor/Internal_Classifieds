USE Classifieds
GO

CREATE TABLE Users(
 		id INT PRIMARY KEY IDENTITY(1,1),
 		name NVARCHAR(25) NOT NULL,
 		phone NVARCHAR(15) NOT NULL,
 		email NVARCHAR(50) NOT NULL UNIQUE,
 		password NVARCHAR(50) NOT NULL,
 		address NVARCHAR(50),
 		department NVARCHAR(15),
 		type INT DEFAULT 1,
 		lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
	GO

	CREATE TABLE Category (
	id INT PRIMARY KEY IDENTITY(1,1),
	title NVARCHAR(25) NOT NULL,
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
	go


CREATE TABLE Classifieds(
 		id INT PRIMARY KEY IDENTITY(1,1),
 		categoryId INT,
 		uid INT NOT NULL,
 		status INT DEFAULT 1,
 		headline NVARCHAR(100) NOT NULL,
 		productName NVARCHAR(50) NOT NULL,
 		brand NVARCHAR(25),
 		condition INT NOT NULL,
 		description NVARCHAR(500) NOT NULL,
 		price INT NOT NULL,
 		isRecurrence INT NOT NULL,
 		pictures NVARCHAR(50),
 		lastUpdatedOn DATETIME DEFAULT CURRENT_TIMEsTAMP
 		FOREIGN KEY (categoryId) REFERENCES Category(id),
 		FOREIGN KEY (uid) REFERENCES Users(id) ON DELETE CASCADE
 	)
	GO

	CREATE TABLE Orders(
 		id INT PRIMARY KEY IDENTITY(1,1),
 		classifiedId INT NOT NULL,
 		fromUserId INT NOT NULL,
 		toUserId INT NOT NULL,
 		proposedPrice INT NOT NULL,
 		status INT NOT NULL,
 		lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
 		FOREIGN KEY (classifiedId) REFERENCES Classifieds(id),
 		FOREIGN KEY (fromUserId) REFERENCES Users(id),
 		FOREIGN KEY (toUserId) REFERENCES Users(id)
 	)
	GO