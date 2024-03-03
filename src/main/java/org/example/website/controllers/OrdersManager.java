package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.OrdersDTO;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;
import org.example.website.mapper.OrdersMapper;
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
    @GET
    @Path("/table")
    @Produces("application/json")
    public Response getOrdersFromTable(@QueryParam("tableNr") int tableNr) {

        List<OrdersEntity> ordersList;
        ordersList = em.createNamedQuery("OrdersEntity.getAllFromTable", OrdersEntity.class)
                .setParameter("table", tableNr)
                .getResultList();
        List<OrdersDTO> ordersDTOs = ordersList.stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());
        return Response.ok(ordersDTOs).build();
    }
    // will be used for inserting and updating data from the API

    @POST
    @Consumes("application/json")
    @Transactional
    public Response createOrder(OrdersDTO orderDTO) {
        TablesEntity tables = new TablesEntity();
        tables.setTableNum(orderDTO.getTableNum());
        OrdersEntity newOrderEntity = OrdersMapper.toEntity(orderDTO);
        em.persist(newOrderEntity);
        return Response.created(URI.create("/orders/" + newOrderEntity.getOrderId())).build();
    }
}