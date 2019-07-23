CREATE TABLE Problem(
	idproblem SERIAL NOT NULL,
	name VARCHAR(20) NOT NULL,
	description VARCHAR(500) NOT NULL,
	input VARCHAR(100) NOT NULL,
	output VARCHAR(100) NOT NULL,		
	timelimit INTEGER NOT NULL,
	PRIMARY KEY (idproblem)
);
