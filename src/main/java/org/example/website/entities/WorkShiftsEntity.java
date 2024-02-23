package org.example.website.entities;

import jakarta.persistence.*;

@NamedQuery(
        name = "WorkShiftsEntity.findEmployeeShifts",
        query = "SELECT ws FROM WorkShiftsEntity ws JOIN FETCH ws.employee"
)
@Entity
@Table(name = "work_shifts", schema = "restaurang", catalog = "")
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
    private Integer month;
    @Basic
    @Column(name = "DAY")
    private Integer day;

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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}