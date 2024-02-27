package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.website.entities.EventsEntity;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemOrdersEntityPK;
import org.example.website.entities.OrdersEntity;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MenuItemsOrderBean implements Serializable  {
    @PersistenceContext
    private EntityManager em;
    private List<MenuItemOrdersEntity> menuItemOrdersList;
    private MenuItemOrdersEntity menuItemOrder;
    private List<OrdersEntity> ordeList;
    private OrdersEntity order;
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
    public OrdersEntity getOrder(){ return  order;}
    public List<OrdersEntity> getOrdeList(){ return ordeList;}
    @Transactional
    public void addItemToOrder(int menuItemId, int orderId) {
        MenuItemOrdersEntityPK pk = new MenuItemOrdersEntityPK(menuItemId, orderId);
        MenuItemOrdersEntity menuItemOrder = em.find(MenuItemOrdersEntity.class, pk);

        if (menuItemOrder == null) {
            menuItemOrder = new MenuItemOrdersEntity();
            menuItemOrder.setMenuItemId(menuItemId);
            menuItemOrder.setOrderId(orderId);// Set the primary key
            // Initialize other necessary properties of menuItemOrder
            em.persist(menuItemOrder);
        } else {
            // Handle the case where the item-order relationship already exists.
            // For example, update quantities or other relevant fields.
        }
    }
}
