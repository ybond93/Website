package org.example.website.dto;


import org.example.website.entities.TablesEntity;

import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private int orderId;
    private TablesDTO tableNum;
    private List<MenuItemOrdersDTO> menuItemsOrders;


    public TablesDTO getTableNum() {return  tableNum;}

    public int getOrderId() {
        return orderId;
    }

    public void setTableNum(TablesDTO tableNum) {
        this.tableNum = tableNum;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


}