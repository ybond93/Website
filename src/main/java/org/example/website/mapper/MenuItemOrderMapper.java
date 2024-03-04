package org.example.website.mapper;

import org.example.website.dto.MenuItemOrdersDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItemOrderMapper {
    public static MenuItemOrdersDTO entityToDTO(MenuItemOrdersEntity entity) {
        MenuItemOrdersDTO dto = new MenuItemOrdersDTO();
        dto.setItemId(MenuItemMapper.entityToDTO(entity.getMenuItem()));
        //TablesMapper.toDTO(entity.getTableNum())

        dto.setOrderId(OrdersMapper.toDTO(entity.getOrder()));
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public static MenuItemOrdersEntity dtoToEntity(MenuItemOrdersDTO dto) {
        MenuItemOrdersEntity entity = new MenuItemOrdersEntity();
        //MenuItemsEntity menuItemsEntity = new MenuItemsEntity();
        //menuItemsEntity.setId();
        entity.setOrderId(dto.getOrderId().getOrderId());
        entity.setAmount(dto.getAmount());
        // Setting the MenuItemsEntity and OrdersEntity based on IDs would typically be handled separately,
        // as it often requires fetching the respective entities from the database.
        return entity;
    }
}
