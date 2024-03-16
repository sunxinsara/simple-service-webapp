package com.wine;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WineResource2Test {
    @Mock
    private WineDAO wineDAO;

    @InjectMocks
    private WineResource wineResource;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Spy
    private WineResource resource = new WineResource();
    @Test
    void defaultConstructorInitializesWineDAO() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        wineResource = spy(WineResource.class);
        Field daoField = WineResource.class.getDeclaredField("dao");
        daoField.setAccessible(true);
        WineDAO wineDAOTest = (WineDAO) daoField.get(wineResource);
        assertNotNull(wineDAOTest, "wineDAO should not be null");
    }

    @Test
    void findAll() {
        List<Wine> expectedList = new ArrayList<>();

        Wine wine = new Wine();
        wine.setId(1);
        wine.setName("Red Wine");
        wine.setCountry("China");
        wine.setGrapes("Black");
        wine.setPicture("none.jpg");
        wine.setYear("1998");

        when(wineDAO.findAll()).thenReturn(expectedList);
        List<Wine> resultList = wineResource.findAll();
        assertNotNull(resultList);
        assertEquals(expectedList, resultList);
        verify(wineDAO).findAll();
    }

    @Test
    void findAll_Empty() {
        when(wineDAO.findAll()).thenReturn(new ArrayList<>());
        List<Wine> resultList = wineResource.findAll();
        assertTrue(resultList.isEmpty());
        verify(wineDAO).findAll();
    }

    @Test
    void findById() {
        Wine expectedWine = new Wine();
        expectedWine.setId(1);
        expectedWine.setName("Red Wine");

        when(wineDAO.findById(1)).thenReturn(expectedWine);

        Response response = wineResource.findById(1);

        assertEquals(200, response.getStatus());
        assertEquals(expectedWine, response.getEntity());
        verify(wineDAO).findById(1);
    }

    @Test
    void findById_NotFound() {
        when(wineDAO.findById(anyInt())).thenReturn(null);

        Response response = wineResource.findById(99); // Assuming ID 99 does not exist

        assertEquals(404, response.getStatus(), "Expected a 404 status for a non-existent wine ID");
        verify(wineDAO).findById(99);
    }

    @Test
    void findByName() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(new Wine());

        when(wineDAO.findByName("Merlot")).thenReturn(expectedWines);

        Response response = wineResource.findByName("Merlot");

        assertEquals(200, response.getStatus());
        assertEquals(expectedWines, response.getEntity());
        verify(wineDAO).findByName("Merlot");
    }

    @Test
    void findByName_Empty() {
        when(wineDAO.findByName("Unknown")).thenReturn(new ArrayList<>());

        Response response = wineResource.findByName("Unknown");

        assertEquals(200, response.getStatus());
        assertTrue(((List<Wine>) response.getEntity()).isEmpty());
        verify(wineDAO).findByName("Unknown");
    }


    @Test
    void findByCountryAndGrapes() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(new Wine());

        when(wineDAO.findByCountryAndGrapes("France", "Merlot")).thenReturn(expectedWines);

        Response response = wineResource.findByCountryAndGrapes("France", "Merlot");

        assertEquals(200, response.getStatus());
        assertEquals(expectedWines, response.getEntity());
        verify(wineDAO).findByCountryAndGrapes("France", "Merlot");
    }

    @Test
    void create() {
        Wine wineToCreate = new Wine();
        wineToCreate.setName("New Wine");

        Wine createdWine = new Wine();
        createdWine.setId(1);
        createdWine.setName("New Wine");

        when(wineDAO.create(wineToCreate)).thenReturn(createdWine);

        Response response = wineResource.create(wineToCreate);

        assertEquals(201, response.getStatus());
        assertEquals(createdWine, response.getEntity());
        verify(wineDAO).create(wineToCreate);
    }

    @Test
    void update() {
        Wine wineToUpdate = new Wine();
        wineToUpdate.setId(1);
        wineToUpdate.setName("Updated Wine");

        when(wineDAO.update(wineToUpdate)).thenReturn(wineToUpdate);

        Response response = wineResource.update(wineToUpdate);

        assertEquals(201, response.getStatus());
        // assertEquals(wineToUpdate, response.getEntity());
        verify(wineDAO).update(wineToUpdate);
    }

    @Test
    void update_Error() {
        Wine wineToUpdate = new Wine();
        wineToUpdate.setId(1);
        wineToUpdate.setName("Updated Wine");

        doThrow(new RuntimeException("Database error")).when(wineDAO).update(any(Wine.class));

        Response response = wineResource.update(wineToUpdate);

        assertEquals(500, response.getStatus(), "Expected a 500 status due to database error");
        verify(wineDAO).update(wineToUpdate);
    }

    @Test
    void remove() {
        when(wineDAO.remove(1)).thenReturn(true);

        Response response = wineResource.remove(1);

        assertEquals(204, response.getStatus());
        assertEquals("Deleted!!", response.getEntity());
        verify(wineDAO).remove(1);
    }
}