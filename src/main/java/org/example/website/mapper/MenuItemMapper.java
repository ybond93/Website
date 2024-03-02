package org.example.website.mapper;

import org.example.website.dto.EmployeesDTO;
import org.example.website.dto.MenuItemQuantityDTO;
import org.example.website.dto.MenuItemsDTO;
import org.example.website.entities.EmployeesEntity;

public class MenuItemMapper {
    public static MenuItemsDTO toDTO(MenuItemsDTO employee) {
        MenuItemsDTO dto = new MenuItemsDTO();
        dto.setMenuItemId(employee.getMenuItemId());
        dto.setCategory(employee.getCategory());
        dto.setName(employee.getName());
        dto.setDescription(employee.getDescription());
        dto.setPrice(employee.getPrice());
        return dto;
    }

    // Will be used for updating and inserting data later on
    public static MenuItemsDTO toEntity(MenuItemsDTO dto) {
        MenuItemsDTO entity = new MenuItemsDTO();
        entity.setMenuItemId(dto.getMenuItemId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setCategory(dto.getCategory());
        return entity;
    }

}
