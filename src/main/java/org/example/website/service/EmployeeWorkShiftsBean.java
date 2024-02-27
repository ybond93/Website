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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Integer selectedDay; // Added for selecting day in addWorkShift form

    // Initialize this in @PostConstruct
    private Map<String, Integer> weekDays;
    @PostConstruct
    public void init() {
        prepareWeekDays();
        employeesWorkshiftList= em.createQuery("SELECT e FROM EmployeesEntity e JOIN FETCH e.workShifts", EmployeesEntity.class).getResultList();
        employeesList = em.createNamedQuery("EmployeesEntity.findEmployees", EmployeesEntity.class).getResultList();
    }

    private void prepareWeekDays() {
        weekDays = new HashMap<>();
        weekDays.put("MONDAY", 1);
        weekDays.put("TUESDAY", 2);
        weekDays.put("WEDNESDAY", 3);
        weekDays.put("THURSDAY", 4);
        weekDays.put("FRIDAY", 5);
        weekDays.put("SATURDAY", 6);
        weekDays.put("SUNDAY", 7);
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

    public List<WorkShiftsEntity> getShiftsByDay(String dayOfWeek) {
        Integer dayInt = weekDays.get(dayOfWeek.toUpperCase());
        if (dayInt == null) {
            return new ArrayList<>();
        }
        return em.createQuery("SELECT ws FROM WorkShiftsEntity ws WHERE ws.day = :dayInt", WorkShiftsEntity.class)
                .setParameter("dayInt", dayInt)
                .getResultList();
    }

    public List<WorkShiftsEntity> getWorkShiftsList(int empId) {
        return em.createQuery("SELECT ws FROM WorkShiftsEntity ws WHERE ws.employee.empId = :empId", WorkShiftsEntity.class)
                .setParameter("empId", empId)
                .getResultList();
    }

    // Getters and Setters
    public WorkShiftsEntity getWorkShift() {
        return workShift;
    }
    public void setWorkShift(WorkShiftsEntity workShift) {
        this.workShift = workShift;
    }

    public Integer getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(Integer selectedDay) {
        this.selectedDay = selectedDay;
    }



    public Map<String, Integer> getWeekDays() {
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