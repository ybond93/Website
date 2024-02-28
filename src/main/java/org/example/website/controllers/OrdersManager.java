package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.OrdersDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;
import org.example.website.mapper.OrdersMapper;
import org.example.website.mapper.TablesMapper;

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
}
