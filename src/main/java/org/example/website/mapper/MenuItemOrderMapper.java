package org.example.website.mapper;

import org.example.website.dto.MenuItemOrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemOrderMapper {
    public static MenuItemOrdersDTO entityToDTO(MenuItemOrdersEntity entity) {
        MenuItemOrdersDTO dto = new MenuItemOrdersDTO();
        dto.setItemId(entity.getMenuItemId());
        dto.setOrderId(entity.getOrderId());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public static MenuItemOrdersEntity dtoToEntity(MenuItemOrdersDTO dto) {
        MenuItemOrdersEntity entity = new MenuItemOrdersEntity();
        entity.setMenuItemId(dto.getItemId());
        entity.setOrderId(dto.getOrderId());
        entity.setAmount(dto.getAmount());
        // Setting the MenuItemsEntity and OrdersEntity based on IDs would typically be handled separately,
        // as it often requires fetching the respective entities from the database.
        return entity;
    }
}
