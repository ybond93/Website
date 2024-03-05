package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.KitchenViewEntity;

import java.util.List;

@Path("/kitchen")
public class KitchenManager {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getKitchen() {
        List<KitchenViewEntity> kitchenList;
        kitchenList= em.createNamedQuery("Kitchen_ViewEntity.findAll", KitchenViewEntity.class).getResultList();

        return Response.ok(kitchenList).build();
    }

}
