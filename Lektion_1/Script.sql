

-- Detta är en kommentar - den körs inte av databasen.

-- Skapa en databas som heter "Football"
CREATE DATABASE Football;

-- Använd football-databasen som aktuell databas
USE Football;

-- Visa vilka tabeller som finns i databasen
SHOW TABLES;

-- Skapa en tabell för våra fotbollsklubbar
CREATE TABLE Clubs (
	id INT,
	name VARCHAR(200)
);


-- Beskriv hur databasen är konstruerad
DESCRIBE Clubs;

-- Stoppa in data i databasen
INSERT INTO Clubs (
	id,
	name
) VALUES (
	1,
	'Snogeröds IF'
);

INSERT INTO Clubs (
	name, 
	id
) VALUES (
	'Maglasäte IF',
	22
);

-- Lista datan i Clubs tabellen
SELECT * FROM Clubs;

