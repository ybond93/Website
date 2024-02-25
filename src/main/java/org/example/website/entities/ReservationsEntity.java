package org.example.website.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;


@NamedQuery(
        name = "ReservationsEntity.findAll",
        query = "Select l FROM ReservationsEntity l"
)
@Entity
@Table(name = "reservations", schema = "restaurang", catalog = "")
public class ReservationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RES_ID")
    private int resId;
    @Basic
    @Column(name = "NUM_OF_GUESTS")
    private int numOfGuests;
    @Basic
    @Column(name = "RES_DATE")
    private Date resDate;
    @Basic
    @Column(name = "RES_TIME")
    private Time resTime;
    @Basic
    @Column(name = "CUST_NAME")
    private String custName;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public Time getResTime() {
        return resTime;
    }

    public void setResTime(Time resTime) {
        this.resTime = resTime;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (resId != that.resId) return false;
        if (numOfGuests != that.numOfGuests) return false;
        if (resDate != null ? !resDate.equals(that.resDate) : that.resDate != null) return false;
        if (resTime != null ? !resTime.equals(that.resTime) : that.resTime != null) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resId;
        result = 31 * result + numOfGuests;
        result = 31 * result + (resDate != null ? resDate.hashCode() : 0);
        result = 31 * result + (resTime != null ? resTime.hashCode() : 0);
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        return result;
    }
}
