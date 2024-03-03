package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.dto.EmployeesDTO;
import org.example.website.dto.TablesDTO;
import org.example.website.entities.EmployeesEntity;
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

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Transactional
    public Response updateTableStatus(@PathParam("id") int id, TablesEntity status) {
        TablesEntity existingTable = em.find(TablesEntity.class, id);
        if (existingTable == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTable.setStatus(status.getStatus());
        em.merge(existingTable);
        return Response.ok().build();
    }
}