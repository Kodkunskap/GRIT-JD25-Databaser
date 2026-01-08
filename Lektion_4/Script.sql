
USE CRM;

SHOW TABLES;

DESCRIBE Accounts;

SELECT id, name FROM Accounts ORDER BY name LIMIT 2;

SELECT * FROM Accounts;

-- Uppdatera från Panduro till Kjell & Company AB för id 6
UPDATE Accounts SET name = 'Kjell & Company AB' WHERE id = 6;

-- Uppdatera från Panduro till Teknikmagasinet AB 
-- och ändra statusen till Prospect för id 7
UPDATE 
	Accounts 
SET 
	name = 'Teknikmagasinet AB',
	status = 'Prospect'
WHERE 
	id = 7;
	
-- Uppdatera noOfEmployees till 0 för 
-- alla Panduro företag
UPDATE 
	Accounts 
SET
	noOfEmployees = 0
WHERE 
--	id IN (5, 8, 9)
	name = 'Panduro';
	
SELECT * FROM Contacts;

-- Uppdatera antalet anställda för id 10
UPDATE 
	Accounts
SET
	noOfEmployees = 2
WHERE 
	id = 10;
	
-- Räkna hur många kontakter (Contacts) som finns på id 10
SELECT COUNT(id) FROM Contacts WHERE accountsId = 10;

-- Slå ihop båda frågorna och uppdatera alla kontos 
-- noOfEmployees baserat på hur många kontakter dem har
UPDATE 
	Accounts
SET
	noOfEmployees = (
		SELECT 
			COUNT(id) 
		FROM 
			Contacts 
		WHERE 
			accountsId = Accounts.id
	)
;

UPDATE
	Accounts
SET
	noOfEmployees = noOfEmployees + 7;

-- Vi skall radera dubletterna av Panduro som 
-- har id 8 och 9
DELETE FROM Accounts WHERE id IN (8, 9);

DELETE FROM Accounts WHERE id = 1;

-- Tabort alla Accounts som inte har några kontakter
-- dvs noOfEmployees = 0
DELETE FROM Accounts WHERE noOfEmployees = 0;
-- id IN (1, 4, 5, 7);


-- Skapar en transaktion
START TRANSACTION;

-- Gör en föränding i databasen
DELETE FROM Contacts;

SELECT * FROM Contacts;

-- Ångrar ändringarna som gjordes i transaktionen
ROLLBACK ;


-- Skapar en transaktion
START TRANSACTION;

-- Gör en förändring till databasen
UPDATE Accounts SET noOfEmployees = 0; 

SELECT * FROM Accounts;

-- Gör ändringar i transaktionen permanenta
COMMIT;



