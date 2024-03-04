package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@NamedQueries({
        @NamedQuery(
                name = "OrdersEntity.findAll", query = "Select l FROM OrdersEntity l"
        ),
        @NamedQuery(
                name = "OrdersEntity.getAllFromTable",
                query = "SELECT o FROM OrdersEntity o WHERE o.tableNum.tableNum = :table"
        )
})
@Entity
@Table(name = "ORDERS", schema = "restaurang", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "TABLE_NUM")
    private TablesEntity tableNum;
    @OneToMany(mappedBy = "order")
    private List<MenuItemOrdersEntity> menuItemOrders;
    // Constructors, getters, and setters



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