package org.example.website.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kitchen", schema = "restaurang", catalog = "")
public class KitchenEntity {
    @Basic
    @Column(name = "TABLE_NUM")
    private int tableNum;
    @Basic
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;
    @Basic
    @Column(name = "ORDER_ID")
    private int orderId;
    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;
    @Basic
    @Column(name = "NAME")
    private String name;

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitchenEntity that = (KitchenEntity) o;

        if (tableNum != that.tableNum) return false;
        if (menuItemId != that.menuItemId) return false;
        if (orderId != that.orderId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableNum;
        result = 31 * result + menuItemId;
        result = 31 * result + orderId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
