package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if(sessionFactory == null) {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

                Metadata metaData = new MetadataSources(registry)
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metaData.buildSessionFactory();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}
