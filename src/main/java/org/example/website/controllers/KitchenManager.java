package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeesDTO;
import org.example.website.dto.KitchenDTO;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.KitchenEntity;
import org.example.website.mapper.EmployeesMapper;

import java.util.List;
import java.util.stream.Collectors;

@Path("/kitchen")
public class KitchenManager {
    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getKitchen() {
        List<KitchenEntity> kitchenList;
        kitchenList= em.createNamedQuery("KitchenEntity.findAll", KitchenEntity.class).getResultList();

        return Response.ok(kitchenList).build();
    }

}
