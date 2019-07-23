DROP VIEW IF EXISTS vw_ranking;

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
