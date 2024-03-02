package org.example.website.mapper;

import org.example.website.dto.EmployeesDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.TablesEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.example.website.entities.OrdersEntity;

public class TablesMapper {
    public static TablesDTO toDTO(TablesEntity t) {
        TablesDTO dto = new TablesDTO();
        dto.setTableNum(t.getTableNum());
        dto.setStatus(t.getStatus());

        return dto;
    }

    //Will be used for updating and inserting data later on
    public static TablesEntity toEntity(TablesDTO dto) {
        TablesEntity entity = new TablesEntity();
        entity.setStatus(dto.getStatus());
        entity.setTableNum(dto.getTableNum());
        return entity;
    }

}