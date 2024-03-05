package org.example.website.dto;

public class MenuItemOrderDTO {

    private MenuItemDTO menuItem;

    private OrdersDTO order;

    private int amount;

    public MenuItemOrderDTO(){};

    public MenuItemOrderDTO(MenuItemDTO menuItem, OrdersDTO order, int amount) {
        this.menuItem = menuItem;
        this.order = order;
        this.amount = amount;
    }

    public MenuItemDTO getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDTO menuItem) {
        this.menuItem = menuItem;
    }

    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
