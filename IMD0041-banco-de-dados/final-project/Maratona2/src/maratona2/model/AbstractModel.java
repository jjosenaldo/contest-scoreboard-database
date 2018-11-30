/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import maratona2.domain.Entity;
import maratona2.utils.DBUtils;

/**
 *
 * @author josenaldo
 */
public abstract class AbstractModel
{
    protected String sql_insert;
    
    protected Connection connection;
    
    public AbstractModel(String sql_insert)
    {
        this.sql_insert = sql_insert;
    }
    
    protected void setConnection() throws SQLException
    {
        if(this.connection == null || this.connection.isClosed())
            this.connection = DBUtils.getConnection();
    }
    
    protected PreparedStatement prepareStatement(String command) throws SQLException
    {
        this.setConnection();
        
        return this.connection.prepareStatement(command);
    }  
    
    public void insert(Entity entity) throws SQLException
    {
        PreparedStatement statement = this.prepareStatement(this.sql_insert);
        this.setStatementParams(statement, entity);
        statement.execute();
        
        this.connection.close();
    }
    
    protected abstract void setStatementParams(PreparedStatement statement, Entity entity) throws SQLException;
}


