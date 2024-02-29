package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.EventsEntity;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.example.website.entities.WorkShiftsEntity;


@Path("/workshifts")
public class WorkShiftsManager {

    @PersistenceContext
    private EntityManager em;

    @Path("/workshifts/{employeeID}")
    @GET
    @Produces("application/json")
    public Response getWorkShiftsFor(@PathParam("employeeID") int employeeID) {
        List<WorkShiftsEntity> workShifts = em.createNamedQuery("WorkShiftsEntity.findWorkShiftsForEmployee", WorkShiftsEntity.class)
                .setParameter("employee", employeeID)
                .getResultList();
        return Response.ok(workShifts).build();
    }
}