package com.wine;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {
    private static HikariDataSource dataSource;
    // Private constructor to prevent instantiation
    private ConnectionHelper() {
        // Prevents the default parameter-less constructor from being used elsewhere in your code.
    }

    // Static inner class for a custom exception
    public static class PropertiesLoadException extends Exception {
        public PropertiesLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    static {
        try {
            initializeDataSource();
        } catch (PropertiesLoadException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static void initializeDataSource() throws PropertiesLoadException {
        Properties properties = new Properties();
        try(InputStream is = ConnectionHelper.class.getClassLoader().getResourceAsStream("application-prod.properties")){
            if (is != null) {
                properties.load(is);
            } else {
                throw new FileNotFoundException("Property file 'application-prod.properties' not found in the classpath");
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("jdbc.url"));
            config.setUsername(properties.getProperty("jdbc.username"));
            config.setPassword(properties.getProperty("jdbc.password"));
            config.setDriverClassName(properties.getProperty("jdbc.driver"));
            config.setMaximumPoolSize(10); // Adjust pool size as needed
            config.setPoolName("WinePool");

            dataSource = new HikariDataSource(config);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PropertiesLoadException("File not found exception occurred", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PropertiesLoadException("Failed to load properties file", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}