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
import maratona2.domain.Problem;

/**
 *
 * @author josenaldo
 */ 
public class ProblemModel extends AbstractModel
{
    public ProblemModel()
    {
        super("INSERT INTO Problem(name, description, input, output, timelimit) VALUES (?, ?, ?, ?, ?)",
                "SELECT idproblem, name, description, input, output, timelimit FROM Problem",
                "UPDATE Problem SET name = ?, description = ?, input = ?, output = ? WHERE idproblem = ?",
                "DELETE FROM Problem WHERE idproblem = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Problem problem = (Problem)entity;
        statement.setString(1, problem.getName());
        statement.setString(2, problem.getDescription());
        statement.setString(3, problem.getInput());
        statement.setString(4, problem.getOutput());
        statement.setInt(5, problem.getTimelimit());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(5, ((Problem)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idproblem");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String input = resultSet.getString("input");
        String output = resultSet.getString("output");
        int timelimit = resultSet.getInt("timelimit");
        Problem problem = new Problem(id, name, description, input, output, timelimit);
        list.add(problem);
    }
}
