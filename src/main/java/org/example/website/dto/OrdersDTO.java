package org.example.website.dto;


import org.example.website.entities.MenuItemOrdersEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdersDTO {

    private int orderId;
    private int tableNum;
    private List<MenuItemOrdersEntity> menuItemOrders = new ArrayList<>();

    public int getTableNum() {
        return tableNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void addMenuItemOrder(MenuItemOrdersEntity menuItemOrder) {
        if (menuItemOrders == null) {
            menuItemOrders = new ArrayList<>();
        }
        menuItemOrders.add(menuItemOrder);
    }

    public List<MenuItemOrdersEntity> getMenuItemOrders() {
        return menuItemOrders;
    }
}