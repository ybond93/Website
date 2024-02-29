package org.example.website.entities;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(    // fetches ALL work shifts
                name = "WorkShiftsEntity.findEmployeeShifts",
                query = "SELECT ws FROM WorkShiftsEntity ws JOIN FETCH ws.employee"
        ),
        @NamedQuery(
                name = "WorkShiftsEntity.findWorkShiftsByDay",
                query = "SELECT ws FROM WorkShiftsEntity ws JOIN FETCH ws.employee WHERE ws.day = :dayOfWeek"
        ),
        // fetches the work shifts for a specific employee
        @NamedQuery(
                name = "WorkShiftsEntity.findWorkShiftsForEmployee",
                query = "SELECT ws FROM WorkShiftsEntity ws JOIN FETCH ws.employee WHERE ws.employee = :employee"
        )
})
@Entity
@Table(name = "WORK_SHIFTS", schema = "restaurang", catalog = "")
public class WorkShiftsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SHIFT_ID")
    private int shiftId;
    @Basic
    @Column(name = "SHIFT_TYPE")
    private String shiftType;
    @Basic
    @Column(name = "YEAR")
    private Integer year;
    @Basic
    @Column(name = "MONTH")
    private String month;
    @Basic
    @Column(name = "DAY")
    private String day;

    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private EmployeesEntity employee;

    public EmployeesEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeesEntity employee) {
        this.employee = employee;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getShiftId() {
        return shiftId;
    }
}