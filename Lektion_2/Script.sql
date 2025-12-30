

-- Skapa en databas som heter CRM
CREATE DATABASE CRM; 

-- Använd CRM-databasen
USE CRM;

-- Radera min databas
DROP DATABASE CRM; 

-- Tabell för företag
--
--   id    name       noOfEmployees    status
--   -------------------------------------------
--    1    IKEA       45320            Customer
-- 
CREATE TABLE IF NOT EXISTS Accounts (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	noOfEmployees INT NULL,
	status ENUM('Prospect', 'Customer', 'Churned')
		NOT NULL DEFAULT 'Prospect'
);

-- Tabell för kontaktpersoner
-- 
-- id    fullname            email            accountsId
-- -----------------------------------------------------
--  5    Tyrian Lannister    tyrian@ikea.se   1
--  6	 Cercei Lannister	 c@ikea.se        1
-- 
CREATE TABLE IF NOT EXISTS Contacts (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	fullname VARCHAR(100) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	accountsId BIGINT NOT NULL,
	FOREIGN KEY (accountsId) REFERENCES Accounts (id)
);

-- Radera tabellerna från databasen
DROP TABLE Accounts; 
DROP TABLE Contacts;

DESCRIBE Accounts;
DESCRIBE Contacts;


-- Spara data i Account-tabell
INSERT INTO Accounts 
	(name, noOfEmployees, status)
VALUES 
	('IKEA', 45320, 'Customer');

INSERT INTO Accounts 
	(name)
VALUES 
	('Volvo'), ('Ericsson'), ('Saab');

SELECT * FROM Accounts;

-- Spara data i Contact-tabell
INSERT INTO Contacts
	(fullname, email, accountsId)
VALUES
	('Martin', 'martin@gritacademy.se', 3);

INSERT INTO Contacts
	(fullname, email, accountsId)
VALUES 
	('Peter', 'peter@gritacademy.se', 1),
	('Nina', 'nina@gritacademy.se', 2);


SELECT * FROM Contacts;


SHOW DATABASES;