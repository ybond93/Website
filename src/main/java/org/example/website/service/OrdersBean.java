package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.website.entities.EventsEntity;
import org.example.website.entities.OrdersEntity;

import java.io.Serializable;
import java.util.List;
@Named
@RequestScoped
public class OrdersBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private List<OrdersEntity> orderList;
    private OrdersEntity order = new OrdersEntity();

    @PostConstruct
    public void init() {
        // Fetch events from the database
        orderList = em.createNamedQuery("OrdersEntity.findAll", OrdersEntity.class).getResultList();
    }

    @Transactional
    public void addOrder() {
        // Persist the new event to the database
        em.persist(order);
        // Clear the event object for the next entry
        order = new OrdersEntity();
        // Refresh the events list
        init();
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public List<OrdersEntity> getOrders() {
        return orderList;
    }
}
