package org.example.website.Models;

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
    @Basic
    @Column(name = "SHIFT_STATUS")
    private String shiftStatus;

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

    public String getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkShiftsEntity that = (WorkShiftsEntity) o;

        if (shiftId != that.shiftId) return false;
        if (shiftType != null ? !shiftType.equals(that.shiftType) : that.shiftType != null) return false;
        if (shiftDate != null ? !shiftDate.equals(that.shiftDate) : that.shiftDate != null) return false;
        if (shiftStatus != null ? !shiftStatus.equals(that.shiftStatus) : that.shiftStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shiftId;
        result = 31 * result + (shiftType != null ? shiftType.hashCode() : 0);
        result = 31 * result + (shiftDate != null ? shiftDate.hashCode() : 0);
        result = 31 * result + (shiftStatus != null ? shiftStatus.hashCode() : 0);
        return result;
    }
}
