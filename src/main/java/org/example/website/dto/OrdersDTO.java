package org.example.website.dto;


import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private int orderId;

    private int tableNum;

    private List<Integer> menuItems;

    public int getTableNum() {
        return tableNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Integer> getMenuItems() {
        return menuItems;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public void setMenuItems(List<Integer> menuItems) {
        this.menuItems = menuItems;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
