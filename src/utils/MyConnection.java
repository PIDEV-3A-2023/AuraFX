/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author belkn
 */
import java.sql.*;

public class MyConnection {

    static MyConnection instance;
    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/javafx23";
    String user = "root";
    String pwd = "";

    private MyConnection() {

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("CONNEXION ETABLIE");
        } catch (SQLException ex) {
            System.out.println("PROBLEME DE CONNEXION");
        }
    }

    public static MyConnection getInstance() {

        if (instance == null) {

            instance = new MyConnection();
        }
        return instance;

    }

    public Connection getConn() {
        return conn;
    }

}
