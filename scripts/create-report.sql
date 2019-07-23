CREATE TABLE Report(
	idreport SERIAL NOT NULL,
	idproblem INT NOT NULL REFERENCES Problem,
	idteam INT NOT NULL REFERENCES Team,
	solved BOOL,
	penalty INT NOT NULL,
	PRIMARY KEY(idreport)
);
