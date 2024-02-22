package org.example.website.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "MENU_ITEMS")
public class MenuItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "descr", length = 200)
    private String descr;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(final Double price) { this.price = price;}

    public String getDescr() { return descr; }
    public void setDescr(final String descr) { this.descr = descr; }
}
