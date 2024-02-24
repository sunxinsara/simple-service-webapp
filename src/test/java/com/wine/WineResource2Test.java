package com.wine;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

        List<Wine> resultList = wineResource.findAll();
        assertNotNull(resultList);
        assertEquals(expectedList, resultList);
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
    void findByName() {
        List<Wine> expectedWines = new ArrayList<>();
        expectedWines.add(new Wine());

        when(wineDAO.findeByName("Merlot")).thenReturn(expectedWines);

        Response response = wineResource.findByName("Merlot");

        assertEquals(200, response.getStatus());
        assertEquals(expectedWines, response.getEntity());
        verify(wineDAO).findeByName("Merlot");
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
    void remove() {
        when(wineDAO.remove(1)).thenReturn(true);

        Response response = wineResource.remove(1);

        assertEquals(204, response.getStatus());
        assertEquals("Deleted!!", response.getEntity());
        verify(wineDAO).remove(1);
    }
}