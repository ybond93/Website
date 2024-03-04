package org.example.website.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@NamedQuery(
        name = "TablesEntity.findAll",
        query = "SELECT l from  TablesEntity l"
)
@Entity
@Table(name = "TABLES", schema = "restaurang", catalog = "")
public class TablesEntity {
    @Id
    @Column(name = "TABLE_NUM")
    private int tableNum;

    @Basic
    @Column(name = "STATUS")
    private String status;
    @OneToMany (mappedBy = "tableNum")
    private List<OrdersEntity> orders = new ArrayList<>();
    public List<OrdersEntity> getOrders(){return  orders;}
    public void setOrders(List<OrdersEntity> orders){
        this.orders = orders;
    }

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