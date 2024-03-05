package org.example.website.mapper;

import org.example.website.dto.*;

import java.util.List;

public class KitchenOrdersMapper {

    public static KitchenOrdersDTO toKitchenOrdersDTO(List<TablesDTO> tablesDTO) {
        KitchenOrdersDTO kitchenOrdersDTO = new KitchenOrdersDTO();
        kitchenOrdersDTO.setTablesDTO(tablesDTO);
        return kitchenOrdersDTO;
    }
}
