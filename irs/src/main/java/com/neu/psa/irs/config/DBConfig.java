/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vineet
 */
public class DBConfig {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE = "irs";

    public Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return connection;
        }

    }

    public ResultSet executePreparedQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    public int executePreparedUpdate(Connection conn, PreparedStatement sql) throws SQLException {
        
        int result = -1;
        
        try {
            result = sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
