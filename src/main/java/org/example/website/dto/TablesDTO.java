package org.example.website.dto;

import java.util.List;

public class TablesDTO {
    private int tableNum;
    private Boolean Status;
    //private List<Integer> orderIds;

    public TablesDTO(){};
    public TablesDTO(int tableNum, Boolean status) {
        this.tableNum = tableNum;
        Status = status;
        //this.orderIds = orderIds;
    }

    public int getTableNum() {
        return tableNum;
    }


    public Boolean getStatus() {
        return Status;
    }


    public void setStatus(Boolean status) {
        Status = status;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}