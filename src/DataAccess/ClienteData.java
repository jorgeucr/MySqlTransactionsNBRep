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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
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

    public LinkedList<Cliente> getClientes() {
        LinkedList<Cliente> clientes = new LinkedList<Cliente>();
        BDConnection bDConnection = BDConnection.getInstance();
        try {
            Statement statement = bDConnection.openConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cliente");
            while (resultSet.next()) {                
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt(1));
                cliente.setNombreCliente(resultSet.getString(2));
                cliente.setApellido(resultSet.getString(3));
                cliente.setCorreo(resultSet.getString(4));
                clientes.addLast(cliente);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }finally{
            try {
                bDConnection.closeConnection();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
        return clientes;
    }
}
