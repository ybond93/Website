package org.example.website.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.example.website.controllers.AlacarteMenuItemsManager;

public class KitchenViewDTO {
    private int orderId;

    private int tableNum;

    private Boolean statusOrder;

    private Boolean statusMains;
    private Boolean statusStart;

    private int menuItemId;

    private Integer amount;

    private String name;

    private String category;


    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatusStart(Boolean statusStart) {
        this.statusStart = statusStart;
    }

    public void setStatusOrder(Boolean statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void setStatusMains(Boolean statusMains) {
        this.statusMains = statusMains;
    }

    public String getCategory() {
        return category;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Boolean getStatusMains() {
        return statusMains;
    }

    public Boolean getStatusOrder() {
        return statusOrder;
    }

    public Boolean getStatusStart() {
        return statusStart;
    }

    public int getTableNum() {
        return tableNum;
    }

    public Integer getAmount() {
        return amount;
    }

}
