package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.MenuItemsEntity;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

@Path("/menuitems")
public class MenuItemsManager {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    public Response getMenuItems() {
        List<MenuItemsEntity> menu_items;
        menu_items = em.createNamedQuery("MenuItemsEntity.findAll", MenuItemsEntity.class).getResultList();
        return Response.ok(menu_items).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addMenuItem(MenuItemsEntity menuItem) {
        em.persist(menuItem);
        em.flush(); // Optional, to ensure ID is generated
        return Response.ok(menuItem).build();
    }
}

