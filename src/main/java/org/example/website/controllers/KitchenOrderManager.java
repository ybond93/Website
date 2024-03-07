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
import org.example.website.entities.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.website.mapper.*;

@Path("/KitchenOrder")
public class KitchenOrderManager {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    @Transactional
    public Response getKitchen() {
        //em.clear();
        //em.flush();

        List<KitchenViewEntity> kitchenList;
        kitchenList= em.createNamedQuery("Kitchen_ViewEntity.findAll", KitchenViewEntity.class).getResultList();
        //em.refresh(kitchenList);
        List<KitchenOrderDTO> kitchenOrderDTOS = KitchenOrderMapper.mapListToDTO(kitchenList);

        return Response.ok(kitchenOrderDTOS).build();
    }

    /*@PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Transactional
    public Response updateTableStatus(@PathParam("id") int id, TablesEntity status) {
        TablesEntity existingTable = em.find(TablesEntity.class, id);
        if (existingTable == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTable.setStatus(status.getStatus());
        em.merge(existingTable);
        return Response.ok().build();
    }*/
}