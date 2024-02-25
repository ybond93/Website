package org.example.website.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;


@NamedQuery(
        name = "ReservationsEntity.findAll",
        query = "Select l FROM ReservationsEntity l"
)
@Entity
@Table(name = "RESERVATIONS", schema = "restaurang", catalog = "")
public class ReservationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RES_ID")
    private int resId;
    @Basic
    @Column(name = "NUM_OF_GUESTS")
    private int numOfGuests;

    @Basic
    @Column(name = "RES_TIME")
    private Time resTime;
    @Basic
    @Column(name = "CUST_NAME")
    private String custName;
    @Basic
    @Column(name = "RES_YEAR")
    private Integer resYear;
    @Basic
    @Column(name = "RES_MONTH")
    private String resMonth;
    @Basic
    @Column(name = "RES_DAY")
    private Integer resDay;

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

        if (resTime != null ? !resTime.equals(that.resTime) : that.resTime != null) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resId;
        result = 31 * result + numOfGuests;
        result = 31 * result + (resTime != null ? resTime.hashCode() : 0);
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        return result;
    }

    public Integer getResYear() {
        return resYear;
    }

    public void setResYear(Integer resYear) {
        this.resYear = resYear;
    }

    public String getResMonth() {
        return resMonth;
    }

    public void setResMonth(String resMonth) {
        this.resMonth = resMonth;
    }

    public Integer getResDay() {
        return resDay;
    }

    public void setResDay(Integer resDay) {
        this.resDay = resDay;
    }
}
