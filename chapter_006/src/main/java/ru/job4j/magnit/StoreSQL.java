package ru.job4j.magnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * StoreSQL.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class StoreSQL implements AutoCloseable {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);
    /** The config stores settings for connecting to the database. */
    private final Config config;
    /** Database connection. */
    private Connection connect;

    /**
     * The constructor creates the object Config.
     * @param config - config.
     */
    public StoreSQL(Config config) {
        this.config = config;
        this.init();
    }

    /**
     * The method creates a connection to the database and the tables in it.
     */
    public void init() {
        try {
            connect = DriverManager.getConnection(config.get("url"));
            try (Statement statement = connect.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS  entries (field integer);";
                statement.executeUpdate(sql);
                sql = "DELETE FROM entries";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOG.error("Connection failed: ", e);
        }
    }

    /**
     * The method generates records in the database.
     * @param size - number of records.
     */
    public void generate(int size) {
        try {
            connect.setAutoCommit(false);
            try (PreparedStatement statement = connect.prepareStatement(
                    "INSERT INTO entries(field) values (?)")) {
                for (int i = 1; i <= size; i++) {
                    statement.setInt(1, i);
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            connect.commit();
            connect.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error("Error in the generate() method", e);
            try {
                connect.rollback();
            } catch (SQLException ex) {
                LOG.error("Error when rollback", ex);
            }
        }
    }

    /**
     * The method returns a list of all records from the database.
     * @return list of all records from the database.
     */
    public List<Entry> load() {
        List<Entry> entries = new LinkedList<>();
        try (Statement statement = connect.createStatement()) {
            String sql = "SELECT * FROM entries";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int field = resultSet.getInt("field");
                    entries.add(new Entry(field));
                }
            }
        } catch (SQLException e) {
            LOG.error("Error in the load() method", e);
        }
        return entries;
    }

    /**
     * Method closes the database connection.
     * @throws Exception - exceptions.
     */
    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
