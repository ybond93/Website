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
import org.example.website.entities.WorkShiftsEntity;

@Named
@RequestScoped
public class EventsBean implements Serializable {

    // for fetching
    private List<EventsEntity> eventsList;

    // creates a **direct binding** from form to menu items entity
    private EventsEntity event = new EventsEntity();

    @PostConstruct
    public void init() {
        try (Client client = ClientBuilder.newClient()) {
            this.eventsList = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/events")
                    .request()
                    .get(new GenericType<List<EventsEntity>>() {
                    });
        }
    }

    public EventsEntity getEvent() { return event; }

    public List<EventsEntity> getEvents() {
        return eventsList;
    }

    public void addEvent() {
        try (Client client = ClientBuilder.newClient()) {
            try (Response response = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/events")
                    .request()
                    .post(Entity.json(event))) {

                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    // Handle success (e.g., adding to the local list, showing success message)
                    eventsList.add(event);
                } else {
                    // Handle error
                }
            }
        }
        // Reset the newMenuItem for the next entry
        event = new EventsEntity();
    }
}