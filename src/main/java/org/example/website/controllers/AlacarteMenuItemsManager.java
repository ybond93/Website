package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.AlacarteMenuItemsEntity;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/alacartemenuitem")
public class AlacarteMenuItemsManager {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    public Response getAlacarteMenuItems(@QueryParam("category") String category) {

        List<AlacarteMenuItemsEntity> alacarteCategoryList;
        String typeQuery = "";

        if(category != null) {
            if (category.equals("starters")) {
                typeQuery = "ALaCarteMenuItems.findStarters";
            } else if (category.equals("mains")) {
                typeQuery = "ALaCarteMenuItems.findMains";
            } else if (category.equals("desserts")) {
                typeQuery = "ALaCarteMenuItems.findDesserts";
            } else if (category.equals("drinks")){
                typeQuery = "ALaCarteMenuItems.findDrinks";
            }
        } else {
            typeQuery = "ALaCarteMenuItems.findAll";
        }
        alacarteCategoryList = em.createNamedQuery(typeQuery, AlacarteMenuItemsEntity.class).getResultList();
        return Response.ok(alacarteCategoryList).build();
    }
}


