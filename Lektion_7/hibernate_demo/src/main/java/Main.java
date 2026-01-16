import dao.ContactDao;
import dao.GenericDao;
import model.Contact;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class Main {

    private static void exampleWithoutDAO(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Contact c = new Contact();
        c.setName("Martin");
        c.setEmail("martin.haagen@gritacademy.se");
        c.setPet("Boss");

        session.persist(c);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    private static void exampleWithDAO(){

        ContactDao contactDao = new ContactDao();

        /*
            Create a brand new Contact
         */
        Contact contact = new Contact();
        contact.setName("Hibernate Martin 2");
        contact.setEmail("unique2@email.com");
        contactDao.saveContact(contact);

        /*
            Fetch a list of all the Contacts
         */
        List<Contact> contactList = contactDao.getContacts();
        contactList.forEach(c -> {
            System.out.println(c);
        });

        /*
            Load a Contact by name and delete it
         */
        /*
        contact = contactDao.getContactByName("Hibernate Martin");
        System.out.println(contact);
        contactDao.deleteContact(contact);
        */

        /*
            Load a Contact by name and update it
         */
        /*
        contact = contactDao.getContactByName("Hibernate Martin 2");
        contact.setEmail("martin22@email.com");
        contactDao.updateContact(contact);
         */

    }

    public static void genericDAOExample() {
        GenericDao<Contact> cdao = new GenericDao<>(Contact.class);
        Contact contact = cdao.getByName("Hibernate Martin 2");
        contact.setEmail("martin67@email.com");
        cdao.update(contact);
    }

    public static void main(String[] args) {
        // exampleWithoutDAO();
        // exampleWithDAO();
        genericDAOExample();
    }

}
