/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataAccess;

import Connection.BDConnection;
import Domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Castillo Vindas / jorge.castillovindas@ucr.ac.cr
 */
public class ClienteData {

    public ClienteData() {
    }
    
    public void insertarCliente(Cliente cliente){
        
        BDConnection bDConnection = BDConnection.getInstance();
        try {
            PreparedStatement statement = bDConnection.openConnection().prepareStatement("Insert into cliente (nombre_cliente, apellido_cliente, correo_cliente) values(?,?,?)");
            statement.setString(1, cliente.getNombreCliente());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCorreo());
            statement.executeUpdate();
            bDConnection.closeConnection();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
