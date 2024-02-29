package org.example.website.mapper;

import org.example.website.dto.EmployeesDTO;
import org.example.website.entities.EmployeesEntity;

public class EmployeesMapper {
    public static EmployeesDTO toDTO(EmployeesEntity employee) {
        EmployeesDTO dto = new EmployeesDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        return dto;
    }

    // Will be used for updating and inserting data later on
    public static EmployeesEntity toEntity(EmployeesDTO dto) {
        EmployeesEntity entity = new EmployeesEntity();
        entity.setEmpId(dto.getEmpId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }
}