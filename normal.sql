

USE iths;

DROP TABLE IF EXISTS Student, School, StudentSchool, Phone, Hobbies, Grade, UNF;
DROP VIEW IF EXISTs PhoneList, StudentHobby;

CREATE TABLE `UNF` (
    `Id` DECIMAL(38, 0) NOT NULL,
    `Name` VARCHAR(26) NOT NULL,
    `Grade` VARCHAR(11) NOT NULL,
    `Hobbies` VARCHAR(25),
    `City` VARCHAR(10) NOT NULL,
    `School` VARCHAR(30) NOT NULL,
    `HomePhone` VARCHAR(15),
    `JobPhone` VARCHAR(15),
    `MobilePhone1` VARCHAR(15),
    `MobilePhone2` VARCHAR(15)
)  ENGINE=INNODB;

CREATE TABLE `Student`(
	`Id` DECIMAL(38,0) NOT NULL PRIMARY KEY,
	`Name` VARCHAR(26) NOT NULL
)ENGINE=INNODB;


LOAD DATA INFILE '/var/lib/mysql-files/denormalized-data.csv'
INTO TABLE UNF
CHARACTER SET latin1
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;


INSERT INTO Student (Id,Name) SELECT DISTINCT Id,Name FROM UNF;




CREATE TABLE School(
	SchoolId int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	School VARCHAR(40) NOT NULL,
	City VARCHAR(40) NOT NULL
) ENGINE=INNODB;


INSERT INTO School(School,City) SELECT DISTINCT School,City FROM UNF;






CREATE TABLE StudentSchool AS SELECT DISTINCT UNF.Id AS StudentId, School.SchoolId
FROM UNF JOIN School ON UNF.School = School.School;

ALTER TABLE StudentSchool MODIFY COLUMN StudentId INT;
ALTER TABLE StudentSchool ADD PRIMARY KEY(StudentId, SchoolId);




CREATE TABLE Phone(
	PhoneId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	StudentId INT NOT NULL,
	Type VARCHAR(32),
	Number VARCHAR(32) NOT NULL
)ENGINE=INNODB;


INSERT INTO Phone(StudentId,Type,Number)
SELECT Id AS StudentId, "Home" AS Type, HomePhone AS Number FROM UNF
WHERE HomePhone IS NOT NULL AND HomePhone != ''
UNION SELECT Id AS StudentId, "Job" AS Type, JobPhone AS Number FROM UNF
WHERE JobPhone IS NOT NULL AND JobPhone != ''
UNION SELECT ID As StudentId, "Mobile" AS Type, MobilePhone1 as Number FROM UNF
WHERE MobilePhone1 IS NOT NULL AND MobilePhone1 != ''
UNION SELECT ID As StudentId, "Mobile" AS Type, MobilePhone2 as Number FROM UNF
WHERE MobilePhone2 IS NOT NULL AND MobilePhone2 != '';




CREATE VIEW PhoneList AS SELECT StudentId, group_concat(Number) AS Numbers FROM Phone GROUP BY StudentId;



CREATE VIEW StudentHobby AS
SELECT Id AS StudentId, TRIM(SUBSTRING_INDEX(Hobbies, ',' , 1)) AS Hobby FROM UNF
UNION SELECT Id AS StudentId, TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(Hobbies, ',' ,-2), ',' ,1)) AS Hobby FROM UNF
UNION SELECT Id AS StudentId, TRIM(SUBSTRING_INDEX(Hobbies, ',' , -1)) AS Hobby FROM UNF;




CREATE TABLE Hobbies(
	Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Hobby VARCHAR(40) NOT NULL
)ENGINE=INNODB;



INSERT INTO Hobbies(Hobby) SELECT DISTINCT TRIM(Hobby) FROM StudentHobby WHERE Hobby != '';


CREATE TABLE Grade(
	Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Grade VARCHAR(30) NOT NULL
)ENGINE=INNODB;


INSERT INTO Grade(Grade) SELECT DISTINCT Grade FROM UNF WHERE Grade NOT IN ('eksellent','awessome','first-class','gorgetus');

