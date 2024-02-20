package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import org.example.website.entities.EventsEntity;
import java.io.Serializable;
import java.util.List;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;

@Named
@RequestScoped
public class EventsBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private List<EventsEntity> eventsList;
    private EventsEntity event = new EventsEntity();

    @PostConstruct
    public void init() {
        em.createNamedQuery("EventsEntity.findAll", EventsEntity.class).getResultList();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public void addEvent() {
        // Persist the new event to the database
        em.persist(event);
        // Clear the event object for the next entry
        event = new EventsEntity();
        // Refresh the events list
        init();
    }

    public EventsEntity getEvent() { return event; }

    public List<EventsEntity> getEvents() { return eventsList; }
}