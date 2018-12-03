/* tables *********************************************/

CREATE TABLE Coach(
	idcoach SERIAL NOT NULL,
	name VARCHAR(20) NOT NULL,
	PRIMARY KEY(idcoach)
);

CREATE TABLE Team(
	idteam SERIAL NOT NULL,
	idcoach INTEGER NOT NULL REFERENCES Coach,
	name VARCHAR(20) NOT NULL,
	college VARCHAR(20) NOT NULL,
	about VARCHAR(100),
	PRIMARY KEY (idteam)
);

CREATE TABLE Contestant(
	idcontestant SERIAL NOT NULL,
	idteam INTEGER NOT NULL REFERENCES Team,
	name VARCHAR(20) NOT NULL,
	nickname VARCHAR(20),
	PRIMARY KEY (idcontestant)
);

CREATE TABLE Problem(
	idproblem SERIAL NOT NULL,
	name VARCHAR(20) NOT NULL,
	description VARCHAR(500) NOT NULL,
	input VARCHAR(100) NOT NULL,
	output VARCHAR(100) NOT NULL,		
	timelimit INTEGER NOT NULL,
	PRIMARY KEY (idproblem)
);

CREATE TABLE Instance(
	idinstance SERIAL NOT NULL,
	idproblem INT NOT NULL REFERENCES Problem,
	input VARCHAR(1000000) NOT NULL,
	output VARCHAR(1000000) NOT NULL,
	blind BOOL,
	PRIMARY KEY(idinstance)
);

CREATE TABLE Report(
	idreport SERIAL NOT NULL,
	idproblem INT NOT NULL REFERENCES Problem,
	idteam INT NOT NULL REFERENCES Team,
	solved BOOL,
	penalty INT NOT NULL,
	PRIMARY KEY(idreport)
);

CREATE TABLE Submission(
	idsubmission SERIAL NOT NULL,
	idreport INT NOT NULL REFERENCES Report,
	time INT NOT NULL,
	duration INT NOT NULL,
	result VARCHAR(3) NOT NULL CHECK(result = 'ACC' 
								OR result = 'WA'
								OR result = 'TLE'
								OR result = 'RTE'
								OR result = 'CE'),
	solution VARCHAR(10000),
	PRIMARY KEY(idsubmission)
);

/* indexes ********************************************/

CREATE INDEX idx_instance_hash ON Instance USING HASH (idproblem);
CREATE INDEX idx_submission_btree ON Submission USING btree (idsubmission);

/* views **********************************************/

CREATE VIEW vw_ranking AS

SELECT t.name, 
(
	SELECT count(r.idteam) FROM Report r WHERE r.idteam = t.idteam AND r.solved = TRUE
) AS solved_problems,
(
	SELECT sum(r.penalty) FROM Report r WHERE r.idteam = t.idteam AND r.solved = TRUE
) AS total_penalty
FROM Team t
ORDER BY solved_problems DESC, total_penalty ASC;

CREATE VIEW vw_how_many_teams_solved_each_problem AS

SELECT p.name, count(t.idteam)
FROM Problem p, Team t
WHERE (SELECT r.solved FROM Report r WHERE r.idproblem = p.idproblem AND r.idteam = t.idteam)
GROUP BY p.name;

/* triggers *******************************************/

CREATE FUNCTION fn_submission_insert_trigger() RETURNS TRIGGER AS $fn_submission_insert_trigger$

DECLARE
	penalty_for_miss INTEGER := 20;

BEGIN
	-- if the problem wasn't solved previously
	IF NOT (SELECT r.solved FROM Report r WHERE r.idreport = NEW.idreport) THEN
	
		-- if it was solved now
		IF NEW.result = 'ACC' THEN
		
			-- set it to solved
			UPDATE Report r SET penalty = penalty + NEW.time WHERE r.idreport = NEW.idreport;
			
			-- update penalty with the time that the problem was solved
			UPDATE Report r SET solved = TRUE WHERE idreport = NEW.idreport;
			
		-- if it wasn't solved now
		ELSE
			-- update penalty
			UPDATE Report r SET penalty = penalty + penalty_for_miss WHERE r.idreport = NEW.idreport;
			
		END IF;
		
		
	END IF;
	

RETURN NEW;
END;

$fn_submission_insert_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER trg_submission_insert AFTER INSERT
ON Submission
FOR EACH ROW EXECUTE
PROCEDURE fn_submission_insert_trigger();

CREATE FUNCTION fn_report_insert_trigger() RETURNS TRIGGER AS $fn_report_insert_trigger$

DECLARE
	temp INTEGER;

BEGIN
	SELECT count(r.idreport) FROM Report r WHERE r.idproblem = NEW.idproblem AND r.idteam = NEW.idteam INTO temp;
	
	IF temp > 0 THEN
		RAISE EXCEPTION 'there must be at most one Report per team/problem pair!';
	END IF;

	

RETURN NEW;
END;

$fn_report_insert_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER trg_report_insert_trigger BEFORE INSERT
ON Report
FOR EACH ROW EXECUTE
PROCEDURE fn_report_insert_trigger();

CREATE FUNCTION fn_contestant_insert_trigger() RETURNS TRIGGER AS $fn_contestant_insert_trigger$

DECLARE
	contestants INTEGER;
	max_contestants INTEGER := 4;

BEGIN

	SELECT count(c.idcontestant) FROM Contestant c WHERE c.idteam = NEW.idteam INTO contestants;
	IF contestants = max_contestants THEN
		RAISE EXCEPTION 'the limit number of contestants was reached!';
	END IF;

	

RETURN NEW;
END;

$fn_contestant_insert_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER trg_contestant_insert_trigger BEFORE INSERT
ON Contestant
FOR EACH ROW EXECUTE
PROCEDURE fn_contestant_insert_trigger();
