package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.MenuItemOrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.mapper.MenuItemOrderMapper;

import java.net.URI;
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
 //http://localhost:8080/Website-1.0-SNAPSHOT/api/menuitemorders/order?orderId=1
    @GET
    @Path("/order")
    @Produces("application/json")
    public Response getMenuItemsOrderFromOrder(@QueryParam("orderId") int orderId){
        List<MenuItemOrdersEntity> menuItemOrdersList;
        menuItemOrdersList = em.createNamedQuery("MenuItemOrdersEntity.getAllFromOrder", MenuItemOrdersEntity.class)
                .setParameter("order", orderId)
                .getResultList();
        List<MenuItemOrdersDTO> menuItemOrdersDTOs = menuItemOrdersList.stream()
                .map(MenuItemOrderMapper::entityToDTO)
                .collect(Collectors.toList());
        return Response.ok(menuItemOrdersDTOs).build();

    }



    @POST
    @Consumes("application/json")
    @Transactional
    public  Response creat(MenuItemOrdersDTO menuItemOrdersDTO){
        //borde använda mapper men funkar just nu /jakob
        MenuItemOrdersEntity menuItemOrdersEntity = new MenuItemOrdersEntity();
        menuItemOrdersEntity.setMenuItemId(menuItemOrdersDTO.getItemId());
        menuItemOrdersEntity.setOrderId(menuItemOrdersDTO.getOrderId());
        menuItemOrdersEntity.setAmount(menuItemOrdersDTO.getAmount());


        em.persist(menuItemOrdersEntity);
        em.flush();
        return Response.created(URI.create("/menuitemorders")).build();
    }

}
