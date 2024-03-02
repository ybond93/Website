package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.MenuItemOrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.mapper.MenuItemOrderMapper;

import java.util.List;
import java.util.stream.Collectors;
@Path("/menuitemorders")
public class MenuItemsOrdersManager {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getMenuItemsOrders() {
        List<MenuItemOrdersEntity> menuItemsOrdersList;
        menuItemsOrdersList = em.createNamedQuery("MenuItemOrdersEntity.findAll", MenuItemOrdersEntity.class).getResultList();
        List<MenuItemOrdersDTO> menuItemOrdersDTOs = menuItemsOrdersList.stream()
                .map(MenuItemOrderMapper::entityToDTO)
                .collect(Collectors.toList());
        return Response.ok(menuItemOrdersDTOs).build();

    }
}
