package org.example.website.mapper;

import org.example.website.dto.MenuItemOrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItemOrdersMapper {
    // Converts an entity to a DTO
    public static MenuItemOrdersDTO toDTO(MenuItemOrdersEntity entity) {
        MenuItemOrdersDTO dto = new MenuItemOrdersDTO();
        dto.setMenuItemId(entity.getMenuItemId());
        dto.setOrderId(entity.getOrderId());
        dto.setQuantity(entity.getAmount());
        return dto;
    }

    // Converts a DTO to an entity
    public static MenuItemOrdersEntity toEntity(MenuItemOrdersDTO dto) {
        MenuItemOrdersEntity entity = new MenuItemOrdersEntity();
        entity.setMenuItemId(dto.getMenuItemId());
        entity.setOrderId(dto.getOrderId());
        entity.setAmount(dto.getQuantity());
        // Note: You would need to set the related MenuItem and Order entities as well
        // This might involve fetching them from the database by their IDs
        return entity;
    }
}
