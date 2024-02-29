package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.website.entities.EmployeesEntity;
import org.example.website.entities.LunchesEntity;
import org.example.website.entities.WorkShiftsEntity;

import java.io.Serializable;
import java.util.*;
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
    // container for all employees work shifts
    private List<EmployeesEntity> employeesWorkshiftList;
    // container for all work shifts for a specific employee
    private List<WorkShiftsEntity> empWorkShifts;
    private String selectedDay; // Added for selecting day in addWorkShift form

    // Initialize this in @PostConstruct
    private Map<String, String> weekDays;

    @PostConstruct
    public void init() {
        prepareWeekDays();
        employeesWorkshiftList= em.createQuery("SELECT e FROM EmployeesEntity e JOIN FETCH e.workShifts", EmployeesEntity.class).getResultList();
        employeesList = em.createNamedQuery("EmployeesEntity.findEmployees", EmployeesEntity.class).getResultList();
        empWorkShifts = em.createNamedQuery("WorkShiftsEntity.findWorkShiftsForEmployee", WorkShiftsEntity.class).getResultList();
    }

    private void prepareWeekDays() {
        weekDays = new LinkedHashMap<>(); // Preserve insertion order
        weekDays.put("Monday", "Monday");
        weekDays.put("Tuesday", "Tuesday");
        weekDays.put("Wednesday", "Wednesday");
        weekDays.put("Thursday", "Thursday");
        weekDays.put("Friday", "Friday");
        weekDays.put("Saturday", "Saturday");
        weekDays.put("Sunday", "Sunday");
    }

    @Transactional
    public void addWorkShift() {
        // Set the day for the work shift from the selectedDay, assuming selectedDay is of a compatible type.
        if (selectedDay != null) {
            workShift.setDay(selectedDay);
        }

        // Attach the work shift to the employee.
        // Assumes `employee` is already attached to the persistence context or is a new entity.
        workShift.setEmployee(employee);

        // Ensure the employee has a non-null list to add the work shift to.
        if (employee.getWorkShifts() == null) {
            employee.setWorkShifts(new ArrayList<>());
        }

        // Add the new work shift to the employee's list of work shifts.
        employee.getWorkShifts().add(workShift);

        // Persist the work shift entity.
        em.persist(workShift);

        // Reset the workShift for the next entry.
        workShift = new WorkShiftsEntity();

        // The call to init() is conditional based on whether you need to refresh or update UI or data.
        // init();
    }

    @Transactional
    public void deleteWorkShift(WorkShiftsEntity ws) {
        WorkShiftsEntity toDelete = em.find(WorkShiftsEntity.class, ws.getShiftId());
        if (toDelete != null) { em.remove(toDelete); }
        // Refresh the list of lunches to reflect the deletion
        // init(); // Assuming init() method populates the list of lunches
    }

    public List<WorkShiftsEntity> getShiftsByDay(String dayOfWeek) {
        return em.createQuery("SELECT ws FROM WorkShiftsEntity ws WHERE ws.day = :dayOfWeek", WorkShiftsEntity.class)
                .setParameter("dayOfWeek", dayOfWeek)
                .getResultList();
    }

    public List<WorkShiftsEntity> getWorkShiftsFor(int employee) {
        TypedQuery<WorkShiftsEntity> query = em.createNamedQuery("WorkShiftsEntity.findWorkShiftsForEmployee", WorkShiftsEntity.class);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    // Getters and Setters
    public WorkShiftsEntity getWorkShift() {
        return workShift;
    }
    public void setWorkShift(WorkShiftsEntity workShift) {
        this.workShift = workShift;
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public Map<String, String> getWeekDays() {
        return weekDays;
    }
    public List<EmployeesEntity> getEmployees() {
        return employeesList;
    }

    public List<EmployeesEntity> getEmployeesWorkshiftList() { return employeesWorkshiftList; }

    public EmployeesEntity getEmployee() {
        return employee;
    }
}