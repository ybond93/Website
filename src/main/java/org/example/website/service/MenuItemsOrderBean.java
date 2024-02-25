package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.website.entities.EventsEntity;
import org.example.website.entities.MenuItemOrdersEntity;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MenuItemsOrderBean implements Serializable  {
    @PersistenceContext
    private EntityManager em;
    private List<MenuItemOrdersEntity> menuItemOrdersList;
    private MenuItemOrdersEntity menuItemOrder;
    @PostConstruct
    public void init() {
        menuItemOrdersList = em.createNamedQuery("MenuItemOrdersEntity.findAll", MenuItemOrdersEntity.class).getResultList();
    }

    public  MenuItemOrdersEntity getMenuItemOrder(){
        return  menuItemOrder;
    }
    public  List<MenuItemOrdersEntity> getMenuItemOrders(){
        return  menuItemOrdersList;
    }


}
