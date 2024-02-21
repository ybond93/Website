package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.EventsEntity;

import java.util.List;

@Path("/events")
public class EventsManager {

    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getEvents() {
        List<EventsEntity> events;
        events = em.createNamedQuery("EventsEntity.findAll", EventsEntity.class).getResultList();
        return Response.ok(events).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addEvent(EventsEntity event) {
        em.persist(event);
        em.flush(); // Optional, to ensure ID is generated
        return Response.ok(event).build();
    }
}