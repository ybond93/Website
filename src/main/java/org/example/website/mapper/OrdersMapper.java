package org.example.website.mapper;


import org.example.website.dto.OrdersDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import org.example.website.entities.OrdersEntity;
import org.example.website.entities.TablesEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {

    public static OrdersDTO toDTO(OrdersEntity entity) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setTableNum(entity.getTableNum().getTableNum());


        return dto;
    }

    public static OrdersEntity toEntity(OrdersDTO dto, TablesEntity table) { //anta vi måste skicka table också?
        OrdersEntity entity = new OrdersEntity();

        entity.setOrderId(dto.getOrderId());
        entity.setTableNum(table);
        return entity;
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