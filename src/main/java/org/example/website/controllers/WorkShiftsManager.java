package org.example.website.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.example.website.entities.WorkShiftsEntity;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

@Path("/workshifts")
public class WorkShiftsManager {

    @PersistenceContext
    private EntityManager em;
    @GET
    @Produces("application/json")
    public Response getWorkShifts() {
        List<WorkShiftsEntity> work_shifts;
        work_shifts = em.createNamedQuery("WorkShiftsEntity.findAll", WorkShiftsEntity.class).getResultList();
        return Response.ok(work_shifts).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addWorkShift(WorkShiftsEntity workShifts) {
        em.persist(workShifts);
        em.flush(); // Optional, to ensure ID is generated
        return Response.ok(workShifts).build();
    }
}

