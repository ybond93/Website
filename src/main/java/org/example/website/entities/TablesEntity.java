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
    @Column(name = "STATUS")
    private String status;

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
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
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableNum;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
