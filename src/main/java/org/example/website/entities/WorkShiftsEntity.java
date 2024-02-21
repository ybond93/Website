package org.example.website.entities;

import jakarta.persistence.*;
import java.util.Date;

@NamedQuery(
        name = "WorkShiftsEntity.findAll",
        query = "Select l FROM WorkShiftsEntity l"
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
    @Column(name = "SHIFT_DATE")
    private Date shiftDate;

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }
}
