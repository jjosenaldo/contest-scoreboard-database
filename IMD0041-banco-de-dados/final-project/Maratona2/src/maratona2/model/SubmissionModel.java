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
import maratona2.domain.Submission;

/**
 *
 * @author josenaldo
 */
public class SubmissionModel extends AbstractModel
{
    public SubmissionModel()
    {
        super("INSERT INTO Submission(idreport, time, duration, result, solution) VALUES (?, ?, ?, ?, ?)",
                "SELECT idsubmission, idreport, time, duration, result, solution FROM Submission",
                "UPDATE Submission SET idreport = ?, time = ?, duration = ?, result= ?, solution = ? WHERE idsubmission = ?",
                "DELETE FROM Submission WHERE idsubmission = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Submission submission = (Submission)entity;
        statement.setInt(1, submission.getIdReport());
        statement.setInt(2, submission.getTime());
        statement.setInt(3, submission.getDuration());
        statement.setString(4, submission.getResult());
        statement.setString(5, submission.getSolution());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(5, ((Submission)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idsubmission");
        int report = resultSet.getInt("idreport");
        int time = resultSet.getInt("time");
        int duration = resultSet.getInt("duration");
        String result = resultSet.getString("result");
        String solution = resultSet.getString("solution");
        
        Submission submission = new Submission(id, report, time, duration, result, solution);
        list.add(submission);
    }
}
