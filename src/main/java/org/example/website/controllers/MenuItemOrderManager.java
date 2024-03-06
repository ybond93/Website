package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.MenuItemOrderDTO;
import org.example.website.dto.OrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.mapper.MenuItemOrderMapper;
import org.example.website.mapper.OrdersMapper;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@Path("/menuitemorder")
public class MenuItemOrderManager {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    public Response getMenuItemsOrders() {
        List<MenuItemOrdersEntity> menuItemsOrdersList;
        menuItemsOrdersList = em.createNamedQuery("MenuItemOrdersEntity.findAll", MenuItemOrdersEntity.class).getResultList();
        List<MenuItemOrderDTO> menuItemOrdersDTOs = menuItemsOrdersList.stream()
                .map(MenuItemOrderMapper::toDTO)
                .collect(Collectors.toList());
        return Response.ok(menuItemOrdersDTOs).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response createOrder(MenuItemOrderDTO menuItemOrderDTO) {
        //System.out.println("Received menuItemOrderDTO: " + menuItemOrderDTO);

        OrdersEntity existingOrder = em.find(OrdersEntity.class, menuItemOrderDTO.getOrder().getOrderId());
        Integer maxOrderId;
        MenuItemOrdersEntity menuItemOrdersEntity = new MenuItemOrdersEntity();
        if (existingOrder == null) {
            maxOrderId = (Integer) em.createQuery("SELECT MAX(o.orderId) FROM OrdersEntity o").getSingleResult();

            // get the correct menu item based on the clicked a la carte item id
            Integer carteItemId = menuItemOrderDTO.getMenuItem().getId();
            Integer correspondingMenuItemId = em.createQuery(
                            "SELECT ami.menuItem.id FROM AlacarteMenuItemsEntity ami WHERE ami.carteItemId = :carteItemId", Integer.class)
                    .setParameter("carteItemId", carteItemId)
                    .getSingleResult();

            // assign the correct menu item id
            menuItemOrderDTO.getMenuItem().setId(correspondingMenuItemId);

            OrdersEntity ordersEntity = OrdersMapper.toEntity(menuItemOrderDTO.getOrder());
            em.persist(ordersEntity);
            em.flush(); // forces the order ID to be automatically generated

            Integer orderId = ordersEntity.getOrderId(); // This should now contain the generated ID.

            menuItemOrderDTO.getOrder().setOrderId(orderId);   // previously: maxOrderId + 1

             menuItemOrdersEntity = MenuItemOrderMapper.toEntity(menuItemOrderDTO);
        } else {
             menuItemOrdersEntity = MenuItemOrderMapper.toEntity(menuItemOrderDTO);
        }

        //MenuItemOrdersEntity menuItemOrdersEntity = MenuItemOrderMapper.toEntity(menuItemOrderDTO);
        //OrdersEntity ordersEntity = OrdersMapper.toEntity(menuItemOrderDTO.getOrder());
        //em.persist(ordersEntity);
        em.persist(menuItemOrdersEntity);
        return Response.ok().build();
    }
}
