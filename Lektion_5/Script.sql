
CREATE DATABASE Relationer;

USE Relationer;

-- En-till-en relation (kan göras i en eller)
-- flera tabeller) - löser den med en tabell. 
CREATE TABLE Persons (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL, 
	email VARCHAR(255) NULL
);

-- En-till-många
CREATE TABLE Blogs (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	subject VARCHAR(255) NOT NULL,
	body TEXT NOT NULL
);

CREATE TABLE Comments (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	comment VARCHAR(255) NOT NULL, 
	blogsId BIGINT NOT NULL, 
	FOREIGN KEY (blogsId) REFERENCES Blogs(id)
);

-- Många-till-många
CREATE TABLE Orders (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	orderDescription VARCHAR(30) NOT NULL UNIQUE,
	total FLOAT NOT NULL DEFAULT 0
);

CREATE TABLE Products (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL, 
	price FLOAT NOT NULL DEFAULT 0
);

CREATE TABLE OrderProducts (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	ordersId BIGINT NOT NULL, 
	productsId BIGINT NOT NULL, 
	qty INT NOT NULL DEFAULT 0, 
	FOREIGN KEY (ordersId) REFERENCES Orders(id),
	FOREIGN KEY (productsId) REFERENCES Products(id)
);

-- 
-- Slå ihop data med JOINS
--

USE CRM;
DESCRIBE Accounts;
DESCRIBE Contacts;

SELECT * FROM Contacts;
SELECT * FROM Accounts;


-- Länkade (JOIN) frågor
SELECT 
	c.fullname AS name,  -- c.fullname döps om till name
	c.email, 
	a.name AS company	-- a.name döpas om till company
FROM 
	Contacts c 			-- Contacts får aliaset c
INNER JOIN 
	Accounts a 			-- Länka Accounts tabellen - ge den aliaset a
ON
	c.accountsId = a.id	-- Länka ihop tabellerna med accountsId i Contacts och id i Accounts
WHERE
	a.status = 'Prospect'	-- Filtrera att bara visa Prospect
ORDER BY 
	company;				-- Sortera svaret i bokstavsordning för företaget


USE Relationer;

SHOW TABLES;

DESCRIBE Products;
DESCRIBE Orders;
DESCRIBE OrderProducts;

INSERT INTO Products (name, price) VALUES 
('Volvo', 98000), ('Polagris', 34), ('Potatis (kg)', 5.34);


INSERT INTO Orders (orderDescription, total) VALUES 
('Beas Order', 0), ('Iffes', 0);

INSERT INTO OrderProducts (ordersId, productsId, qty) VALUES
(1, 1, 1), (2, 2, 5), (2, 3, 12);


-- Länka i hop tre taballer
--   Orders (id) med OrderProducts (orderId)
--   OrderProducts (productsId) med Products (id)
SELECT
	o.orderDescription,
	op.qty,
	p.name
FROM 
	Orders o
LEFT JOIN
	OrderProducts op
ON 
	o.id = op.ordersId
LEFT JOIN
	Products p
ON 
	op.productsId = p.id;




