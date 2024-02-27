package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.EmployeesEntity;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/employees")
public class EmployeesManager {
    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    public Response getEmployees() {
        List<EmployeesEntity> employeesList;
        employeesList = em.createNamedQuery("EmployeesEntity.findEmployees", EmployeesEntity.class).getResultList();
        return Response.ok(employeesList).build();
    }
}
/*
package org.example.website.mapper;
import org.example.website.dto.EmployeeDTO;
import org.example.website.entities.EmployeesEntity;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(EmployeesEntity employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        // Map other fields as needed
        return dto;
    }
}
*/

/*
package org.example.website.mapper;
import org.example.website.dto.WorkShiftDTO;
import org.example.website.entities.WorkShiftsEntity;

public class WorkShiftMapper {
    public static WorkShiftDTO toDTO(WorkShiftsEntity workShift) {
        WorkShiftDTO dto = new WorkShiftDTO();
        dto.setShiftId(workShift.getShiftId());
        // Map other fields as needed
        return dto;
    }
}
*/

/*
package org.example.website.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeeDTO;
import org.example.website.mapper.EmployeeMapper;
import org.example.website.entities.EmployeesEntity;

import java.util.List;
import java.util.stream.Collectors;

@Path("/employees")
public class EmployeesManager {
    // EntityManager and other dependencies

    @GET
    @Produces("application/json")
    public Response getEmployees() {
        List<EmployeesEntity> employeesList = // Retrieve employees from database
        List<EmployeeDTO> employeeDTOs = employeesList.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return Response.ok(employeeDTOs).build();
    }
}

*/