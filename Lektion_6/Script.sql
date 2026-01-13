
USE CRM;

DESCRIBE Accounts;
SELECT id, name, 'Accounts' FROM Accounts;

DESCRIBE Contacts;
SELECT id, fullname, 'Contacts' FROM Contacts;


SELECT id, name, 'Accounts' FROM Accounts WHERE name LIKE '%Martin%'
UNION
SELECT id, fullname, 'Contacts' FROM Contacts WHERE fullname LIKE '%Martin%';


SELECT id, fullname, email, accountsId FROM Contacts WHERE id = 1;

INSERT INTO Contacts (fullname, email, accountsId) VALUES ('FULLNAME', 'EMAIL', 10);
UPDATE Contacts SET fullname='NEWFULLNAME', email='NEWEMAIL', accountsId=10 WHERE id = 9;

SELECT * FROM Contacts c ;