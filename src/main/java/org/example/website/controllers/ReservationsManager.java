package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.ReservationsEntity;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

@Path("/reservations")
public class ReservationsManager {

    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getReservations() {
        List<ReservationsEntity> Reservations;
        Reservations = em.createNamedQuery("ReservationsEntity.findAll", ReservationsEntity.class).getResultList();
        return Response.ok(Reservations).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addReservation(ReservationsEntity Reservations) {
        em.persist(Reservations);
        em.flush(); // Optional, to ensure ID is generated
        return Response.ok(Reservations).build();
    }
}