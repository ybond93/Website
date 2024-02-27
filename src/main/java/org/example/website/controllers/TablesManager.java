package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.EventsEntity;
import org.example.website.entities.TablesEntity;

import java.util.List;

@Path("/tables")
public class TablesManager {

    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getTables() {
        List<TablesEntity> tablesList;
        tablesList = em.createNamedQuery("TablesEntity.findTables", TablesEntity.class).getResultList();
        return Response.ok(tablesList).build();
    }
}
