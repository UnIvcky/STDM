
--DROP DATABASE InventarIlluminaten;
--CREATE DATABASE InventarIlluminaten;


CREATE TABLE Locations (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    location nvarchar(255) -- z.B. A.105
);

CREATE TABLE Customer (
    ID INTEGER PRIMARY KEY AUTOINCREMENT, 
    GivenName nvarchar(255),
    Address nvarchar(255)
);

CREATE TABLE Products (
    ID INTEGER PRIMARY KEY AUTOINCREMENT, 
    Productname nvarchar(255), 
    Price float,
    Supplier_ID, --fk Suppliers.ID

    FOREIGN KEY(Supplier_ID) REFERENCES Suppliers(ID)
);

CREATE TABLE Storage (
    ID INTEGER PRIMARY KEY AUTOINCREMENT, 
    Product_ID int, -- fk Products.ID
    Location_ID int, -- fk Location.ID 

    FOREIGN KEY(Product_ID) REFERENCES Products(ID)
    FOREIGN KEY(Location_ID) REFERENCES Locations(ID)
);

CREATE TABLE Suppliers (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Suppliername nvarchar(255)
);

CREATE TABLE Orders (
    ID INTEGER PRIMARY KEY AUTOINCREMENT, 
    Customer_ID int, -- fk Customer.ID 
    Product_ID int, -- fk Products.ID
    
    FOREIGN KEY(Customer_ID) REFERENCES Customers(ID)
    FOREIGN KEY(Product_ID) REFERENCES Products(ID)
);

CREATE TABLE SupplierOrders (
    ID int AUTOINCREMENT, 
    Product_ID int, -- fk -- Products.ID
    Completed bit,

    FOREIGN KEY(Product_ID) REFERENCES )
);

INSERT INTO Locations(location)
VALUES("A.1");
INSERT INTO Locations(location)
VALUES("A.5");
INSERT INTO Locations(location)
VALUES("B.23");
INSERT INTO Locations(location)
VALUES("C.11");
INSERT INTO Locations(location)
VALUES("B.3");

INSERT INTO Customer(GivenName, Address)
VALUES("Marcel Wortmann", "Musterstraße 6a");
INSERT INTO Customer(GivenName, Address)
VALUES("Luca VDH", "Musterweg 7a");
INSERT INTO Customer(GivenName, Address)
VALUES("Joshua Voigt", "MusterAllee 8a");

INSERT INTO Suppliers(Suppliername)
VALUES("Porsche");
INSERT INTO Suppliers(Suppliername)
VALUES("Audi");
INSERT INTO Suppliers(Suppliername)
VALUES("Mercedes");

INSERT INTO Products(Productname, Price, Supplier_ID)
VALUES("Tisch", 49.99, 1 );
INSERT INTO Products(Productname, Price, Supplier_ID)
VALUES("Stuhl", 29.99, 1 );
INSERT INTO Products(Productname, Price, Supplier_ID)
VALUES("PC", 4000.99, 2 );
INSERT INTO Products(Productname, Price, Supplier_ID)
VALUES("Luca`s Ehre", 0.00, 3 );

INSERT INTO Storage(Product_ID, Location_ID)
VALUES(1, 1);
INSERT INTO Storage(Product_ID, Location_ID)
VALUES(1, 3);
INSERT INTO Storage(Product_ID, Location_ID)
VALUES(2, 2);
INSERT INTO Storage(Product_ID, Location_ID)
VALUES(3, 4);


INSERT INTO Orders(Customer_ID, Product_ID)
VALUES(1, 2);
INSERT INTO Orders(Customer_ID, Product_ID)
VALUES(1, 1);
INSERT INTO Orders(Customer_ID, Product_ID)
VALUES(2, 2);
INSERT INTO Orders(Customer_ID, Product_ID)
VALUES(3, 2);
INSERT INTO Orders(Customer_ID, Product_ID)
VALUES(2, 4);

INSERT INTO SupplierOrders(Product_ID, Completed) 
VALUES(1,1);
INSERT INTO SupplierOrders(Product_ID, Completed) 
VALUES(1,1);
INSERT INTO SupplierOrders(Product_ID, Completed) 
VALUES(2,0);
INSERT INTO SupplierOrders(Product_ID, Completed) 
VALUES(1,0);

CREATE TRIGGER tr_reorder_Product
    AFTER DELETE ON Storage
    WHEN(  
        (SELECT COUNT(*) from Storage WHERE Product_ID = NEW.Product_ID) < 10
    )
BEGIN
    INSERT INTO SupplierOrders(Product_ID, Completed) 
	VALUES ((SELECT product_ID from Products where ID = NEW.Product_ID), 0);

END;


CREATE TRIGGER tr_remove_from_Storage
    AFTER INSERT ON Orders

BEGIN 
    DELETE FROM Storage 
        WHERE ID = (
                    SELECT ID from Storage WHERE Product_ID = New.Product_ID
                    LIMIT 1
                );
END;

CREATE VIEW View_Customers AS 
    SELECT * FROM CUSTOMERS

