package com.wine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WineResourceTest {

    @InjectMocks
    @Spy
    private WineResource wineResource;
    @Mock
    private WineDAO wineDAO;


    private HttpServer server;
    private WebTarget target;
    public static final String BASE_URI = "http://localhost:8899/";

    @BeforeEach
    public void setUp() throws Exception {
        // start the server
        final ResourceConfig rc = new ResourceConfig().packages("com.wine");
        server =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        server.start();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(BASE_URI);
        System.out.println("Server is starting on: " + BASE_URI);

        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.shutdownNow();
        System.out.println("Server is closed: " + BASE_URI);
    }

    @Test
    void findAll() {
        Wine wine = new Wine();
        wine.setId(1);
        wine.setName("Red Wine");
        wine.setCountry("China");
        wine.setGrapes("Black");
        wine.setPicture("none.jpg");
        wine.setYear("1998");
        List<Wine> wineList = new ArrayList<>();
        Mockito.when(wineDAO.findAll()).thenReturn(wineList);
        String responseMsg = target.path("wines").request().get(String.class);
        //System.out.println(responseMsg);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseMsg);
            assertTrue(jsonNode.isArray(), "Response should be a JSON array");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findById() {
        String id = "123";
        String responseMsg = target.path("wines/1").request().get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    void findByName() {
        String id = "123";
        String responseMsg = target.path("wines/search/CHATEAU").request().get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    void findByCountryAndGrapes() {
        String responseMsg = target.path("wines/query")
                .queryParam("country", "france")
                .queryParam("grapes", "merlot")
                .request()
                .get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    void create() {
        String wineJson = "{\"name\":\"New Wine\",\"year\":\"2021\",\"grapes\":\"Cabernet Sauvignon\",\"country\":\"France\",\"region\":\"Bordeaux\",\"description\":\"A very fine wine.\"}";
        Response response = target.path("wines").request(MediaType.APPLICATION_JSON).post(Entity.entity(wineJson, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        assertEquals(response.getStatus(), 201);
        System.out.println(response.readEntity(String.class));
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}
