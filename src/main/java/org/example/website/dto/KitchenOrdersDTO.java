package org.example.website.dto;

import org.example.website.entities.TablesEntity;

import java.util.List;

public class KitchenOrdersDTO {
    private List<OrdersDTO> ordersDTO;
    private List<TablesDTO> tablesDTO;
    private List<AlacarteMenuItemsDTO> alacarteMenuItemsDTOs;
    private List<MenuItemOrderDTO> menuItemOrderDTOList;
    public KitchenOrdersDTO(){};

    public KitchenOrdersDTO(List<OrdersDTO> ordersDTO, List<TablesDTO> tablesDTO, List<AlacarteMenuItemsDTO> alacarteMenuItemsDTOs, List<MenuItemOrderDTO> menuItemOrderDTOList) {
        this.ordersDTO = ordersDTO;
        this.tablesDTO = tablesDTO;
        this.alacarteMenuItemsDTOs = alacarteMenuItemsDTOs;
        this.menuItemOrderDTOList = menuItemOrderDTOList;
    }

    public List<OrdersDTO> getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(List<OrdersDTO> ordersDTO) {
        this.ordersDTO = ordersDTO;
    }

    public List<TablesDTO> getTablesDTO() {
        return tablesDTO;
    }

    public void setTablesDTO(List<TablesDTO> tablesDTO) {
        this.tablesDTO = tablesDTO;
    }

    public List<AlacarteMenuItemsDTO> getAlacarteMenuItemsDTOs() {
        return alacarteMenuItemsDTOs;
    }

    public void setAlacarteMenuItemsDTOs(List<AlacarteMenuItemsDTO> alacarteMenuItemsDTOs) {
        this.alacarteMenuItemsDTOs = alacarteMenuItemsDTOs;
    }

    public List<MenuItemOrderDTO> getMenuItemOrderDTO() {
        return menuItemOrderDTOList;
    }

    public void setMenuItemOrderDTO(List<MenuItemOrderDTO> menuItemOrderDTOList) {
        this.menuItemOrderDTOList = menuItemOrderDTOList;
    }
}
