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


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        wineDAO = new WineDAO();

    }

    @Test
    public void testFindAll() throws SQLException {


        List<Wine> result = wineDAO.findAll();
        assertNotNull(result);

    }

    @Test
    public void testFindById() throws SQLException {

        Wine result = wineDAO.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void findByName() {
        List<Wine> wines = wineDAO.findeByName("merlot");
        assertNotNull(wines);
    }
    @Test
    void findByName_Empty() {
        List<Wine> wines = wineDAO.findeByName("xxxx");
        assertNull(wines);
    }

    @Test
    void findByCountryAndGrapes() {
        List<Wine> wines = wineDAO.findByCountryAndGrapes("france", "merlot");
        assertNotNull(wines);
    }
    @Test
    void findByCountryAndGrapes_empty() {
        List<Wine> wines = wineDAO.findByCountryAndGrapes("xxxx", "merlot");
        assertNull(wines);
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