package com.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URI;

class MyResourceTest {
    private HttpServer server;
    private WebTarget target;
    public static final String BASE_URI = "http://localhost:8656/";

    @BeforeEach
    public void setUp() throws Exception {
        // start the server
        final ResourceConfig rc = new ResourceConfig().packages("com.example");
        server =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        server.start();
        System.out.println("Server is starting on: " + BASE_URI);

        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also hamve to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.shutdownNow();
        System.out.println("Server is closed: " + BASE_URI);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        try{
            String responseMsg = target.path("myresource").request().get(String.class);
            System.out.println(responseMsg);
            assertEquals("Got it!", responseMsg);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

}
