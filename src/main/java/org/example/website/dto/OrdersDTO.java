package org.example.website.dto;


import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private int orderId;
    private int tableNum;
    private String status;
    //private List<MenuItemQuantityDTO> menuItemQuantities;
    public OrdersDTO(){};
    public OrdersDTO(int orderId, int tableNum, String status) {
        this.orderId = orderId;
        this.tableNum = tableNum;
        this.status = status;
        //this.menuItemQuantities = menuItemQuantities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    /*public List<MenuItemQuantityDTO> getMenuItemQuantities() {
        return menuItemQuantities;
    }

    public void setMenuItemQuantities(List<MenuItemQuantityDTO> menuItemQuantities) {
        this.menuItemQuantities = menuItemQuantities;
    }*/
}