package org.example.website.entities;

import jakarta.persistence.*;

import java.util.List;

@NamedQuery(name = "Kitchen_ViewEntity.findAll", query = "SELECT l FROM KitchenViewEntity l WHERE l.statusOrder = FALSE")
@Entity
@Table(name = "KITCHEN_VIEW", schema = "restaurang", catalog = "")
@IdClass(org.example.website.entities.KitchenViewEntityPk.class)
public class KitchenViewEntity {
    @Id
    @Column(name = "ORDER_ID")
    private int orderId;
    @Basic
    @Column(name = "TABLE_NUM")
    private int tableNum;
    @Basic
    @Column(name = "STATUS_ORDER")
    private Boolean statusOrder;
    @Basic
    @Column(name = "STATUS_MAINS")
    private Boolean statusMains;
    @Basic
    @Column(name = "STATUS_START")
    private Boolean statusStart;
    @Id
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;
    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CATEGORY")
    private String category;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Boolean getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Boolean statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Boolean getStatusMains() {
        return statusMains;
    }

    public void setStatusMains(Boolean statusMains) {
        this.statusMains = statusMains;
    }

    public Boolean getStatusStart() {
        return statusStart;
    }

    public void setStatusStart(Boolean statusStart) {
        this.statusStart = statusStart;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitchenViewEntity that = (KitchenViewEntity) o;

        if (orderId != that.orderId) return false;
        if (tableNum != that.tableNum) return false;
        if (menuItemId != that.menuItemId) return false;
        if (statusOrder != null ? !statusOrder.equals(that.statusOrder) : that.statusOrder != null) return false;
        if (statusMains != null ? !statusMains.equals(that.statusMains) : that.statusMains != null) return false;
        if (statusStart != null ? !statusStart.equals(that.statusStart) : that.statusStart != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + tableNum;
        result = 31 * result + (statusOrder != null ? statusOrder.hashCode() : 0);
        result = 31 * result + (statusMains != null ? statusMains.hashCode() : 0);
        result = 31 * result + (statusStart != null ? statusStart.hashCode() : 0);
        result = 31 * result + menuItemId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }


}
