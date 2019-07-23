DROP VIEW IF EXISTS vw_how_many_teams_solved_each_problem;

CREATE VIEW vw_how_many_teams_solved_each_problem AS

SELECT p.name, count(t.idteam)
FROM Problem p, Team t
WHERE (SELECT r.solved FROM Report r WHERE r.idproblem = p.idproblem AND r.idteam = t.idteam)
GROUP BY p.name;
