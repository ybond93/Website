package org.example.website.mapper;

import jakarta.persistence.criteria.Order;
import org.example.website.dto.*;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItemOrderMapper {

    public static MenuItemOrderDTO toDTO(MenuItemOrdersEntity entity) {
        MenuItemOrderDTO dto = new MenuItemOrderDTO();
        dto.setMenuItem(MenuItemsMapper.toDTO(entity.getMenuItem()));
        dto.setOrder(OrdersMapper.toDTO(entity.getOrder()));
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public static MenuItemOrdersEntity toEntity(MenuItemOrderDTO dto) {
        MenuItemOrdersEntity menuItemOrdersEntity = new MenuItemOrdersEntity();
        TablesDTO tablesDTO = new TablesDTO();
        OrdersDTO ordersDTO = new OrdersDTO();
        MenuItemDTO menuItemDTO = new MenuItemDTO();

        tablesDTO.setStatus(dto.getOrder().getTable().getStatus());
        tablesDTO.setTableNum(dto.getOrder().getTable().getTableNum());

        ordersDTO.setOrderId(dto.getOrder().getOrderId());
        ordersDTO.setStatus(dto.getOrder().getStatus());
        ordersDTO.setTable(tablesDTO);

        menuItemDTO.setId(dto.getMenuItem().getId());
        menuItemDTO.setName(dto.getMenuItem().getName());
        menuItemDTO.setPrice(dto.getMenuItem().getPrice());

        menuItemOrdersEntity.setId(0);
        menuItemOrdersEntity.setOrder(OrdersMapper.toEntity(ordersDTO));
        menuItemOrdersEntity.setMenuItem(MenuItemsMapper.toEntity(menuItemDTO));
        menuItemOrdersEntity.setAmount(dto.getAmount());

        return menuItemOrdersEntity;








    }

    /*
    public static List<MenuItemOrdersEntity> toEntities(List<MenuItemQuantityDTO> menuItemQuantities) {
        return menuItemQuantities.stream()
                .map(MenuItemOrderMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static MenuItemOrdersEntity toEntity(MenuItemQuantityDTO menuItemQuantityDTO) {
        MenuItemOrdersEntity menuItemOrderEntity = new MenuItemOrdersEntity();
        menuItemOrderEntity.setMenuItemId(menuItemQuantityDTO.getMenuItemId());
        menuItemOrderEntity.setAmount(menuItemQuantityDTO.getAmount());
        return menuItemOrderEntity;
    }
    */
}
