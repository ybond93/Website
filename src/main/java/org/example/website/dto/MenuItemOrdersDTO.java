package org.example.website.dto;

public class MenuItemOrdersDTO {
    private int itemId;
    private  int orderId;
    private  int amount;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getAmount() {
        return amount;
    }

    public int getItemId() {
        return itemId;
    }
}
