--Admin Creation

INSERT INTO Users (name,phone,email,password,address,department,type) VALUES ('Admin1','8888855555','admin1@amazon.com','admin123','BLR12','Gov',1);
go

INSERT INTO Users (name,phone,email,password,address,department,type) VALUES ('Admin2','7999945666','admin2@amazon.com','admin234','MAA12','Gov',1);
go

UPDATE Users SET password = 'admin123' WHERE id = 3;
UPDATE Users SET password = 'admin234' WHERE id = 4;
go

UPDATE Users SET email = 'jose@amazon.com' WHERE id = 1;
UPDATE Users SET name = 'Yogesh', phone = '88883333666',email = 'yogi@amazon.com' WHERE id = 2;
go

UPDATE Users SET password = 'yogi123' WHERE id = 2;
go

UPDATE Users SET name = 'admin1', phone = '8888877777',address = 'BLR2', department = 'Administration' WHERE id = 3;
UPDATE Users SET name = 'admin2', phone = '7777766666',address = 'SEA1', department = 'Administration' WHERE id = 4;

