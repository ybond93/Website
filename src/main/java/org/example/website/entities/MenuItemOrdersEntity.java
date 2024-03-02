package org.example.website.entities;

import jakarta.persistence.*;
@NamedQuery(
        name = "MenuItemOrdersEntity.findAll",
        query = "SELECT l FROM MenuItemOrdersEntity l"
)
@Entity
@Table(name = "MENU_ITEM_ORDERS", schema = "restaurang", catalog = "")
@IdClass(org.example.website.entities.MenuItemOrdersEntityPK.class)
public class MenuItemOrdersEntity {
    @Id
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;

    @Id
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "AMOUNT")
    private int amount; // Added quantity field
    // Getters and setters

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private OrdersEntity order;

    // Bidirectional @ManyToOne association back to MenuItemsEntity
    @ManyToOne
    @JoinColumn(name = "MENU_ITEM_ID", insertable = false, updatable = false)
    private MenuItemsEntity menuItem;

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MenuItemsEntity getMenuItem() {
        return menuItem;
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public void setMenuItem(MenuItemsEntity menuItem) {
        this.menuItem = menuItem;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

}