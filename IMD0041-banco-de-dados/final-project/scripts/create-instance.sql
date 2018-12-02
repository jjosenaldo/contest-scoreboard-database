CREATE TABLE Instance(
	idinstance SERIAL NOT NULL,
	idproblem INT NOT NULL REFERENCES Problem,
	input VARCHAR(1000000) NOT NULL,
	output VARCHAR(1000000) NOT NULL,
	blind BOOL,
	PRIMARY KEY(idinstance)
);
