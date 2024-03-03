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
import org.example.website.mapper.AlacarteMenuItemsMapper;
import org.example.website.mapper.EmployeesMapper;
import org.example.website.mapper.OrdersMapper;

@Path("/KitchenOrder")
public class KitchenOrderManager {



    @PersistenceContext
    private EntityManager em; // Injecting EntityManager

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKitchenOrders() {
        // Fetch data using EntityManager and named query for OrdersEntity
        String ordersQuery = "OrdersEntity.findAll"; // Replace with your named query
        List<OrdersEntity> ordersEntities = em.createNamedQuery(ordersQuery, OrdersEntity.class).getResultList();

        // Extract order IDs
        List<Integer> orderIds = ordersEntities.stream()
                .map(OrdersEntity::getOrderId)
                .collect(Collectors.toList());

        // Fetch MenuItemOrders based on order IDs
        List<MenuItemOrdersEntity> menuItemOrderEntities = fetchMenuItemOrdersByOrderIds(orderIds);

        // Extract menuItemIds from MenuItemOrders
        List<Integer> menuItemIds = menuItemOrderEntities.stream()
                .map(MenuItemOrdersEntity::getMenuItemId)
                .collect(Collectors.toList());

        // Fetch AlacarteMenuItemsDTO and MenuItemDTO based on the extracted menuItemIds
        List<AlacarteMenuItemsDTO> alacarteMenuItemsDTOs = fetchAlacarteMenuItemsByMenuIds(menuItemIds);
        List<MenuItemDTO> menuItemDTOs = fetchMenuItemsByIds(menuItemIds);

        // For simplicity, assume you have fetched ordersDTO and tablesDTO from somewhere

        // Create the combined DTO object
        KitchenOrdersDTO kitchenOrdersDTO = new KitchenOrdersDTO();
        kitchenOrdersDTO.setOrdersDTO(OrdersDTO);
        kitchenOrdersDTO.setTablesDTO(TablesDTO);
        kitchenOrdersDTO.setAlacarteMenuItemsDTOs(alacarteMenuItemsDTOs);
        kitchenOrdersDTO.setMenuItemDTO(menuItemDTOs);

        // Return the combined DTO as a response
        return Response.ok(kitchenOrdersDTO).build();
    }

    // Method to fetch MenuItemOrderEntity based on order IDs
    private List<MenuItemOrdersEntity> fetchMenuItemOrdersByOrderIds(List<Integer> orderIds) {
        // Fetch MenuItemOrders based on orderIds
        // Write your logic to fetch MenuItemOrderEntity using orderIds
        return null;
    }

    // Method to fetch AlacarteMenuItemsDTO based on menu item IDs
    private List<AlacarteMenuItemsDTO> fetchAlacarteMenuItemsByMenuIds(List<Integer> menuItemIds) {
        // Fetch AlacarteMenuItems based on menuItemIds
        // Write your logic to fetch AlacarteMenuItemsDTO using menuItemIds
        return null;
    }

    // Method to fetch MenuItemDTO based on menu item IDs
    private List<MenuItemDTO> fetchMenuItemsByIds(List<Integer> menuItemIds) {
        // Fetch MenuItems based on menuItemIds
        // Write your logic to fetch MenuItemDTO using menuItemIds
        return null;
    }*/


    @GET
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
    }
}
