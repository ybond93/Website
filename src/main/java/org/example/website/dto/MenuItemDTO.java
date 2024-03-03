package org.example.website.dto;

public class MenuItemDTO {
    private int id;

    private String name;

    private Double price;

    private String descr;
    public MenuItemDTO(){};
    public MenuItemDTO(int id, String name, Double price, String descr) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
