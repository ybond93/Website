package org.example.website.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEES", schema = "restaurang", catalog = "")
public class EmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EMP_ID")
    private int empId;
    @Basic
    @Column(name = "F_NAME")
    private String fName;
    @Basic
    @Column(name = "L_NAME")
    private String lName;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesEntity that = (EmployeesEntity) o;

        if (empId != that.empId) return false;
        if (fName != null ? !fName.equals(that.fName) : that.fName != null) return false;
        if (lName != null ? !lName.equals(that.lName) : that.lName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empId;
        result = 31 * result + (fName != null ? fName.hashCode() : 0);
        result = 31 * result + (lName != null ? lName.hashCode() : 0);
        return result;
    }
}
