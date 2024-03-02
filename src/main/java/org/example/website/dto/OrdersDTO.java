package org.example.website.dto;


import java.util.ArrayList;
import java.util.List;

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