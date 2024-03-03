package org.example.website.mapper;

import org.example.website.dto.MenuItemDTO;
import org.example.website.entities.MenuItemsEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsMapper {
    public static MenuItemDTO toDTO(MenuItemsEntity entity) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescr(entity.getDescr());
        // You can map other fields here if needed
        return dto;
    }

    public static MenuItemsEntity toEntity(MenuItemDTO dto) {
        MenuItemsEntity entity = new MenuItemsEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescr(dto.getDescr());
        // You can map other fields here if needed
        return entity;
    }

    public static List<MenuItemDTO> toDTOList(List<MenuItemsEntity> entities) {
        List<MenuItemDTO> dtos = new ArrayList<>();
        for (MenuItemsEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    public static List<MenuItemsEntity> toEntityList(List<MenuItemDTO> dtos) {
        List<MenuItemsEntity> entities = new ArrayList<>();
        for (MenuItemDTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
