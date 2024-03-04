package org.example.website.dto;

import org.example.website.entities.MenuItemOrdersEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// fixa named queries med table id X
public class OrdersDTO {

    private int orderId;
    private int tableNum;


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



}