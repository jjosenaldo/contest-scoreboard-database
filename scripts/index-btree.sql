DROP INDEX IF EXISTS idx_submission_btree;
CREATE INDEX idx_submission_btree ON Submission USING btree (idsubmission);
