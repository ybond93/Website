package org.example.website.dto;

import java.util.List;

public class TablesDTO {
    private int tableNum;
    private String Status;
    //private List<Integer> orderIds;

    public TablesDTO(){};
    public TablesDTO(int tableNum, String status) {
        this.tableNum = tableNum;
        Status = status;
        //this.orderIds = orderIds;
    }

    public int getTableNum() {
        return tableNum;
    }


    public String getStatus() {
        return Status;
    }


    public void setStatus(String status) {
        Status = status;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}