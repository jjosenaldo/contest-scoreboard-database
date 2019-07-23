CREATE OR REPLACE FUNCTION fn_contestant_insert_trigger() RETURNS TRIGGER AS $fn_contestant_insert_trigger$

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

DROP TRIGGER IF EXISTS trg_contestant_insert_trigger ON Contestant; 
CREATE TRIGGER trg_contestant_insert_trigger BEFORE INSERT
ON Contestant
FOR EACH ROW EXECUTE
PROCEDURE fn_contestant_insert_trigger();
