/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson Aguin
 */
public class ConnectionBdd {
    private static final String URL = "jdbc:mysql://localhost:3306/claseprogra";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    public void ConnectionBdd(){
        
    }
    
    public static Connection getConnection() {
        Connection connection = null;
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
           //  conn.close();
           e.printStackTrace();
               System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Driver no encontrado: " + ex.getMessage());
        }
          
        
        return connection;
    }
    
    public static void main(String[] args) {
        // Test de la conexión
        Connection conn = getConnection();
        System.out.println("la conexion ex: " + conn);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
