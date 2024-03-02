package org.example.website.dto;

public class MenuItemOrdersDTO {
    private int menuItemId;
    private int orderId;
    private int quantity;

    // Constructors
    public MenuItemOrdersDTO() {
    }

    public MenuItemOrdersDTO(int menuItemId, int orderId, int quantity) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString, equals, and hashCode methods can be overridden as needed
}
