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
import org.example.website.entities.ReservationsEntity;
import java.io.Serializable;
import java.util.List;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;

@Named
@RequestScoped
public class ReservationsBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    // for fetching
    private List<ReservationsEntity> reservationsList;

    // creates a **direct binding** from form to menu items entity
    private ReservationsEntity reservation = new ReservationsEntity();

    @PostConstruct
    public void init() {
        reservationsList = em.createNamedQuery("ReservationsEntity.findAll", ReservationsEntity.class).getResultList();
    }

    public ReservationsEntity getReservation() { return reservation; }

    public List<ReservationsEntity> getReservations() {
        return reservationsList;
    }

    @Transactional
    public void addReservation() {
        em.persist(reservation);
        // Reset the newMenuItem for the next entry
        reservation = new ReservationsEntity();
        init();
    }
}