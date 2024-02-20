package org.example.website.Beans;

import org.example.website.Modles.EventsEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EventsBean implements Serializable {
    // for fetching
    private List<EventsEntity> events;

    // creates a **direct binding** from form to menu items entity
    private EventsEntity event = new EventsEntity();

    @PostConstruct
    public void init() {
        try (Client client = ClientBuilder.newClient()) {
            this.events = client.target("http://localhost:8080/adminWebsite/api/events")
                    .request()
                    .get(new GenericType<List<EventsEntity>>() {
                    });
        }
    }

    public EventsEntity getEvent() { return event; }

    public List<EventsEntity> getEvents() {
        return events;
    }

    public void addEvent() {
        try (Client client = ClientBuilder.newClient()) {
            try (Response response = client.target("http://localhost:8080/adminWebsite/api/events")
                    .request()
                    .post(Entity.json(event))) {

                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    // Handle success (e.g., adding to the local list, showing success message)
                    events.add(event);
                } else {
                    // Handle error
                }
            }
        }
        // Reset the newMenuItem for the next entry
        event = new EventsEntity();
    }
}
