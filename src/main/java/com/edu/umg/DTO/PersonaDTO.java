package com.edu.umg.DTO;

import javax.persistence.*;

@Entity
@Table(name = "persona")  // Especifica el nombre de la tabla en la base de datos
public class PersonaDTO {

    @Id
    @Column(name = "id_persona")  // Mapea el campo "id_persona" de la tabla
    private int idPersona;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;
    private int estado;

    // Constructor vacío requerido por Hibernate
    public PersonaDTO() {}

    // Getters y setters

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
