package org.example.website.entities;

import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQueries({
    @NamedQuery(
            name = "EmployeesEntity.findEmployees",
            query = "SELECT l from  EmployeesEntity l"
    ),
})
@Entity
@Table(name = "EMPLOYEES", schema = "restaurang", catalog = "")
public class EmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EMP_ID")
    private int empId;
    @Basic
    @Column(name = "F_NAME")
    private String firstName;
    @Basic
    @Column(name = "L_NAME")
    private String lastName;

    // maps to WorkShiftsEntity's "employee" property
    @OneToMany(mappedBy = "employee")
    private List<WorkShiftsEntity> workShifts = new ArrayList<>();

    public List<WorkShiftsEntity> getWorkShifts() {
        return workShifts;
    }

    public void setWorkShifts(List<WorkShiftsEntity> workShifts) {
        this.workShifts = workShifts;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public Integer getId() {
        return empId;
    }
}
