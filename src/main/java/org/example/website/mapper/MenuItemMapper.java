package org.example.website.mapper;

import org.example.website.dto.MenuItemsDTO;
import org.example.website.entities.MenuItemsEntity;

public class MenuItemMapper {
    public static MenuItemsDTO entityToDTO(MenuItemsEntity entity) {
        MenuItemsDTO dto = new MenuItemsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescr(entity.getDescr());
        return dto;
    }

    public static MenuItemsEntity dtoToEntity(MenuItemsDTO dto) {
        MenuItemsEntity entity = new MenuItemsEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescr(dto.getDescr());
        return entity;
    }
}

