package org.example.website.mapper;

import org.example.website.dto.OrdersDTO;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.entities.OrdersEntity;

import java.util.stream.Collectors;

public class OrdersMapper {

    // MANY-TO-MANY ---> MENU_ITEMS
    public static OrdersDTO toDTO(OrdersEntity entity) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(entity.getOrderId());
        if (entity.getTableNum() != null) {
            dto.setTableNum(entity.getTableNum().getTableNum());
        }
        dto.setMenuItems(entity.getMenuItems().stream()
                .map(MenuItemsEntity::getId) // Assuming MenuItemsEntity has getId()
                .collect(Collectors.toList()));
        return dto;
    }
}