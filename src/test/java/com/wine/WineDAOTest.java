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
        List<Wine> wines = wineDAO.findByName("merlot");
        assertNotNull(wines);
    }
    @Test
    void findByName_Empty() {
        List<Wine> wines = wineDAO.findByName("xxxx");
        assertTrue(wines.size() ==0 );
    }

    @Test
    void findByCountryAndGrapes() {
        List<Wine> wines = wineDAO.findByCountryAndGrapes("france", "merlot");
        assertNotNull(wines);
    }
    @Test
    void findByCountryAndGrapes_empty() {
        List<Wine> wines = wineDAO.findByCountryAndGrapes("xxxx", "merlot");
        assertTrue(wines.size() ==0 );
    }

    @Test
    void create() {
        Wine wine = new Wine();
        wine.setId(61);
        wine.setName("Red Wine Test");
        wine.setCountry("China");
        wine.setGrapes("Black");
        wine.setPicture("none.jpg");
        wine.setYear("1998");

        Wine wineRes = wineDAO.create(wine);
        assertEquals(wine.getName(), wineRes.getName());
    }

    @Test
    void update() {
        Wine wineToUpdate = new Wine();
        wineToUpdate.setId(1);
        wineToUpdate.setName("Updated Wine");

        Wine wineResult = wineDAO.update(wineToUpdate);
        assertEquals(wineResult, wineToUpdate);
    }

    @Test
    void remove() {
        List<Wine> result = wineDAO.findAll();
        boolean res = wineDAO.remove(result.getLast().getId());
        assertTrue(res);
    }

    @Test
    void processRow() throws SQLException {
// Create a mock ResultSet.
        mockResultSet = mock(ResultSet.class);

        // Set up the mock ResultSet behavior.
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("BLOCK NINE");
        when(mockResultSet.getString("grapes")).thenReturn("Pinot Noir");
        when(mockResultSet.getString("country")).thenReturn("USA");
        when(mockResultSet.getString("region")).thenReturn("California");
        when(mockResultSet.getString("year")).thenReturn("2009");
        when(mockResultSet.getString("picture")).thenReturn("block_nine.jpg");
        when(mockResultSet.getString("description")).thenReturn("With hints of ginger and spice, this wine makes an excellent complement to light appetizer and dessert fare for a holiday gathering.");

        Wine result = wineDAO.processRow(mockResultSet);
        assertEquals(1, result.getId());
        assertEquals("BLOCK NINE", result.getName());
        assertEquals("Pinot Noir", result.getGrapes());
        assertEquals("USA", result.getCountry());
        assertEquals("California", result.getRegion());
        assertEquals("2009", result.getYear());
        assertEquals("block_nine.jpg", result.getPicture());

    }
}