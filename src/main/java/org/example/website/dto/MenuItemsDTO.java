package org.example.website.dto;

public class MenuItemsDTO {
    private int menuItemId;
    private String name;
    private String description;
    private double price;
    private String category; // Assuming category is a string. If it's an entity, use CategoryDTO

    // plus constructors, getters, and setters

    public int getMenuItemId() {
        return menuItemId;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
}

