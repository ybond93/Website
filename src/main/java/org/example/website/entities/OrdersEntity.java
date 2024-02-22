package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS", schema = "restaurang", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private int orderId;
    @Basic
    @Column(name = "EMP_ID")
    private int empId;
    @Basic
    @Column(name = "TABLE_NUM")
    private int tableNum;
    @ManyToMany
    @JoinTable(
            name = "MENU_ITEM_ORDERS",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItemsEntity> menuItems = new ArrayList<>();

    // Constructors, getters, and setters



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }


}
