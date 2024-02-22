package org.example.website.entities;

import jakarta.persistence.*;

import java.sql.Date;

/* The named query below effectively fetches all
 * LunchesTestEntity instances along with their
 * associated MenuItemsTestEntity instances. */
@NamedQuery(
        name = "LunchesTestEntity.findAll",
        query = "SELECT l FROM LunchesEntity l JOIN FETCH l.menuItem"
)
@Entity
@Table(name = "lunches", schema = "restaurang", catalog = "")
public class LunchesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LUNCH_ID")
    private int lunchId;
    @Basic
    @Column(name = "DATE")
    private Date date;
    @Basic
    @Column(name = "DAY")
    private String day;
    @OneToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "menu_item_id")
    private MenuItemsEntity menuItem;   // Lunches Foreign Key

    // getting the FK
    public MenuItemsEntity getMenuItem() {
        return menuItem;
    }

    // setting the FK
    public void setMenuItem(MenuItemsEntity menuItem) {
        this.menuItem = menuItem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
