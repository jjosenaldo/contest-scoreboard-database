CREATE OR REPLACE FUNCTION fn_submission_insert_trigger() RETURNS TRIGGER AS $fn_submission_insert_trigger$

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

DROP TRIGGER IF EXISTS trg_submission_insert ON Submission; 
CREATE TRIGGER trg_submission_insert AFTER INSERT
ON Submission
FOR EACH ROW EXECUTE
PROCEDURE fn_submission_insert_trigger();
