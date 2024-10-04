package com.edu.umg.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jose5
 */



public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
               // CargarPropiedades cargarPropiedades = new CargarPropiedades();
                
                
                // Configuración de Hibernate
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/claseprogra");
                configuration.setProperty("hibernate.connection.username","root");
                configuration.setProperty("hibernate.connection.password", "1234");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


                // Añadir la clase de entidad
                configuration.addAnnotatedClass(com.edu.umg.DTO.PersonaDTO.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
            }
        }
    }
}

