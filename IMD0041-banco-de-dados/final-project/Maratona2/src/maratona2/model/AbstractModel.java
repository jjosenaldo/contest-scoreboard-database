/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maratona2.domain.Entity;
import maratona2.utils.DBUtils;
import org.postgresql.util.PSQLException;

/**
 *
 * @author josenaldo
 */
public abstract class AbstractModel
{
    protected String sqlInsert;
    protected String sqlFindAll;
    protected String sqlUpdate;
    protected String sqlDelete;
    
    protected Connection connection;
    
    public AbstractModel(String sql_insert, String sql_find_all, String sql_update, String sql_delete)
    {
        this.sqlInsert = sql_insert;
        this.sqlFindAll = sql_find_all;
        this.sqlUpdate = sql_update;
        this.sqlDelete = sql_delete;
    }
    
    protected void setConnection() throws SQLException
    {
        if(this.connection == null || this.connection.isClosed())
            this.connection = DBUtils.getConnection();
            
    }
    
    protected PreparedStatement prepareStatement(String command, int key) throws SQLException
    {
        this.setConnection();
        
        return this.connection.prepareStatement(command, key);
    }
    
    protected PreparedStatement prepareStatement(String command) throws SQLException
    {
        this.setConnection();
        
        return this.connection.prepareStatement(command);
    }  
    
    public void delete(int id) throws SQLException
    {
        PreparedStatement statement = null;
        
        try
        {
            statement = this.prepareStatement(this.sqlDelete);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        finally
        {
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(this.connection);
        }
    }
    
    protected Statement createStatement() throws SQLException
    {
        this.setConnection();
        
        return this.connection.createStatement();
    }
    
    public int insert(Entity entity) throws SQLException, SQLException
    {
        PreparedStatement statement = null;
        
        try
        {
            statement = this.prepareStatement(this.sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            this.setPreparedStatementInsertParams(statement, entity);
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        
        catch (PSQLException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        finally
        {
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(this.connection);
        }
    }
    
    public void update(Entity entity) throws PSQLException, SQLException
    {
        PreparedStatement statement = null;
        
        try
        {
            statement = this.prepareStatement(this.sqlUpdate);
            this.setPreparedStatementUpdateParams(statement, entity);
            
            statement.executeUpdate();
        }
        
        catch (PSQLException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        finally
        {
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(this.connection);
        }
        
    }
    
    public List<Entity> selectAll() throws SQLException
    {
        List<Entity> resultList = new ArrayList<>();
        
        Statement statement = this.createStatement();
        try (ResultSet resultSet = statement.executeQuery(this.sqlFindAll))
        {
            while(resultSet.next())
            {
                this.addEntityToList(resultSet, resultList);
            }
        }
        
        this.connection.close();
        
        return resultList;
    }
    
    protected void setPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        this.setPreparedStatementInsertParams(statement, entity);
        this.setIdInPreparedStatementUpdateParams(statement, entity);
    }
    
    protected abstract void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException;
    
    protected abstract void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException;
    
    protected abstract void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException;
}


