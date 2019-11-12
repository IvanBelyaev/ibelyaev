package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * TrackerSQL.
 * @author Ivan Belyaev
 * @since 13.11.2019
 * @version 2.0
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    /** Database connection. */
    private Connection connection;
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class);
    /** Field to create a unique identifier for each request. */
    private static final Random RN = new Random();

    /**
     * The constructor creates the object TrackerSQL.
     */
    public TrackerSQL() {
        this.init();
    }

    /**
     * The method creates a connection to the database and the tables in it.
     * @return true if the connection was successful.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, pass);
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS users ("
                                + "id VARCHAR(14) PRIMARY KEY,"
                                + "name VARCHAR(50) NOT NULL,"
                                + "description VARCHAR(350) NOT NULL,"
                                + "create_time TIMESTAMP NOT NULL)";
                statement.executeUpdate(sql);
                sql = "DELETE FROM users";
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            LOG.error("Connection failed: ", e);
        }
        return connection != null;
    }

    /**
     * The method adds a request.
     * @param item - new application.
     * @return returns a reference to the request or null if add failed.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users(id, name, description, create_time) VALUES(?, ?, ?, ?)")) {
            String newID = this.generateId();
            item.setId(newID);
            statement.setString(1, newID);
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesctiption());
            statement.setTimestamp(4, new Timestamp(item.getCreate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error in the add() method", e);
        }
        return item;
    }

    /**
     * The method updates the information in the application.
     * @param id - id of request you want to update
     * @param item - new information for applications.
     */
    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET name = ?, description = ?, create_time = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesctiption());
            statement.setTimestamp(3, new Timestamp(item.getCreate()));
            statement.setString(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error in the replace() method", e);
        }
    }

    /**
     * Method removes the application.
     * @param id - id of request you want to delete
     */
    @Override
    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error in the delete() method", e);
        }
    }

    /**
     * The method returns all applications.
     * @return returns an array of all applications.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                while (resultSet.next()) {
                    items.add(this.getItem(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error in the findAll() method", e);
        }
        return items;
    }

    /**
     * The method returns all applications with a specific name.
     * @param key - name to search for.
     * @return returns an array of all applications with a specific name.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name = ?")) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(this.getItem(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error in the findByName() method", e);
        }
        return items;
    }

    /**
     * Method returns the request with a specific ID.
     * @param id - specific ID.
     * @return returns the request with a specific ID.
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = this.getItem(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error in the findById() method", e);
        }
        return item;
    }

    /**
     * Method closes the database connection.
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error("Error occurred while closing the connection to the database", e);
            }
        }
    }

    /**
     * The method generates an ID.
     * @return returns the unique ID.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Method creates a Item object from query results.
     * @param resultSet - ResultSet.
     * @return a item object.
     * @throws SQLException - sql exceptions.
     */
    private Item getItem(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String desc = resultSet.getString("description");
        long create = resultSet.getTimestamp("create_time").getTime();
        Item item = new Item(name, desc, create);
        item.setId(id);
        return item;
    }
}