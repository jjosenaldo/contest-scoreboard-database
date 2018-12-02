CREATE TABLE Submission(
	idsubmission SERIAL NOT NULL,
	idreport INT NOT NULL REFERENCES Report,
	time INT NOT NULL,
	duration INT NOT NULL,
	result VARCHAR(3) NOT NULL CHECK(result = 'ACC' 
								OR result = 'WA'
								OR result = 'TLE'
								OR result = 'CE'),
	solution VARCHAR(10000),
	PRIMARY KEY(idsubmission)
);
