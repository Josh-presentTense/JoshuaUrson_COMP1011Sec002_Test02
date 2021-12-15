CREATE DATABASE test02comp1011;

USE test02comp1011;

CREATE TABLE players (
	playerID 		int NOT NULL AUTO_INCREMENT,
	firstName 		nvarchar(50) NOT NULL,
    lastName 		nvarchar(50) NOT NULL,
    battingAverage 	DECIMAL(10,3) NOT NULL,
	PRIMARY KEY (playerID)
);

INSERT INTO players (firstName, lastName, battingAverage) VALUES ("Joe", "DiMaggio", 0.325);
INSERT INTO players (firstName, lastName, battingAverage) VALUES ("Keith", "Hernandez", 0.296);
INSERT INTO players (firstName, lastName, battingAverage) VALUES ("Babe", "Ruth", 0.342);
INSERT INTO players (firstName, lastName, battingAverage) VALUES ("Lou", "Gehrig", 0.340);
INSERT INTO players (firstName, lastName, battingAverage) VALUES ("Willie", "Mays", 0.302);

SELECT * FROM players;

SELECT * 
FROM players
WHERE firstName = "Babe";

SELECT * 
FROM players
WHERE playerID = 1;