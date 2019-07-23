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
import maratona2.domain.Instance;

/**
 *
 * @author josenaldo
 */
public class InstanceModel extends AbstractModel
{
    public InstanceModel()
    {
        super("INSERT INTO Instance(idproblem, input, output, blind) VALUES (?, ?, ?, ?)",
                "SELECT idinstance, idproblem, input, output, blind FROM Instance",
                "UPDATE Instance SET idproblem = ?, input = ?, output = ?, blind = ? WHERE idinstance = ?",
                "DELETE FROM Instance WHERE idinstance = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Instance instance = (Instance)entity;
        statement.setInt(1, instance.getIdProblem());
        statement.setString(2, instance.getInput());
        statement.setString(3, instance.getOutput());
        statement.setBoolean(4, instance.isBlind());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(5, ((Instance)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idinstance");
        int problem = resultSet.getInt("idproblem");
        String input = resultSet.getString("input");
        String output = resultSet.getString("output");
        boolean blind = resultSet.getBoolean("blind");
        Instance instance = new Instance(id, problem, input, output, blind);
        list.add(instance);
    }
}
