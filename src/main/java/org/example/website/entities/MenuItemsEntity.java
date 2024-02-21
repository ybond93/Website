package org.example.website.entities;

import jakarta.persistence.*;

@NamedQuery(
        name = "MenuItemsEntity.findAll",
        query = "Select l FROM MenuItemsEntity l"
)
@Entity
@Table(name = "menu_items", schema = "restaurang", catalog = "")
public class MenuItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PRICE")
    private Double price;
    @Basic
    @Column(name = "DESCR")
    private String descr;

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
