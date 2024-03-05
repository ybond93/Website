package org.example.website.mapper;


import jakarta.persistence.Entity;
import org.example.website.dto.MenuItemQuantityDTO;
import org.example.website.dto.OrdersDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.MenuItemsEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {

    public static OrdersDTO toDTO(OrdersEntity entity) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setStatus(entity.getStatus());
        dto.setTable(TablesMapper.toDTO(entity.getTableNum()));


        /*List<MenuItemQuantityDTO> menuItemQuantities = entity.getMenuItemOrders().stream()
                .map(menuItemOrderEntity -> {
                    MenuItemQuantityDTO mqDto = new MenuItemQuantityDTO();
                    mqDto.setMenuItemId(menuItemOrderEntity.getMenuItem().getId());
                    mqDto.setAmount(menuItemOrderEntity.getAmount());
                    return mqDto;
                })
                .collect(Collectors.toList());
        dto.setMenuItemQuantities(menuItemQuantities);*/
        return dto;
    }

    public static OrdersEntity toEntity(OrdersDTO dto) {
        OrdersEntity orderEntity = new OrdersEntity();
        TablesDTO tablesDTO = new TablesDTO();
        tablesDTO.setStatus(dto.getTable().getStatus());
        tablesDTO.setTableNum(dto.getTable().getTableNum());

        orderEntity.setOrderId(dto.getOrderId());
        orderEntity.setStatus(dto.getStatus());
        orderEntity.setTableNum(TablesMapper.toEntity(tablesDTO));

        //TablesEntity tableEntity = TablesMapper.toEntity(tablesDTO);
        //orderEntity.setTableNum(tableEntity);
    /*if(!dto.getMenuItemQuantities().isEmpty()) {
        // Map MenuItemQuantityDTO list to MenuItemOrderEntity list
        List<MenuItemOrdersEntity> menuItemOrders = dto.getMenuItemQuantities().stream()
                .map(mqDto -> {
                    MenuItemOrdersEntity menuItemOrderEntity = new MenuItemOrdersEntity();
                    menuItemOrderEntity.setMenuItemId(mqDto.getMenuItemId());
                    menuItemOrderEntity.setAmount(mqDto.getAmount());

                    return menuItemOrderEntity;
                })
                .collect(Collectors.toList());
        entity.setMenuItemOrders(menuItemOrders);
    }*/
        return orderEntity;
    }

    /*public static OrdersEntity toEntity(OrdersDTO dto) {
        OrdersEntity entity = new OrdersEntity();
        TablesDTO tablesDTO = new TablesDTO();
        entity.setOrderId(dto.getOrderId());
        tablesDTO.setTableNum(dto.getTableNum());
        TablesEntity tableEntity = TablesMapper.toEntity(tablesDTO);
        entity.setTableNum(tableEntity);

        entity.setMenuItemOrders(MenuItemOrderMapper.toEntities(dto.getMenuItemQuantities()));

        return entity;
    }*/
}