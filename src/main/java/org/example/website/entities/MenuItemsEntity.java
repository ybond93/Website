package org.example.website.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "MENU_ITEMS", schema = "restaurang", catalog = "")
public class MenuItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "PRICE")
    private Double price;

    @Basic
    @Column(name = "DESCR", length = 200)
    private String descr;

    // Getters and Setters
    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(final Double price) { this.price = price;}

    public String getDescr() { return descr; }

    public void setDescr(final String descr) { this.descr = descr; }



}
