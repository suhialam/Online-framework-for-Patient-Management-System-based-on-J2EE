/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author suhail
 */
public class SQLQueryUtil {
   
    private Connection connection;
    private Statement statement;
    
    
    public void connect(boolean autoCommit) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded.");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pms","root","");
            System.out.println("Connection established.");

            connection.setAutoCommit(autoCommit);
            statement = connection.createStatement();
            System.out.println("Statement Created.");
        } catch (Throwable th) {
            th.printStackTrace();
        }

    }
    

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    public void commit() {
        try {
            if (!(connection == null || connection.isClosed())) {
                connection.commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (!(connection == null || connection.isClosed())) {
                connection.rollback();
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        SQLQueryUtil sq = new SQLQueryUtil();
        sq.connect(true);
    }
}
