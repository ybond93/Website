package org.example.website.mapper;

import org.example.website.dto.*;

import java.util.List;

public class KitchenOrdersMapper {

    public static KitchenOrdersDTO toKitchenOrdersDTO(List<OrdersDTO> ordersDTO, List<TablesDTO> tablesDTO, List<AlacarteMenuItemsDTO> alacarteMenuItemsDTO, List<MenuItemOrderDTO> menuItemOrderDTO) {
        KitchenOrdersDTO kitchenOrdersDTO = new KitchenOrdersDTO();
        kitchenOrdersDTO.setOrdersDTO(ordersDTO);
        kitchenOrdersDTO.setTablesDTO(tablesDTO);
        kitchenOrdersDTO.setAlacarteMenuItemsDTOs(alacarteMenuItemsDTO);
        kitchenOrdersDTO.setMenuItemOrderDTO(menuItemOrderDTO);
        return kitchenOrdersDTO;
    }
}
