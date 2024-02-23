package com.wine;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WineResourceTest {
    private HttpServer server;
    private WebTarget target;
    public static final String BASE_URI = "http://localhost:8080/";

    @BeforeEach
    public void setUp() throws Exception {
        // start the server
        final ResourceConfig rc = new ResourceConfig().packages("com.wine");
        server =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void findAllTest(){
        String responseMsg = target.path("wines").request().get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void findByIdTest(){
        String id = "123";
        String responseMsg = target.path("wines/1").request().get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void findByIdNameTest(){
        String id = "123";
        String responseMsg = target.path("wines/search/CHATEAU").request().get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void findByCountryAndGrapesTest(){
        String responseMsg = target.path("wines/query")
                .queryParam("country", "france")
                .queryParam("grapes", "merlot")
                .request()
                .get(String.class);
        System.out.println(responseMsg);
    }

    @Test
    public void createTest(){
        String wineJson = "{\"name\":\"New Wine\",\"year\":\"2021\",\"grapes\":\"Cabernet Sauvignon\",\"country\":\"France\",\"region\":\"Bordeaux\",\"description\":\"A very fine wine.\"}";
        Response response = target.path("wines").request(MediaType.APPLICATION_JSON).post(Entity.entity(wineJson, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        assertEquals(response.getStatus(), 201);
        System.out.println(response.readEntity(String.class));
    }
}
