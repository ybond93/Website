package org.example.website.dto;


import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private int orderId;
    private Boolean statusOrder;
    private TablesDTO table;

    //private List<MenuItemQuantityDTO> menuItemQuantities;

    public OrdersDTO(){};

    public OrdersDTO(int orderId, Boolean statusOrder, TablesDTO table) {
        this.orderId = orderId;
        this.statusOrder = statusOrder;
        this.table = table;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public Boolean getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Boolean statusOrder) {
        this.statusOrder = statusOrder;
    }

    public TablesDTO getTable() {
        return table;
    }

    public void setTable(TablesDTO table) {
        this.table = table;
    }
}