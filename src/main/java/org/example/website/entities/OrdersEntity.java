package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@NamedQuery(
        name = "OrdersEntity.findAll",
        query = "Select l FROM OrdersEntity l"
)
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

    public TablesEntity getTableNum() {
        return tableNum;
    }

    public void setTableNum(TablesEntity tableNum) {
        this.tableNum = tableNum;
    }

    public List<MenuItemsEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemsEntity> menuItems) {
        this.menuItems = menuItems;
    }
}