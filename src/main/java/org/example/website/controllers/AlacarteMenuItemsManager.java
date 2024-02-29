package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.AlacarteMenuItemsEntity;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/alacartemenuItem")
public class AlacarteMenuItemsManager {

    @PersistenceContext
    private EntityManager em;
    @Path("/starters")
    @GET
    @Produces("application/json")
    public Response getStartersList() {
        List<AlacarteMenuItemsEntity> startersList;
        startersList = em.createNamedQuery("ALaCarteMenuItems.findStarters", AlacarteMenuItemsEntity.class).getResultList();
        return Response.ok(startersList).build();
    }
    @Path("/mains")
    @GET
    @Produces("application/json")
    public Response getMainsList() {
        List<AlacarteMenuItemsEntity> mainsList;
        mainsList = em.createNamedQuery("ALaCarteMenuItems.findMains", AlacarteMenuItemsEntity.class).getResultList();
        return Response.ok(mainsList).build();
    }
    @Path("/desserts")
    @GET
    @Produces("application/json")
    public Response getDessertsList() {
        List<AlacarteMenuItemsEntity> dessertsList;
        dessertsList = em.createNamedQuery("ALaCarteMenuItems.findDesserts", AlacarteMenuItemsEntity.class).getResultList();
        return Response.ok(dessertsList).build();
    }
    @Path("/drinks")
    @GET
    @Produces("application/json")
    public Response getDrinksList() {
        List<AlacarteMenuItemsEntity> drinksList;
        drinksList = em.createNamedQuery("ALaCarteMenuItems.findDrinks", AlacarteMenuItemsEntity.class).getResultList();
        return Response.ok(drinksList).build();
    }
}

