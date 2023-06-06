 	CREATE TABLE Users(
 		id INT PRIMARY KEY IDENTITY(1,1),
 		name NVARCHAR(30) NOT NULL,
 		phone NVARCHAR(15) NOT NULL,
 		email NVARCHAR(50) NOT NULL UNIQUE,
 		password NVARCHAR(50) NOT NULL,
 		address NVARCHAR(50) NOT NULL,
 		department NVARCHAR(15) NOT NULL,
 		type INT NOT NULL,								--(1.Admin 2.User)
 		isActive INT NOT NULL,							--(0 De-active 1.Active)
 		lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
	GO

	CREATE TABLE Category (
 	id INT PRIMARY KEY IDENTITY(1,1),
 	title NVARCHAR(30) NOT NULL UNIQUE,
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
 	)
	GO

	CREATE TABLE Classifieds(
 	id INT PRIMARY KEY IDENTITY(1,1),
 	categoryId INT NOT NULL,
 	uid INT NOT NULL,
 	status INT NOT NULL,						--(0 - pending, 1 - approved, 2 - rejected, 3 - sold)
 	headline NVARCHAR(100) NOT NULL,
 	productName NVARCHAR(50) NOT NULL,
 	brand NVARCHAR(25),
 	condition INT NOT NULL,						--(1- Brand New(Seal Packed), 2- Lightly Used, 3 - Moderately Used, 4 - Heavily Used, 5 - Damaged/Dented, 6 - Not Working)
 	description NVARCHAR(500) NOT NULL,
 	price INT NOT NULL,
 	isRecurrence INT NOT NULL,					--(// 1.YES || 0.NO)
 	pictures NVARCHAR(50),
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMEsTAMP
	FOREIGN KEY (uid) REFERENCES Users(id) ON DELETE CASCADE,
 	FOREIGN KEY (categoryId) REFERENCES Category(id)
)
GO

CREATE TABLE Orders(
 	id INT PRIMARY KEY IDENTITY(1,1),
 	classifiedId INT NOT NULL,
 	classifiedName NVARCHAR(50) NOT NULL,
 	fromUserId INT NOT NULL,
 	toUserId INT NOT NULL,
 	proposedPrice INT NOT NULL,
 	status INT NOT NULL,		--(// 0 - requested for purchase, 1 - approved i.e. agreed to sell, 2 - rejected i.e. not interested to in this price, 3 - payment processed -> Product sold, 4 - Item sold to someone else )
 	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
 	FOREIGN KEY (classifiedId) REFERENCES Classifieds(id),
 	FOREIGN KEY (fromUserId) REFERENCES Users(id),
 	FOREIGN KEY (toUserId) REFERENCES Users(id)
)