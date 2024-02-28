package org.example.website.dto;


import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private int orderId;

    private int tableNum;
    private List<MenuItemQuantityDTO> menuItemQuantities;
    public static class MenuItemQuantityDTO {
        private int menuItemId;
        private int amount;
        public MenuItemQuantityDTO() {}


        public MenuItemQuantityDTO(int menuItemId, int amount) {
            this.menuItemId = menuItemId;
            this.amount = amount;
        }
        public int getMenuItemId() {
            return menuItemId;
        }

        public int getAmount() {
            return amount;
        }

        public void setMenuItemId(int menuItemId) {
            this.menuItemId = menuItemId;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
        // Getters and setters
        // ...
    }
    public int getTableNum() {
        return tableNum;
    }

    public int getOrderId() {
        return orderId;
    }


    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }



    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<MenuItemQuantityDTO> getMenuItemQuantities() {
        return menuItemQuantities;
    }

    public void setMenuItemQuantities(List<MenuItemQuantityDTO> menuItemQuantities) {
        this.menuItemQuantities = menuItemQuantities;
    }
}
