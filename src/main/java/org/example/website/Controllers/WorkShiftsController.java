package org.example.website.Controllers;

import org.example.website.Modles.WorkShiftsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/work_shifts")
public class WorkShiftsController {
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
