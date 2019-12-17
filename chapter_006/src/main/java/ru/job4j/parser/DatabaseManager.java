package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * DatabaseManager.
 * Class for performing operations with the database.
 * @author Ivan Belyaev
 * @since 16.12.2019
 * @version 2.0
 */
public class DatabaseManager implements AutoCloseable {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(DatabaseManager.class);
    /** Stores application properties. */
    private Properties properties = new Properties();
    /** Database connection. */
    private Connection connection;

    /**
     * Constructor used by the application.
     */
    public DatabaseManager() {

    }

    /**
     * Constructor with a database connection parameter.
     * @param connection - database connection.
     */
    public DatabaseManager(Connection connection) {
        this.connection = connection;
        init();
    }

    /**
     * The method sets the properties field.
     * @param properties - application properties.
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Methods connects to db and creates tables if necessary.
     */
    public void connect() {
        try {
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, pass);
            this.init();
        } catch (Exception e) {
            LOG.error("Connection failed: ", e);
        }
    }

    /**
     * Methods creates tables in database if necessary.
     */
    private void init() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS vacancy ("
                    + "id serial PRIMARY KEY,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "text VARCHAR(2000) NOT NULL,"
                    + "link VARCHAR(200) NOT NUll"
                    + ")";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS update_to ("
                    + "dataTime TIMESTAMP NOT NULL "
                    + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error creating database structure", e);
        }
    }

    /**
     * Method closes database connection.
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error("An error occurred while closing the connection", e);
        }
    }

    /**
     * The method returns the date of the last update.
     * @return the date of the last update.
     */
    public LocalDateTime getReadUpDataTime() {
        LocalDateTime result = null;
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM update_to";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    result = resultSet.getTimestamp("dataTime").toLocalDateTime();
                } else {
                    result = LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.MIN);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error getting date", e);
        }
        return result;
    }

    /**
     * The method brings new vacancies to the database.
     * Jobs with the same name are considered the same.
     * It also updates the date of the last search in the database.
     * @param list - list of vacancies.
     * @param newReadUpDataTime - the date of the last search.
     */
    public void updateDB(List<Vacancy> list, LocalDateTime newReadUpDataTime) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO vacancy(name, text, link) VALUES(?, ?, ?)"
            )) {
                for (Vacancy vacancy : list) {
                    if (!doesVacancyExist(vacancy)) {
                        statement.setString(1, vacancy.getName());
                        statement.setString(2, vacancy.getText());
                        statement.setString(3, vacancy.getLink());
                        statement.executeUpdate();
                    }
                }
                updateDataTime(newReadUpDataTime);
        } catch (SQLException e) {
            LOG.error("SQLException when adding vacancies", e);
        }
    }

    /**
     * The method returns a list of vacancies from the database.
     * @return a list of vacancies from the database.
     */
    public List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * from vacancy";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    vacancies.add(
                            new Vacancy(
                                    resultSet.getString("name"),
                                    resultSet.getString("text"),
                                    resultSet.getString("link")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error("Error trying to get vacancies from the database", e);
        }
        return vacancies;
    }

    /**
     * Method updates the date of the last search in the database.
     * @param newReadUpDataTime - the date of the last search.
     * @throws SQLException - sql exceptions.
     */
    private void updateDataTime(LocalDateTime newReadUpDataTime) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM update_to";
            statement.executeUpdate(sql);
        }
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO update_to values (?)")) {
            statement.setTimestamp(1, Timestamp.valueOf(newReadUpDataTime));
            statement.executeUpdate();
        }
    }

    /**
     * The method checks if this vacancy exists in the database.
     * Jobs with the same name are considered the same.
     * @param vacancy - vacancy for check.
     * @return true if vacancy exist otherwise returns false.
     * @throws SQLException - sql exceptions.
     */
    private boolean doesVacancyExist(Vacancy vacancy) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM vacancy WHERE name = ?")) {
            statement.setString(1, vacancy.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = true;
                }
            }
        }
        return result;
    }
}
