package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeesDTO;
import org.example.website.entities.EmployeesEntity;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
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

    @POST
    @Consumes("application/json")
    @Transactional
    public Response createEmployee(EmployeesDTO employeesDTO) {
        EmployeesEntity employeesEntity = EmployeesMapper.toEntity(employeesDTO);
        em.persist(employeesEntity);
        return Response.created(URI.create("/employees/" + employeesEntity.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Transactional
    public Response updateEmployee(@PathParam("id") int id, EmployeesDTO employeesDTO) {
        EmployeesEntity existingEmployee = em.find(EmployeesEntity.class, id);
        if (existingEmployee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Update the existing employee entity with data from the DTO
        EmployeesEntity updatedEmployee = EmployeesMapper.toEntity(employeesDTO);
        updatedEmployee.setEmpId(existingEmployee.getId()); // Ensure the ID remains unchanged

        // Merge the updated employee entity into the persistence context
        em.merge(updatedEmployee);

        return Response.ok().build();
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
