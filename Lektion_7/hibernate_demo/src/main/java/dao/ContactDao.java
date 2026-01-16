package dao;

import model.Contact;
import org.hibernate.*;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class ContactDao {

    // C: Create
    public void saveContact(Contact contact) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Save the Contact to the database
            transaction = session.beginTransaction();
            session.persist(contact);
            transaction.commit();

        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // U: Update
    public void updateContact(Contact c) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(c);
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // R: Read (id)
    public Contact getContact(int id) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Contact contact = session.getReference(
                    Contact.class,
                    id
            );
            return contact;
        }
    }

    // R: Read by name
    public Contact getContactByName(String name){

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Use HQL to adjust the Query to the database
            Query query = session.createQuery("select c from Contact c where c.name = :name", Contact.class);
            query.setParameter("name", name);
            List<Contact> contacts = query.list();
            if(!contacts.isEmpty()){
                return contacts.getFirst();
            }
            return null;
        }

    }

    // R: Read (all)
    public List<Contact> getContacts(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Contact", Contact.class).list();
        }
    }

    // D: Delete
    public void deleteContact(Contact c) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(c);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
