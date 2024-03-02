package org.example.website.dto;

public class MenuItemQuantityDTO {

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
}
