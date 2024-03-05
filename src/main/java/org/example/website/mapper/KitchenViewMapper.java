package org.example.website.mapper;

import org.example.website.dto.KitchenViewDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.KitchenViewEntity;
import org.example.website.entities.TablesEntity;

public class KitchenViewMapper {
    public static KitchenViewDTO toDTO(KitchenViewEntity entity) {
        KitchenViewDTO  dto = new KitchenViewDTO ();

        dto.setTableNum(entity.getTableNum());
        dto.setOrderId(entity.getOrderId());

        dto.setStatusOrder(entity.getStatusOrder());
        dto.setStatusStart(entity.getStatusStart());
        dto.setStatusMains(entity.getStatusMains());

        dto.setMenuItemId(entity.getMenuItemId());
        dto.setAmount(entity.getAmount());
        dto.setName(entity.getName());

        dto.setCategory(entity.getCategory());
        return dto;

    }
}
