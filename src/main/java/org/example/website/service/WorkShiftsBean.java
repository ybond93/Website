package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import org.example.website.entities.WorkShiftsEntity;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class WorkShiftsBean implements Serializable {
    // for fetching
    private List<WorkShiftsEntity> work_shifts;

    // creates a **direct binding** from form to menu items entity
    private WorkShiftsEntity workShift = new WorkShiftsEntity();

    @PostConstruct
    public void init() {
        try (Client client = ClientBuilder.newClient()) {
            this.work_shifts = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/workshifts")
                    .request()
                    .get(new GenericType<List<WorkShiftsEntity>>() {
                    });
        }
    }

    public WorkShiftsEntity getWorkShift() { return workShift; }

    public List<WorkShiftsEntity> getWorkShifts() {
        return work_shifts;
    }

    public void addWorkShift() {
        try (Client client = ClientBuilder.newClient()) {
            try (Response response = client.target("http://localhost:8080/Website-1.0-SNAPSHOT/api/workshifts")
                    .request()
                    .post(Entity.json(workShift))) {

                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    // Handle success (e.g., adding to the local list, showing success message)
                    work_shifts.add(workShift);
                } else {
                    // Handle error
                }
            }
        }
        // Reset the newMenuItem for the next entry
        workShift = new WorkShiftsEntity();
    }
}



