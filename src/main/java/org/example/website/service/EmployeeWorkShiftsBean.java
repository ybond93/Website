package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.WorkShiftsEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class EmployeeWorkShiftsBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private List<WorkShiftsEntity> workShiftsList;
    private WorkShiftsEntity workShift = new WorkShiftsEntity();
    private EmployeesEntity employee = new EmployeesEntity();
    private List<EmployeesEntity> employeesList;
    private List<EmployeesEntity> employeesWorkshiftList;

    @PostConstruct
    public void init() {
        employeesWorkshiftList= em.createQuery("SELECT e FROM EmployeesEntity e JOIN FETCH e.workShifts", EmployeesEntity.class).getResultList();
        employeesList = em.createNamedQuery("EmployeesEntity.findEmployees", EmployeesEntity.class).getResultList();
    }

    @Transactional
    public void addWorkShift() {
        // Assumes that 'employee' is already attached to the persistence context or is a new entity.
        // If 'employee' is detached, you would need to merge it first.
        workShift.setEmployee(employee);

        if (employee.getWorkShifts() == null) {
            employee.setWorkShifts(new ArrayList<>());
        }
        // If you're managing the list of workShifts in the employee object, add the new workShift to it
        employee.getWorkShifts().add(workShift);

        em.persist(workShift);
        workShift = new WorkShiftsEntity(); // Reset for the next entry
        // Depending on your use case, you might not want to call init here as it might be refreshing the list unnecessarily
        init();
    }

    public WorkShiftsEntity getWorkShift() {
        return workShift;
    }

    public List<WorkShiftsEntity> getWorkShiftsList(int empId) {
        return em.createQuery("SELECT ws FROM WorkShiftsEntity ws WHERE ws.employee.empId = :empId", WorkShiftsEntity.class)
                .setParameter("empId", empId)
                .getResultList();
    }

    public List<EmployeesEntity> getEmployees() {
        return employeesList;
    }

    public List<EmployeesEntity> getEmployeesWorkshiftList() { return employeesWorkshiftList; }

    public EmployeesEntity getEmployee() {
        return employee;
    }
}