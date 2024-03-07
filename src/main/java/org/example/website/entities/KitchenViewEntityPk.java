package org.example.website.entities;

import java.io.Serializable;


public class KitchenViewEntityPk implements Serializable {
    private int orderId;
    private int menuItemId;
    public KitchenViewEntityPk(int orderId, int menuItemId){
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public int getOrderId() {
        return orderId;
    }

}