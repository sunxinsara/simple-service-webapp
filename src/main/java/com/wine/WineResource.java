package com.wine;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;



@Path("/wines")
public class WineResource {

    private WineDAO dao;
    public WineResource(){
        dao = new WineDAO();
    }
    public WineResource(WineDAO dao){
        this.dao = dao;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Wine> findAll() {
        System.out.println("findAll");
        List<Wine> list = dao.findAll();
        System.out.println(list);
        return list;
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findById(@PathParam("id") int id){
        Wine wine = dao.findById(id);
        return Response.status(200).entity(wine).build();
    }

    @GET @Path("search/{query}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByName(@PathParam("query") String name){
        List<Wine> wines = null;
        try {
            wines = dao.findeByName(name);
            System.out.println(wines);
        }catch (Throwable e){
            e.printStackTrace();
            return Response.status(200).entity(wines).build();
        }

        return Response.status(200).entity(wines).build();
    }

    @GET @Path("/query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByCountryAndGrapes(@QueryParam("country") String country, @QueryParam("grapes") String grape){
        List<Wine> wines = dao.findByCountryAndGrapes(country, grape);
        return Response.status(200).entity(wines).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Wine wine){
        Wine wineObj = null;
        try {
            wineObj = dao.create(wine);
        } catch (Throwable e){
            e.printStackTrace();
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
        }catch (Throwable e){
            e.printStackTrace();
            return Response.status(500).entity("error sql").build();
        }
    }

    @DELETE @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") int id){
        try {
            dao.remove(id);
            return Response.status(204).entity("Deleted!!").build();
        }catch (Throwable e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}
