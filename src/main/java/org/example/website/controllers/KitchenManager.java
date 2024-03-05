package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.KitchenViewDTO;
import org.example.website.dto.OrdersDTO;
import org.example.website.entities.AlacarteMenuItemsEntity;
import org.example.website.entities.KitchenViewEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.mapper.KitchenViewMapper;
import org.example.website.mapper.OrdersMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/kitchen")
public class KitchenManager {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getKitchen() {
        List<KitchenViewEntity> kitchenList;
        kitchenList= em.createNamedQuery("Kitchen_ViewEntity.findAll", KitchenViewEntity.class).getResultList();

        kitchenList.forEach(kitchenView-> {
            List<String> cat = em.createNamedQuery("ALaCarteMenuItems.findByID", String.class)
                    .setParameter("id", kitchenView.getMenuItemId())
                    .getResultList();
            //String save = cat.get(0).getCategory();
            kitchenView.setCategory(cat.get(0));

        } );
        List<KitchenViewDTO> kitchenVievList = kitchenList.stream()
                .map(KitchenViewMapper::toDTO)
                .collect(Collectors.toList());
        List<OrdersEntity> ordersList;
        ordersList = em.createNamedQuery("OrdersEntity.findAll", OrdersEntity.class).getResultList();
        /*
        List<OrdersDTO> ordersDTOs = ordersList.stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());
       */

        // Group KitchenViewEntity instances by ORDER_ID
        Map<Integer, List<KitchenViewEntity>> groupedByOrderId = kitchenList.stream()
                .collect(Collectors.groupingBy(KitchenViewEntity::getOrderId));

        // Build the response with the grouped data
        return Response.ok(groupedByOrderId).build();

        // Build the response
      //  return Response.ok(combinedResponse).build();
    }

}
