package ru.job4j.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * EmailNotification.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.12.2020
 */
public class EmailNotification {
    /** Pool threads. */
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Sends a notification to the user via email.
     * Can send multiple notifications in concurrency using a thread pool.
     * @param user the user to whom the email is sent.
     */
    public void emailTo(User user) {
        String email = user.getEmail();
        String subject = String.format("Notification %s to email %s", user.getName(), email);
        String body = String.format("Add a new event to %s", email);
        pool.execute(() -> send(subject, body, email));
    }

    /**
     * Closes the thread pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends an email.
     * @param subject subject of the email
     * @param body body of the email.
     * @param email e-mail address.
     */
    public void send(String subject, String body, String email) {

    }
}
