/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josenaldo
 */
public class DBUtils
{
    public static final String URL = "jdbc:postgresql://localhost/db_final_project";
    public static final String USER = "pg_josenaldo";
    public static final String PASSWORD = "1234";
    
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASSWORD);
    }
}
