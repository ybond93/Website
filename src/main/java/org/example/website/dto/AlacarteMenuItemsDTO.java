package org.example.website.dto;

public class AlacarteMenuItemsDTO {
    private int carteItemId;
    private String category;
    private MenuItemDTO menuItem;
    public AlacarteMenuItemsDTO (){};
    public AlacarteMenuItemsDTO(int carteItemId, String category, MenuItemDTO menuItem) {
        this.carteItemId = carteItemId;
        this.category = category;
        this.menuItem = menuItem;
    }

    public int getCarteItemId() {
        return carteItemId;
    }

    public void setCarteItemId(int carteItemId) {
        this.carteItemId = carteItemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MenuItemDTO getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDTO menuItem) {
        this.menuItem = menuItem;
    }
}
