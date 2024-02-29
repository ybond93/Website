package org.example.website.entities;

import java.io.Serializable;
import java.util.Objects;

public class MenuItemOrdersEntityPK implements Serializable {
    private int menuItemId;
    private int orderId;

    // Constructors, getters, setters, equals, and hashCode

    public MenuItemOrdersEntityPK() {}

    public MenuItemOrdersEntityPK(int menuItemId, int orderId) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemOrdersEntityPK)) return false;
        MenuItemOrdersEntityPK that = (MenuItemOrdersEntityPK) o;
        return menuItemId == that.menuItemId && orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemId, orderId);
    }
}