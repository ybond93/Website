package org.example.website.dto;

import java.util.List;

public class TablesDTO {
    private int tableNum;
    private String Status;
    private List<Integer> orderIds;



    public int getTableNum() {
        return tableNum;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public String getStatus() {
        return Status;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}