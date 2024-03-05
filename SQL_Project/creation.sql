
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
    
    FOREIGN KEY(Customer_ID) REFERENCES Customer(ID)
    FOREIGN KEY(Product_ID) REFERENCES Products(ID)
);

CREATE TABLE SupplierOrders (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Product_ID int, -- fk -- Products.ID
    quantity int,
    Completed bit,

    FOREIGN KEY(Product_ID) REFERENCES Products(ID)
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

INSERT INTO SupplierOrders(Product_ID, quantity, Completed) 
VALUES(1,5,1);
INSERT INTO SupplierOrders(Product_ID, quantity, Completed) 
VALUES(1,3,1);
INSERT INTO SupplierOrders(Product_ID, quantity, Completed) 
VALUES(2,4,0);
INSERT INTO SupplierOrders(Product_ID, quantity, Completed) 
VALUES(1,8,0);

CREATE TRIGGER tr_reorder_Product
    AFTER DELETE ON Storage
    WHEN(  
        (SELECT COUNT(*) from Storage WHERE Product_ID = old.Product_ID) < 10
    )
BEGIN
    INSERT INTO SupplierOrders(Product_ID, quantity, Completed) 
	VALUES ((SELECT ID from Products where ID = old.Product_ID), 10, 0);

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

CREATE VIEW vw_ProductTotal AS 
    SELECT p.id, p.productname, count(s.product_id) AS quantity 
    FROM Products p 
    LEFT JOIN Storage s 
    ON p.id = s.product_id 
    GROUP by s.product_id;

CREATE VIEW vw_CriticalStorage AS 
    SELECT *
    FROM vw_ProductTotal
    WHERE quantity < 15;

CREATE view vw_CustomerOrderQuantity AS
    SELECT c.id, c.GivenName, o.Product_id, p.ProductName, count(o.id) as quantity 
    FROM Customer c
    JOIN Orders o ON c.id = o.customer_id
    JOIN Products p ON p.id = o.Product_id
    GROUP By c.id, o.Product_id;
