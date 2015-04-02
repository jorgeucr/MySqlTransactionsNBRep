/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Connection.BDConnection;
import Domain.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Castillo Vindas / jorge.castillovindas@ucr.ac.cr
 */
public class ClienteData {

    public ClienteData() {
    }

    public void insertarCliente(Cliente cliente) {

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

    public void insertarClienteSP(Cliente clienteInsertar) {

        try {
            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
            BDConnection bDConnection = BDConnection.getInstance();
            CallableStatement callableStatement = bDConnection.openConnection().prepareCall(" CALL sp_insertarCliente(?,?,?,?)");
            // parametros de salida
            callableStatement.registerOutParameter("id", Types.INTEGER);//Tipo String
            //se cargan los parametros de entrada
            callableStatement.setString("nombre", clienteInsertar.getNombreCliente());
            callableStatement.setString("apellido", clienteInsertar.getApellido());
            callableStatement.setString("correo", clienteInsertar.getCorreo());

            // Se ejecuta el procedimiento almacenado
            callableStatement.execute();
            // devuelve el valor del parametro de salida del procedimiento
            clienteInsertar.setId(Integer.parseInt(callableStatement.getString("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
