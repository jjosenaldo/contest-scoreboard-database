CREATE OR REPLACE FUNCTION fn_report_insert_trigger() RETURNS TRIGGER AS $fn_report_insert_trigger$

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

DROP TRIGGER trg_report_insert_trigger ON Report; 
CREATE TRIGGER trg_report_insert_trigger BEFORE INSERT
ON Report
FOR EACH ROW EXECUTE
PROCEDURE fn_report_insert_trigger();
