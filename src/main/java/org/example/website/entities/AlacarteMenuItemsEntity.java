package org.example.website.entities;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "ALaCarteMenuItems.findStarters",
                query = "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem WHERE a.category = 'Starter'"),
        @NamedQuery(name = "ALaCarteMenuItems.findMains",
                query = "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem WHERE a.category = 'Main'"),
        @NamedQuery(name = "ALaCarteMenuItems.findDesserts",
                query = "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem WHERE a.category = 'Dessert'"),
        @NamedQuery(name = "ALaCarteMenuItems.findDrinks",
                query = "SELECT a FROM AlacarteMenuItemsEntity a JOIN FETCH a.menuItem WHERE a.category = 'Drink'")
})
@Entity
@Table(name = "alacarte_menu_items", schema = "restaurang", catalog = "")
public class AlacarteMenuItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CARTE_ITEM_ID")
    private int carteItemId;
    @Basic
    @Column(name = "CATEGORY")
    private String category;
    @OneToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "menu_item_id")
    private MenuItemsEntity menuItem;   // Foreign Key

    // getting the FK
    public MenuItemsEntity getMenuItem() {
        return menuItem;
    }

    // setting the FK
    public void setMenuItem(MenuItemsEntity menuItem) {
        this.menuItem = menuItem;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() { return carteItemId; }
}