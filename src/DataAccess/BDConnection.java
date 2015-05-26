/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jorge Castillo Vindas / jorge.castillovindas@ucr.ac.cr
 */
public class BDConnection {

    private static BDConnection bdConnection;
    private Connection connection = null;

    private BDConnection() {

    }

    public static BDConnection getInstance() {

        if (bdConnection == null) {
            bdConnection = new BDConnection();
        }
        return bdConnection;
    }

    public Connection openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
