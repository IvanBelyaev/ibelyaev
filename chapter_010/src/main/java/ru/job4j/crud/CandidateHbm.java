package ru.job4j.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CandidateHbm {
    private final Configuration configuration = new Configuration();
    private final SessionFactory sf = configuration.configure("test.cfg.xml").buildSessionFactory();

    public static void main(String[] args) {
        CandidateHbm hbm = new CandidateHbm();
        Candidate first = Candidate.of("Sergey", 5, 100);
        Candidate second = Candidate.of("Oleg", 3, 80);
        Candidate third = Candidate.of("Oleg", 7, 120);
        hbm.saveCandidate(first);
        hbm.saveCandidate(second);
        hbm.saveCandidate(third);

        List<Candidate> allCandidates = hbm.getAllCandidates();
        System.out.println("All candidates:");
        for (Candidate candidate : allCandidates) {
            System.out.println(candidate);
        }

        Candidate candidateById = hbm.getCandidateById(first.getId());
        System.out.printf("Candidate with id equal to %d:%n", first.getId());
        System.out.println(candidateById);

        List<Candidate> candidatesWithNameOleg = hbm.getCandidatesByName("Oleg");
        System.out.println("Candidates with the name Oleg:");
        for (Candidate candidate : candidatesWithNameOleg) {
            System.out.println(candidate);
        }

        Candidate newFirst = Candidate.of("Gleb", 8, 150);
        newFirst.setId(first.getId());
        hbm.updateCandidate(newFirst);
        allCandidates = hbm.getAllCandidates();
        System.out.printf("After update candidate with id = %d:%n", first.getId());
        for (Candidate candidate : allCandidates) {
            System.out.println(candidate);
        }

        hbm.deleteCandidateById(first.getId());
        allCandidates = hbm.getAllCandidates();
        System.out.printf("After delete candidate with id = %d:%n", first.getId());
        for (Candidate candidate : allCandidates) {
            System.out.println(candidate);
        }

        hbm.deleteAllCandidates();
        allCandidates = hbm.getAllCandidates();
        System.out.println("After delete all candidates:");
        for (Candidate candidate : allCandidates) {
            System.out.println(candidate);
        }
    }

    public void saveCandidate(Candidate candidate) {
        txConsumer(
                session -> session.save(candidate)
        );
    }

    public List<Candidate> getAllCandidates() {
        return txFunction(
                session -> session.createQuery("from Candidate ").list()
        );
    }

    public Candidate getCandidateById(int id) {
        return txFunction(
                session -> session.find(Candidate.class, id)
        );
    }

    public List<Candidate> getCandidatesByName(String name) {
        return txFunction(
                session ->
                        session.createQuery("from Candidate c where c.name = :name")
                                .setParameter("name", name)
                                .list()
        );
    }

    public void deleteCandidateById(int id) {
        txConsumer(
                session -> session.createQuery("delete from Candidate c where c.id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    public void deleteAllCandidates() {
        txConsumer(
                session -> session.createQuery("delete from Candidate").executeUpdate()
        );
    }

    public void updateCandidate(Candidate candidate) {
        txConsumer(
                session ->
                        session.createQuery("update Candidate c set c.name = :newName, c.experience = :newExperience, c.salary = :newSalary where c.id = :id")
                                .setParameter("id", candidate.getId())
                                .setParameter("newName", candidate.getName())
                                .setParameter("newExperience", candidate.getExperience())
                                .setParameter("newSalary", candidate.getSalary())
                                .executeUpdate()
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
