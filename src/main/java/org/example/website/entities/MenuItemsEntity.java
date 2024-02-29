package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@NamedQuery(
        name = "MenuItemsEntity.findAll",
        query = "Select l FROM MenuItemsEntity l"
)
@Entity
@Table(name = "MENU_ITEMS", schema = "restaurang", catalog = "")
public class MenuItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "PRICE")
    private Double price;

    @Basic
    @Column(name = "DESCR", length = 200)
    private String descr;

    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemOrdersEntity> menuItemOrders;
    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(final Double price) { this.price = price;}

    public String getDescr() { return descr; }

    public void setDescr(final String descr) { this.descr = descr; }

    public List<MenuItemOrdersEntity> getMenuItemOrders() {
        return menuItemOrders;
    }

    public void setMenuItemOrders(List<MenuItemOrdersEntity> menuItemOrders) {
        this.menuItemOrders = menuItemOrders;
    }

    public void setId(int id) {
        this.id = id;
    }

}