package com.edu.umg.DAO;

import com.edu.umg.DTO.PersonaDTO;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PersonaDAO {

    // Método para listar todas las personas
    public List<PersonaDTO> listaPersona() {
        List<PersonaDTO> list = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Usamos HQL (Hibernate Query Language) para consultar las personas
            list = session.createQuery("FROM PersonaDTO", PersonaDTO.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    // Método para agregar una persona
    public void agregarPersona(PersonaDTO persona) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(persona); // Hibernate se encarga de generar el INSERT
            transaction.commit();
            System.out.println("Persona agregada correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar una persona por ID
    public void eliminarPersona(int idPersona) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PersonaDTO persona = session.get(PersonaDTO.class, idPersona);
            if (persona != null) {
                session.delete(persona); // Hibernate se encarga de generar el DELETE
                System.out.println("Persona eliminada correctamente.");
            } else {
                System.out.println("No se encontró la persona con ID: " + idPersona);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para actualizar una persona
    public void actualizarPersona(PersonaDTO persona) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(persona); // Hibernate se encarga de generar el UPDATE
            transaction.commit();
            System.out.println("Persona actualizada correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
