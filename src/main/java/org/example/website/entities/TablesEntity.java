package org.example.website.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TABLES", schema = "restaurang", catalog = "")
public class TablesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TABLE_NUM")
    private int tableNum;
    @Basic
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Basic
    @Column(name = "STATUS")
    private String status;

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TablesEntity that = (TablesEntity) o;

        if (tableNum != that.tableNum) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableNum;
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
