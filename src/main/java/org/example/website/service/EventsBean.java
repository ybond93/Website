package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.website.entities.EventsEntity;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EventsBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private List<EventsEntity> eventsList;
    private EventsEntity event = new EventsEntity();

    @PostConstruct
    public void init() {
        // Fetch events from the database
        eventsList = em.createNamedQuery("EventsEntity.findAll", EventsEntity.class).getResultList();
    }

    @Transactional
    public void addEvent() {
        // Persist the new event to the database
        em.persist(event);
        // Clear the event object for the next entry
        event = new EventsEntity();
        // Refresh the events list
        init();
    }

    public EventsEntity getEvent() {
        return event;
    }

    public List<EventsEntity> getEvents() {
        return eventsList;
    }
}