package org.example.website.mapper;

import org.example.website.dto.AlacarteMenuItemsDTO;
import org.example.website.entities.AlacarteMenuItemsEntity;

public class AlacarteMenuItemsMapper {
    public static AlacarteMenuItemsDTO toDTO(AlacarteMenuItemsEntity entity) {
        AlacarteMenuItemsDTO dto = new AlacarteMenuItemsDTO();
        dto.setCarteItemId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setMenuItemName(entity.getMenuItem().getName());
        return dto;
    }

    public static AlacarteMenuItemsEntity toEntity(AlacarteMenuItemsDTO dto) {
        AlacarteMenuItemsEntity entity = new AlacarteMenuItemsEntity();
        entity.setCarteItemId(dto.getCarteItemId());
        entity.setCategory(dto.getCategory());
        return entity;
    }
}
