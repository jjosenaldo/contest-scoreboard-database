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
import maratona2.domain.Coach;
import maratona2.domain.Entity;

/**
 *
 * @author josenaldo
 */
public class CoachModel extends AbstractModel{
    public CoachModel()
    {
        super("INSERT INTO Coach (name) VALUES (?)",
                "SELECT idcoach, name FROM coach",
                "UPDATE Coach SET name = ? WHERE idcoach = ?",
                "DELETE FROM Coach WHERE idcoach = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setString(1, ((Coach)entity).getName());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(2, ((Coach)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        Coach coach = new Coach(resultSet.getInt("idcoach"), resultSet.getString("name"));
        list.add(coach);
    }
}
