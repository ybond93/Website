package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeesDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.EventsEntity;
import org.example.website.entities.TablesEntity;
import org.example.website.mapper.EmployeesMapper;
import org.example.website.mapper.TablesMapper;

import java.util.List;
import java.util.stream.Collectors;

@Path("/tables")
public class TablesManager {

    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getTables() {
        List<TablesEntity> tablesList;
        tablesList = em.createNamedQuery("TablesEntity.findAll", TablesEntity.class).getResultList();

            List<TablesDTO> employeeDTOs = tablesList.stream()
                    .map(TablesMapper::toDTO)
                    .collect(Collectors.toList());
            return Response.ok(employeeDTOs).build();


    }
}
