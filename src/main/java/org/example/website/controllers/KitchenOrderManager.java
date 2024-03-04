package org.example.website.controllers;


import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.*;
import org.example.website.entities.AlacarteMenuItemsEntity;
import org.example.website.entities.MenuItemOrdersEntity;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.example.website.entities.OrdersEntity;
import org.example.website.mapper.*;

@Path("/KitchenOrder")
public class KitchenOrderManager {



    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKitchenOrders() {
        List<MenuItemOrdersEntity> menuItemOrderEntities = em.createNamedQuery("MenuItemOrdersEntity.findInProgress", MenuItemOrdersEntity.class)
                .getResultList();

        // Map MenuItemOrdersEntity to DTO using the toDTO method
        List<MenuItemOrderDTO> menuItemOrderDTOs = menuItemOrderEntities.stream()
                .map(MenuItemOrderMapper::toDTO)
                .collect(Collectors.toList());

        // Extract order IDs from MenuItemOrderDTOs
        List<Integer> orderIds = menuItemOrderDTOs.stream()
                .map(MenuItemOrderDTO::getOrderId)
                .collect(Collectors.toList());

        // Fetch orders based on order IDs
        String ordersQuery = "SELECT o FROM OrdersEntity o WHERE o.orderId IN :orderIds";
        List<OrdersEntity> ordersEntities = em.createQuery(ordersQuery, OrdersEntity.class)
                .setParameter("orderIds", orderIds)
                .getResultList();


        List<OrdersDTO> orderDTOs = ordersEntities.stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());



        List<Integer> menuItemIds = menuItemOrderDTOs.stream()
                .map(MenuItemOrderDTO::getMenuItemId)
                .collect(Collectors.toList());

        // First step: Fetch AlacarteMenuItemsEntity instances based on menuItemIds
        List<AlacarteMenuItemsEntity> alacarteMenuItemsEntities = em.createQuery(
                        "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem " +
                                "WHERE a.menuItem.id IN :menuItemIds", AlacarteMenuItemsEntity.class)
                .setParameter("menuItemIds", menuItemIds)
                .getResultList();


        List<AlacarteMenuItemsDTO> alacarteMenuItemDTOs = alacarteMenuItemsEntities.stream()
                .map(AlacarteMenuItemsMapper::toDTO)
                .collect(Collectors.toList());


        // Set the mapped DTOs to kitchenOrdersDTO
        KitchenOrdersDTO kitchenOrdersDTO = new KitchenOrdersDTO();
        kitchenOrdersDTO.setMenuItemOrderDTO(menuItemOrderDTOs);
        kitchenOrdersDTO.setOrdersDTO(orderDTOs);
        //kitchenOrdersDTO.setAlacarteMenuItemsDTOs(alacarteMenuItemsDTOs);

        return Response.ok(alacarteMenuItemDTOs).build();
    }

    /*


    List<AlacarteMenuItemsEntity> alacarteMenuItemsEntities = em.createQuery(
                    "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem m " +
                            "WHERE a.menuItem.id IN :menuItemIds", AlacarteMenuItemsEntity.class)
            .setParameter("menuItemIds", menuItemIds)
            .getResultList();


    List<AlacarteMenuItemsDTO> alacarteMenuItemDTOs = alacarteMenuItemsEntities.stream()
            .map(entity -> new AlacarteMenuItemsDTO(entity.getCategory(), entity.getMenuItem().getName()))
            .collect(Collectors.toList());
    */

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKitchenOrders() {
        // Fetch data using EntityManager and named query
        String typeQuery = "ALaCarteMenuItems.findAll";
        List<AlacarteMenuItemsEntity> alacarteMenuItemsEntities = em.createNamedQuery(typeQuery, AlacarteMenuItemsEntity.class).getResultList();

        // Mapping AlacarteMenuItemsEntity to AlacarteMenuItemsDTO
        List<AlacarteMenuItemsDTO> alacarteMenuItemsDTOs = alacarteMenuItemsEntities.stream()
                .map(AlacarteMenuItemsMapper::toDTO)
                .collect(Collectors.toList());


        // Create the combined DTO object
        KitchenOrdersDTO kitchenOrdersDTO = new KitchenOrdersDTO();
        kitchenOrdersDTO.setAlacarteMenuItemsDTOs(alacarteMenuItemsDTOs); // Update the setter method name

        // Return the combined DTO as a response
        return Response.ok(kitchenOrdersDTO).build();
    }*/
}
