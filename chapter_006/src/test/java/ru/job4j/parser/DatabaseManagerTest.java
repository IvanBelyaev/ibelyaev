package ru.job4j.parser;

import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DatabaseManagerTest.
 * @author Ivan Belyaev
 * @since 17.12.2019
 * @version 1.0
 */
public class DatabaseManagerTest {
    /**
     * Method creates database connection.
     * @return database connection.
     */
    private Connection init() {
        Connection connection = null;
        Properties properties = new Properties();
        try (InputStream in = DatabaseManagerTest.class.getClassLoader().getResourceAsStream("sqlRuParser.properties")) {
            properties.load(in);
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");
            connection = ConnectionRollback.create(DriverManager.getConnection(url, user, pass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Test for the DatabaseManager class.
     */
    @Test
    public void whenVacancyIsAddedThenItAppearsInTheDatabase() {
        DatabaseManager databaseManager = new DatabaseManager(this.init());
        List<Vacancy> listOfVacancies = new LinkedList<>();
        Vacancy vacancy1 = new Vacancy("java", "text", "link");
        Vacancy vacancy2 = new Vacancy("java", "text2", "link2");
        listOfVacancies.add((vacancy1));
        listOfVacancies.add((vacancy2));

        int numberOfVacancies = databaseManager.getVacancies().size();
        LocalDateTime lastUpdate = LocalDateTime.now();
        databaseManager.updateDB(listOfVacancies, lastUpdate);

        assertThat(databaseManager.getVacancies().size(), is(numberOfVacancies + 1));
        assertThat(databaseManager.getReadUpDataTime(), is(lastUpdate));
    }
}
