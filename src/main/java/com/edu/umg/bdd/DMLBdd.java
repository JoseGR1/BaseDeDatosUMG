/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.bdd;


import com.edu.umg.DTO.PersonaDTO;
import static com.edu.umg.bdd.ConnectionBdd.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilson Aguin
 */
public class DMLBdd {
    public void DMLBdd(){
        
    }
 //   static Connection connection = ConnectionBdd.getConnection();
    public static List<PersonaDTO> listaPersona() throws SQLException{
     List<PersonaDTO>list= new ArrayList<PersonaDTO>();
     Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
      
           try {
               connection=ConnectionBdd.getConnection();
                if(connection !=null){
                stmt=connection.createStatement();
               String query="Select *from persona";
                rs =stmt.executeQuery(query);
               while(rs.next()){
                   PersonaDTO per = new PersonaDTO();
                 per.setIdPersona(rs.getInt("id_persona"));
                 per.setNombre(rs.getString("nombre"));
                 per.setApellido(rs.getString("apellido"));
                 per.setTelefono(rs.getInt("telefono"));
                 per.setCorreo(rs.getString("correo"));
                 per.setEstado(rs.getInt("estado"));
                 list.add(per);
               }
                }
           } catch (Exception ex) {
               connection.close();
               System.out.println("Error al realizar la consulta.." + ex);
           }finally{
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (connection != null) connection.close();
           }
    
       
       
       return list;
   }
   
    public void agregarPersona(PersonaDTO pPersona){
        Connection connection = null;
        PreparedStatement pstm = null;
       
            try {
                connection = ConnectionBdd.getConnection(); // Obtén la conexión al inicio del método
             if(connection!=null){
               // Statement stmt = connection.createStatement();
                String insert="INSERT INTO persona (nombre, apellido, telefono, correo) values (?,?,?,?)";
                pstm=connection.prepareCall(insert);
                pstm.setString(1, pPersona.getNombre());
                pstm.setString(2, pPersona.getApellido());
                pstm.setInt(3, pPersona.getTelefono());
                pstm.setString(4, pPersona.getCorreo());
                int rowsInserted=pstm.executeUpdate();
                if(rowsInserted>0){
                    System.out.println("Se inserto el dato correctamente...");
                }
             }
            } catch (Exception ex) {
                System.out.println("error al ejecutar el insert. " + ex);
                
            }finally{
                 try {
                if (pstm != null) pstm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
public void eliminarPersona(int idPersona) throws SQLException {
    Connection connection = null;
    PreparedStatement pstm = null;
    
    try {
        connection = ConnectionBdd.getConnection(); // Establecer la conexión
        if (connection != null) {
            String deleteSQL = "DELETE FROM persona WHERE id_persona = ?";
            pstm = connection.prepareStatement(deleteSQL);
            pstm.setInt(1, idPersona); // Establecer el parámetro ID
            
            int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Persona eliminada correctamente.");
            } else {
                System.out.println("No se encontró la persona con ID: " + idPersona);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al eliminar persona: " + ex.getMessage());
        throw ex; // Re-lanza la excepción para manejarla en el Bean
    } finally {
        if (pstm != null) {
            pstm.close();
        }
        if (connection != null) {
            connection.close(); // Asegura que la conexión se cierra
        }
    }
}

public void actualizarPersona(PersonaDTO persona) throws SQLException {
    Connection connection = null;
    PreparedStatement pstm = null;
    
    try {
        connection = ConnectionBdd.getConnection(); // Establecer la conexión
        if (connection != null) {
            String updateSQL = "UPDATE persona SET nombre = ?, apellido = ?, telefono = ?, correo = ?, estado = ? WHERE id_persona = ?";
            pstm = connection.prepareStatement(updateSQL);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setInt(3, persona.getTelefono());
            pstm.setString(4, persona.getCorreo());
            pstm.setInt(5, persona.getEstado());
            pstm.setInt(6, persona.getIdPersona()); // Establecer el parámetro ID
            
            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Persona actualizada correctamente.");
            } else {
                System.out.println("No se encontró la persona con ID: " + persona.getIdPersona());
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al actualizar persona: " + ex.getMessage());
        throw ex; // Re-lanza la excepción para manejarla en el Bean
    } finally {
        if (pstm != null) {
            pstm.close();
        }
        if (connection != null) {
            connection.close(); // Asegura que la conexión se cierra
        }
    }
}

  
    
}
