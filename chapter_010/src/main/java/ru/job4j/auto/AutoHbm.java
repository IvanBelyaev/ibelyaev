package ru.job4j.auto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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
        List<Brand> list = new ArrayList<>();
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

                list = session.createQuery(
                        "select distinct b from Brand b join fetch b.models"
                ).list();
                tx.commit();
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            } finally {
                session.close();
            }

            for (Brand brand : list) {
                for (Model model : brand.getModels()) {
                    System.out.println(model);
                }
            }
        }
    }
}
