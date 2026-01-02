

SHOW DATABASES;

USE CRM;

SHOW TABLES;

DESCRIBE Accounts; 
DESCRIBE Contacts;


SELECT * FROM Accounts;

-- Id, namn och status för alla Accounts
SELECT id, name, status FROM Accounts;

-- Samma fast IKEA
SELECT id, name, status FROM Accounts WHERE name = 'IKEA';

SELECT * FROM Contacts;

-- Lista alla kontaker på IKEA (som har id = 1)
SELECT fullname, email FROM Contacts WHERE accountsId = 1;

SELECT name, noOfEmployees FROM Accounts WHERE noOfEmployees > 0;
SELECT name, noOfEmployees FROM Accounts WHERE noOfEmployees IS NULL;

SELECT name FROM Accounts WHERE noOfEmployees IS NOT NULL AND noOfEmployees < 1000;

-- Lista alla Account som har anställda i stigande ordning
SELECT 
	name, 
	noOfEmployees 
FROM 
	Accounts
WHERE
	noOfEmployees IS NOT NULL
ORDER BY
	noOfEmployees ASC,
	name ASC;

-- Hur många Accounts har vi? 
SELECT COUNT(id) AS cnt FROM Accounts;

-- Största och minsta Id från Accounts
SELECT MIN(id) AS Minsta, MAX(id) AS Största FROM Accounts;

-- Vad är det totala antalet anställda och medeltalet hos
-- företagen i vårt Accounts register
SELECT 
	SUM(noOfEmployees) AS Total,
	AVG(noOfEmployees) AS Average
FROM
	Accounts 
WHERE
	noOfEmployees IS NOT NULL;

-- Samma som tidigare fråga, men grupperat efter namn
SELECT 
	name, 
	SUM(noOfEmployees) AS Total,
	AVG(noOfEmployees) AS Average
FROM
	Accounts 
WHERE
	noOfEmployees IS NOT NULL
GROUP BY 
	name;

-- LAST_INSERT_ID()

INSERT INTO Accounts ( 
	name, 
	noOfEmployees
) VALUES (
	'Spotify',
	8456
);

SELECT LAST_INSERT_ID();

INSERT INTO Contacts (
	fullname,
	email, 
	accountsID
) VALUES (
	'Martin Haagen',
	'martin.haagen@spotify.com',
	LAST_INSERT_ID()
), (
	'Peter Persson',
	'peter.persson@spotify.com',
	LAST_INSERT_ID()
);

-- Lista alla Spotify kontakter
SELECT * FROM Contacts WHERE accountsId = 10;


-- 
-- Nästlade frågor
-- 

-- Vad vi vill göra - hitta alla kontakter som jobbar på Spotify
--                    namn och epost
-- 1. Vilket id har Spotify
-- 2. Vilka kontakter har detta id?

SELECT id FROM Accounts WHERE name = 'Spotify';
SELECT fullname, email FROM Contacts WHERE accountsId IN (10);

SELECT 
	fullname, 
	email 
FROM 
	Contacts 
WHERE 
	accountsId IN (
		SELECT 
			id 
		FROM 
			Accounts 
		WHERE 
			name = 'Spotify'
);

SELECT * FROM Contacts;

-- Vilka företag (namn) har kontakter med gritacademy.se 
-- i sin epost-adress. 
-- 1. Vilka kontakter har en gritacademy.se adress
-- 2. Vad heter dessa företagen? 
SELECT accountsId FROM Contacts WHERE email LIKE '%@gritacademy.se';
SELECT name FROM Accounts WHERE id IN (3, 1, 2);

SELECT 
	name 
FROM 
	Accounts 
WHERE 
	id IN (
		SELECT 
			accountsId 
		FROM 
			Contacts 
		WHERE 
			email LIKE '%@gritacademy.se'
	)
ORDER BY
	name DESC;


SELECT 
	fullname, 
	accountsId, 
	(
		SELECT 
			name 
		FROM 
			Accounts 
		WHERE 
			Accounts.id = accountsId
	) AS companyName
FROM 
	Contacts;
