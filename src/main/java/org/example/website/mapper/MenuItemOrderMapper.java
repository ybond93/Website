package org.example.website.mapper;

import org.example.website.dto.MenuItemQuantityDTO;
import org.example.website.entities.MenuItemOrdersEntity;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemOrderMapper {

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
}
