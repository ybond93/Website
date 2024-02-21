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
import org.example.website.entities.ReservationsEntity;
import java.io.Serializable;
import java.util.List;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;

@Named
@RequestScoped
public class ReservationsBean implements Serializable {

    // for fetching
    private List<ReservationsEntity> reservationsList;

    // creates a **direct binding** from form to menu items entity
    private ReservationsEntity reservation = new ReservationsEntity();

    @PostConstruct
    public void init() {
        try (Client client = ClientBuilder.newClient()) {
            this.reservationsList = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/reservations")
                    .request()
                    .get(new GenericType<List<ReservationsEntity>>() {
                    });
        }
    }

    public ReservationsEntity getReservation() { return reservation; }

    public List<ReservationsEntity> getReservations() {
        return reservationsList;
    }

    public void addReservation() {
        try (Client client = ClientBuilder.newClient()) {
            try (Response response = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/reservations")
                    .request()
                    .post(Entity.json(reservation))) {

                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    // Handle success (e.g., adding to the local list, showing success message)
                    reservationsList.add(reservation);
                } else {
                    // Handle error
                }
            }
        }
        // Reset the newMenuItem for the next entry
        reservation = new ReservationsEntity();
    }
}