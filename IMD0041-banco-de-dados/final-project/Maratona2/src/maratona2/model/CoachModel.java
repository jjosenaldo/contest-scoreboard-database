/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import maratona2.domain.Coach;
import maratona2.utils.DBUtils;

/**
 *
 * @author josenaldo
 */
public class CoachModel {
    private Connection connection;
    
    public void insert(Coach newCoach) throws SQLException
    {
        String sql = "INSERT INTO Coach (name) VALUES (?)";
        PreparedStatement statement = this.prepareStatement(sql);
        
        statement.setString(1, newCoach.getName());
        statement.execute();
        
        this.connection.close();
    }
    
    public void update(Coach coach)
    {
        System.out.println("Coach updated!");
    }
    
    public void delete(Coach coach)
    {
        System.out.println("Coach deleted!");
    }
    
    private void setConnection() throws SQLException
    {
        if(this.connection == null || this.connection.isClosed())
            this.connection = DBUtils.getConnection();
    }
    
    private PreparedStatement prepareStatement(String command) throws SQLException
    {
        this.setConnection();
        
        return this.connection.prepareStatement(command);
    }   
}
