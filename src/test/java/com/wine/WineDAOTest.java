package com.wine;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WineDAOTest {

    @Mock
    private ConnectionHelper connectionHelper;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private WineDAO wineDAO;
    private static HikariDataSource dataSource;

    private static void initializeDataSource() throws ConnectionHelper.PropertiesLoadException {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream("application-test.properties")){
            properties.load(fis);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("jdbc.url"));
            config.setUsername(properties.getProperty("jdbc.username"));
            config.setPassword(properties.getProperty("jdbc.password"));
            config.setDriverClassName(properties.getProperty("jdbc.driver"));
            config.setMaximumPoolSize(10); // Adjust pool size as needed
            config.setPoolName("WinePool");

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        wineDAO = new WineDAO();
        initializeDataSource();
        mockConnection = dataSource.getConnection();

        // Assuming ConnectionHelper is a utility class that you've written to manage database connections
        when(connectionHelper.getConnection()).thenReturn(mockConnection);

    }

    @Test
    public void testFindAll() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulate one row returned
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Wine");
        // Set other columns as needed

        List<Wine> result = wineDAO.findAll();
        assertNotNull(result);
        assertEquals(1, (result).size());
        Wine wine = result.get(0);
        assertEquals(1, wine.getId());
        assertEquals("Test Wine", wine.getName());

        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    public void testFindById() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Wine");
        // Set other columns as needed

        Wine result = wineDAO.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Wine", result.getName());

        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }
    @Test
    void findByName() {
    }

    @Test
    void findByCountryAndGrapes() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }

    @Test
    void processRow() {
    }
}