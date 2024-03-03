package org.example.website.dto;

public class MenuItemOrderDTO {

    private int menuItemId;

    private int orderId;

    private int amount;

    public MenuItemOrderDTO(){};
    public MenuItemOrderDTO(int menuItemId, int orderId, int amount) {
        this.menuItemId = menuItemId;
        this.orderId = orderId;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
