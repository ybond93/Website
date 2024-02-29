package org.example.website.mapper;

import org.example.website.dto.OrdersDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {

    public static OrdersDTO toDTO(OrdersEntity entity) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setTableNum(entity.getTableNum().getTableNum());

        List<OrdersDTO.MenuItemQuantityDTO> menuItemQuantities = entity.getMenuItemOrders().stream()
                .map(menuItemOrderEntity -> {
                    OrdersDTO.MenuItemQuantityDTO mqDto = new OrdersDTO.MenuItemQuantityDTO();
                    mqDto.setMenuItemId(menuItemOrderEntity.getMenuItem().getId());
                    mqDto.setAmount(menuItemOrderEntity.getAmount());
                    return mqDto;
                })
                .collect(Collectors.toList());

        dto.setMenuItemQuantities(menuItemQuantities);


        return dto;
    }
}
