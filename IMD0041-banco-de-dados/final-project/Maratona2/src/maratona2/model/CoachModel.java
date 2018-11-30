/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import maratona2.domain.Coach;
import maratona2.domain.Entity;

/**
 *
 * @author josenaldo
 */
public class CoachModel extends AbstractModel{
    
    public CoachModel(String sql_insert)
    {
        super(sql_insert);
    }
    
    @Override
    protected void setStatementParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setString(1, ((Coach)entity).getName());
    }
    
    public void update(Coach coach)
    {
        System.out.println("Coach updated!");
    }
    
    public void delete(Coach coach)
    {
        System.out.println("Coach deleted!");
    }
    
}
