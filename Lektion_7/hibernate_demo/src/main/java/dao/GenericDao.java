package dao;

import org.hibernate.*;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class GenericDao<T> {

    private final Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    // C: Create
    public void save(T obj) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Save the Contact to the database
            transaction = session.beginTransaction();
            session.persist(obj);
            transaction.commit();

        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // U: Update
    public void update(T obj) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(obj);
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // R: Read (id)
    public T get(int id) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.getReference(
                    type,
                    id
            );
        }
    }

    // R: Read by name
    public T getByName(String name){

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select o from " + type.getName() + " o where o.name = :name";
            Query query = session.createQuery(hql, type);
            query.setParameter("name", name);
            List<T> objects = query.list();
            if(!objects.isEmpty()){
                return objects.getFirst();
            }
            return null;
        }

    }

    // R: Read (all)
    public List<T> get(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from " + type.getName();
            return session.createQuery(hql, type).list();
        }
    }

    // D: Delete
    public void delete(T obj) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(obj);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
