CREATE TABLE Contestant(
	idcontestant SERIAL NOT NULL,
	idteam INTEGER NOT NULL REFERENCES Team,
	name VARCHAR(20) NOT NULL,
	nickname VARCHAR(20),
	PRIMARY KEY (idcontestant)
);
