package org.example.website.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.sql.Time;

@NamedQuery(
        name = "EventsEntity.findAll",
        query = "Select l FROM EventsEntity l"
)
@Entity
@Table(name = "EVENTS", schema = "restaurang", catalog = "")
public class EventsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EVENT_ID")
    private int eventId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PRICE")
    private Integer price;
    @Basic
    @Column(name = "DESCR")
    private String descr;
    @Basic
    @Column(name = "EVENT_DAY")
    private Integer eventDay;
    @Basic
    @Column(name = "EVENT_MONTH")
    private Integer eventMonth;
    @Basic
    @Column(name = "EVENT_YEAR")
    private Integer eventYear;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }



    public Integer getEventDay() {
        return eventDay;
    }

    public void setEventDay(Integer eventDay) {
        this.eventDay = eventDay;
    }

    public Integer getEventMonth() {
        return eventMonth;
    }

    public void setEventMonth(Integer eventMonth) {
        this.eventMonth = eventMonth;
    }

    public Integer getEventYear() {
        return eventYear;
    }

    public void setEventYear(Integer eventYear) {
        this.eventYear = eventYear;
    }
}
