package org.example.website.entities;

import jakarta.persistence.*;
@NamedQuery(
        name = "MenuItemOrdersEntity.findAll",
        query = "SELECT l FROM MenuItemOrdersEntity l"
)
@Entity
@jakarta.persistence.Table(name = "MENU_ITEM_ORDERS", schema = "restaurang", catalog = "")
@jakarta.persistence.IdClass(org.example.website.entities.MenuItemOrdersEntityPK.class)
public class MenuItemOrdersEntity {
    @Id
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;

    @Id
    @Column(name = "ORDER_ID")
    private int orderId;

    // Getters and setters

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

}
