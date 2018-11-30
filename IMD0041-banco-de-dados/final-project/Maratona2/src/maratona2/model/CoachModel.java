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
    
    public CoachModel(String sql_insert, String sql_find_all, String sql_update, String sql_delete)
    {
        super(sql_insert, sql_find_all, sql_update, sql_delete);
    }
    
    @Override
    protected void setPreparedStatementParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setString(1, ((Coach)entity).getName());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        Coach coach = new Coach(resultSet.getInt("idcoach"), resultSet.getString("name"));
        list.add(coach);
    }
    
    public void update(Coach coach)
    {
        System.out.println("Coach updated!");
    }
}
