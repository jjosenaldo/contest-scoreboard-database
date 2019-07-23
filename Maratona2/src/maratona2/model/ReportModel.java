/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import maratona2.domain.Entity;
import maratona2.domain.Report;

/**
 *
 * @author josenaldo
 */
public class ReportModel extends AbstractModel
{
    public ReportModel()
    {
        super("INSERT INTO Report(idproblem, idteam, solved, penalty) VALUES (?, ?, ?, ?)",
                "SELECT idreport, idproblem, idteam, solved, penalty FROM Report",
                "UPDATE Report SET idproblem = ?, idteam = ?, solved = ?, penalty = ? WHERE idreport = ?",
                "DELETE FROM Report WHERE idreport = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Report report = (Report)entity;
        statement.setInt(1, report.getIdProblem());
        statement.setInt(2, report.getIdTeam());
        statement.setBoolean(3, report.isSolved());
        statement.setInt(4, report.getPenalty());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(5, ((Report)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idreport");
        int problem = resultSet.getInt("idproblem");
        int team = resultSet.getInt("idteam");
        boolean solved = resultSet.getBoolean("solved");
        int penalty = resultSet.getInt("penalty");
        
        Report report = new Report(id, problem, team, solved, penalty);
        list.add(report);
    }
}
