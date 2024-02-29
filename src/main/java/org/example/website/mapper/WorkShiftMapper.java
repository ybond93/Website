package org.example.website.mapper;

import org.example.website.dto.WorkShiftsDTO;
import org.example.website.entities.WorkShiftsEntity;

public class WorkShiftMapper {

    public static WorkShiftsDTO toDTO(WorkShiftsEntity workshift) {
        WorkShiftsDTO dto = new WorkShiftsDTO();
        dto.setShiftId(workshift.getShiftId());
        dto.setShiftType(workshift.getShiftType());
        dto.setYear(workshift.getYear());
        dto.setMonth(workshift.getMonth());
        dto.setDay(workshift.getDay());

        return dto;
    }
}

