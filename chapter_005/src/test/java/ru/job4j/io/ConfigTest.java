package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * ConfigTest.
 * @author Ivan Belyaev
 * @since 14.04.2019
 * @version 1.0
 */
public class ConfigTest {
    /**
     * Test for Config class.
     * @throws IOException - input / output exceptions.
     */
    @Test
    public void whenLoadThenValueContainsAllKeys() throws IOException {
        String path = createSettingsFile();

        Config config = new Config(path);
        config.load();

        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value(
                "hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"
        ));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertNull(config.value("non-existent key"));

        deleteSettingsFile(path);
    }

    /**
     * Method creates a configuration file.
     * @return returns the path to the file with the settings.
     * @throws IOException - input / output exceptions.
     */
    public String createSettingsFile() throws IOException {
        String path = System.getProperty("java.io.tmpdir") + File.separator + "app.properties";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("## PostgreSQL\n");
            bw.newLine();
            bw.write("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect\n");
            bw.write("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio\n");
            bw.write("hibernate.connection.driver_class=org.postgresql.Driver\n");
            bw.write("hibernate.connection.username=postgres\n");
            bw.write("hibernate.connection.password=password\n");
        }

        return path;
    }

    /**
     * The method deletes the file.
     * @param path - path to the file to be deleted.
     */
    public void deleteSettingsFile(String path) {
        new File(path).delete();
    }
}
