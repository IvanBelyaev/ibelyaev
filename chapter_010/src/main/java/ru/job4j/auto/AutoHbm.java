package ru.job4j.auto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * AutoHbm.
 */
public class AutoHbm {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try (SessionFactory sf = configuration.configure("auto.cfg.xml").buildSessionFactory()) {
            final Session session = sf.openSession();
            final Transaction tx = session.beginTransaction();
            try {
                Brand brand = Brand.of("mazda");
                brand.addModel(Model.of("model 1"));
                brand.addModel(Model.of("model 2"));
                brand.addModel(Model.of("model 3"));
                brand.addModel(Model.of("model 4"));
                brand.addModel(Model.of("model 5"));
                session.save(brand);
                tx.commit();
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            } finally {
                session.close();
            }
        }
    }
}
