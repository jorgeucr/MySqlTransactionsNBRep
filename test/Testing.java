/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataAccess.ClienteData;
import Domain.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge Castillo Vindas/jorge.castillovindas@ucr.ac.cr
 */
public class Testing {
    
    public Testing() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         Cliente cliente = new Cliente();
         cliente.setNombreCliente("Jorge");
         
         Cliente cliente2 = new Cliente();
         cliente.setNombreCliente("Juan");
     
         new ClienteData().insertarCliente(cliente);
         new ClienteData().insertarCliente(cliente2);
     }
}
