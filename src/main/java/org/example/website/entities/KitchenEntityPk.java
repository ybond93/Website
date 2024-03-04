package org.example.website.entities;

import java.io.Serializable;


public class KitchenEntityPk implements Serializable {
    private int orderId;
    private int menuItemId;
    public KitchenEntityPk(int orderId, int menuItemId){
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KitchenEntityPk)) return false;
        KitchenEntityPk that = (KitchenEntityPk) o;
        return menuItemId == that.menuItemId && orderId == that.orderId;
    }
}
