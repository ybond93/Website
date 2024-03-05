package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@NamedQueries({
        @NamedQuery(name = "OrdersEntity.findAll",
                query = "Select l FROM OrdersEntity l"),

})
@Entity
@Table(name = "ORDERS", schema = "restaurang", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private int orderId;
    @Basic
    @Column(name = "STATUS_ORDER")
    private Boolean statusOrder;
    @Basic
    @Column(name = "STATUS_MAINS")
    private Boolean statusMains;
    @Basic
    @Column(name = "STATUS_START")
    private Boolean statusStart;
    @ManyToOne
    @JoinColumn(name = "TABLE_NUM")
    private TablesEntity tableNum;
    @OneToMany(mappedBy = "order")
    private List<MenuItemOrdersEntity> menuItemOrders;
    // Constructors, getters, and setters


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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public TablesEntity getTableNum() {
        return tableNum;
    }

    public void setTableNum(TablesEntity tableNum) {
        this.tableNum = tableNum;
    }

    public void setMenuItemOrders(List<MenuItemOrdersEntity> menuItemOrders) {
        this.menuItemOrders = menuItemOrders;
    }

    public List<MenuItemOrdersEntity> getMenuItemOrders() {
        return menuItemOrders;
    }
}