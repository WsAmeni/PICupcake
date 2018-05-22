/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jihed
 */
public class DataSource {

        String url="jdbc:mysql://127.0.0.1/pidev";
        String login="root";
        String password="";
        private Connection con;
        private static DataSource data;

    private DataSource() {

        try {
            con = DriverManager.getConnection(url, login, password);
            System.out.println("DataBase Connected");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
  
    public Connection getConnection() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }

}
