/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author josenaldo
 */
public class DBUtils
{   
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DBConstants.URL, DBConstants.USER, DBConstants.PASSWORD);
    }
    
    public static void closeStatement(Statement statement)
    {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public static void closeConnection(Connection connection)
    {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
}
