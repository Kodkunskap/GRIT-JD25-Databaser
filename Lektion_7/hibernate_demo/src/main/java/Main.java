import model.Contact;
import org.hibernate.Session;
import util.HibernateUtil;

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

    public static void main(String[] args) {
        exampleWithoutDAO();
    }

}
