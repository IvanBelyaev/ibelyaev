package ru.job4j.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * BookHbm.
 */
public class BookHbm {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try (SessionFactory sf = configuration.configure("book.cfg.xml").buildSessionFactory()) {
            final Session session = sf.openSession();
            final Transaction tx = session.beginTransaction();
            try {
                Author bernard = Author.of("Bernard");
                Author lewis = Author.of("Lewis");

                Book firstBook = Book.of("first book");
                firstBook.addAuthor(bernard);
                firstBook.addAuthor(lewis);

                Book secondBook = Book.of("second book");
                secondBook.addAuthor(bernard);

                session.persist(firstBook);
                session.persist(secondBook);
                Book book = session.get(Book.class, firstBook.getId());
                session.remove(book);
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
