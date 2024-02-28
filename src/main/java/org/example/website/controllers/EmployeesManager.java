package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeesDTO;
import org.example.website.entities.EmployeesEntity;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.example.website.mapper.EmployeesMapper;

@Path("/employees")
public class EmployeesManager {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces("application/json")
    public Response getEmployees() {
        List<EmployeesEntity> employeesList;
        employeesList = em.createNamedQuery("EmployeesEntity.findEmployees", EmployeesEntity.class).getResultList();
        List<EmployeesDTO> employeeDTOs = employeesList.stream()
                .map(EmployeesMapper::toDTO)
                .collect(Collectors.toList());
        return Response.ok(employeeDTOs).build();
    }

    // will be used for inserting and updating data from the API
    /*@POST
    @Consumes("application/json")
    public Response createEmployee(EmployeeDTO employeeDTO) {
        EmployeesEntity employeeEntity = EmployeeMapper.toEntity(employeeDTO);
        em.persist(employeeEntity);
        return Response.ok().build();
    }*/
}

/*
    @POST
    @Consumes("application/json")
    @Transactional
    public Response addMenuItem(MenuItemsEntity menuItem) {
        em.persist(menuItem);
        em.flush(); // Optional, to ensure ID is generated
        return Response.ok(menuItem).build();
    }
}
*/