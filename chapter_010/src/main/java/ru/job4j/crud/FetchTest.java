package ru.job4j.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class FetchTest {
    private final Configuration configuration = new Configuration();
    private final SessionFactory sf = configuration.configure("test.cfg.xml").buildSessionFactory();

    public static void main(String[] args) {
        FetchTest test = new FetchTest();
        Candidate candidate = Candidate.of("Sergey", 5, 100);
        VacanciesDB db = VacanciesDB.of("hh.ru");
        candidate.setDatabase(db);
        Vacancy junior = Vacancy.of("Java Junior");
        Vacancy middle = Vacancy.of("Java Middle");
        db.setVacancies(Arrays.asList(junior, middle));
        test.save(db);
        test.save(candidate);

        System.out.println(test.getCandidateById(candidate.getId()));

        test.delete(candidate);
        test.delete(db);
    }

    public <T> void save(T item) {
        txConsumer(
                session -> session.save(item)
        );
    }

    public Candidate getCandidateById(int id) {
        return txFunction(
               session -> session.createQuery(
                       "select c from Candidate c join fetch c.database d join fetch d.vacancies where c.id = :id",
                       Candidate.class)
                       .setParameter("id", id)
                       .uniqueResult()
        );
    }

    public <T> void delete(T item) {
        txConsumer(
                session -> session.remove(item)
        );
    }

    private <T> T txFunction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void txConsumer(Consumer<Session> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
