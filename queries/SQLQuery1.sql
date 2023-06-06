use Classifieds

select * from Users
select * from Classifieds
select * from Orders
SELECT * FROM Category
go

INSERT INTO Classifieds (categoryId,uid,status,headline,productName,brand,condition,description,price,isRecurrence) 
VALUES (1,2,1,'Selling my washing machine','whirhpool white magic','whirhpool',3,'domangoli product',5000,0) 
go

update Orders set status = 0, lastUpdatedOn = CURRENT_TIMESTAMP where classifiedId = 5;
GO

INSERT INTO Orders (classifiedId,fromUserId,toUserId,proposedPrice,status) VALUES (4,2,1,13000,0)
go

DELETE FROM Orders where id = 8;
go

	SELECT * FROM Orders WHERE fromUserId = 1 AND classifiedId = 5

insert into Users(name, phone, email, password, address, department, type) VALUES ('sudha', 1234567, 'sudha',4321, 'MAA23','aws', 2) 
go

ALTER TABLE Orders
ADD classifiedName NVARCHAR(50)
