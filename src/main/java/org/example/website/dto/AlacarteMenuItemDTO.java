package org.example.website.dto;

public class AlacarteMenuItemDTO {
    private String category;
    private String menuItemName;

    // Constructors, getters, and setters
    public AlacarteMenuItemDTO(String category, String menuItemName) {
        this.category = category;
        this.menuItemName = menuItemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }
}