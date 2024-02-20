package org.example.website.Controllers;

import org.example.website.Models.MenuItemsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/menu_items")
public class MenuItemsController {
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
