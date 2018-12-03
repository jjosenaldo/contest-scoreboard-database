DROP INDEX IF EXISTS idx_instance_hash;
CREATE INDEX idx_instance_hash ON Instance USING HASH (idproblem);
