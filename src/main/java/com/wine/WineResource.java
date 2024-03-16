package com.wine;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;



@Path("/wines")
public class WineResource {

    private WineDAO dao;
    private static final Logger logger = LogManager.getLogger(WineResource.class);
    public WineResource(){
        dao = new WineDAO();
    }
    public WineResource(WineDAO dao){
        this.dao = dao;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Wine> findAll() {
        return dao.findAll();
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findById(@PathParam("id") int id){
        try {
            Wine wine = dao.findById(id);
            if (wine == null){
                return Response.status(404).build();
            }
            return Response.status(200).entity(wine).build();
        }catch (Exception e){
            logger.warn("findByName", e);
            return Response.status(500).build();
        }

    }

    @GET @Path("search/{query}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByName(@PathParam("query") String name){
        List<Wine> wines = null;
        try {
            wines = dao.findByName(name);
        }catch (Exception e){
            logger.warn("findByName", e);
            return Response.status(404).build();
        }

        return Response.status(200).entity(wines).build();
    }

    @GET @Path("/query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByCountryAndGrapes(@QueryParam("country") String country, @QueryParam("grapes") String grape){
        try {
            List<Wine> wines = dao.findByCountryAndGrapes(country, grape);
            return Response.status(200).entity(wines).build();
        }catch (Exception e){
            return Response.status(500).build();
        }


    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Wine wine){
        Wine wineObj = null;
        try {
            wineObj = dao.create(wine);
        } catch (Exception e){
            logger.warn("create", e);
            return Response.status(500).entity(wineObj).build();
        }

        return Response.status(201).entity(wineObj).build();
    }

    @PUT @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(Wine wine){
        try {
            dao.update(wine);
            return Response.status(201).entity(wine).build();
        }catch (Exception e){
            logger.warn("update", e);
            return Response.status(500).entity("error sql").build();
        }
    }

    @DELETE @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") int id){
        try {
            dao.remove(id);
            return Response.status(204).entity("Deleted!!").build();
        }catch (Exception e){
            logger.warn("remove", e);
            return Response.status(500).build();
        }
    }
}
