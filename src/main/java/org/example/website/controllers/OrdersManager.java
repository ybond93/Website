package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.OrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;
import org.example.website.mapper.OrdersMapper;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/orders")
public class OrdersManager {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getOrders() {
        List<OrdersEntity> ordersList;
        ordersList = em.createNamedQuery("OrdersEntity.findAll", OrdersEntity.class).getResultList();
        List<OrdersDTO> ordersDTOs = ordersList.stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());
        return Response.ok(ordersDTOs).build();
    }

    /*
    // will be used for inserting and updating data from the API
    @POST
    @Consumes("application/json")
    @Transactional
    public Response createOrder(OrdersDTO orderDTO) {
        OrdersEntity newOrderEntity = OrdersMapper.toEntity(orderDTO);
        em.persist(newOrderEntity);
        return Response.created(URI.create("/orders/" + newOrderEntity.getOrderId())).build();
    }
     */

    // test solution, tailored for our ER-model
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response createOrder(OrdersDTO orderDTO) {
        // Convert DTO to Entity
        OrdersEntity orderEntity = new OrdersEntity();
        // Set Order's table number
        TablesEntity table = em.find(TablesEntity.class, orderDTO.getTableNum());
        orderEntity.setTableNum(table);

        // Set Order's menu items
        List<MenuItemOrdersEntity> menuItemOrdersEntities = orderDTO.getMenuItemOrders().stream()
                .map(dto -> {
                    MenuItemOrdersEntity menuItemOrder = new MenuItemOrdersEntity();
                    MenuItemsEntity menuItem = em.find(MenuItemsEntity.class, dto.getMenuItemId());
                    menuItemOrder.setMenuItem(menuItem);
                    menuItemOrder.setOrder(orderEntity);
                    menuItemOrder.setAmount(dto.getAmount());
                    return menuItemOrder;
                }).collect(Collectors.toList());

        orderEntity.setMenuItemOrders(menuItemOrdersEntities);

        em.persist(orderEntity);
        em.flush(); // Ensure ID is generated

        // Optionally convert back to DTO to return the created order
        OrdersDTO persistedOrderDTO = OrdersMapper.toDTO(orderEntity);

        // Return the response with the location of the created order
        return Response.created(URI.create("/orders/" + orderEntity.getOrderId()))
                .entity(persistedOrderDTO)
                .build();
    }
}