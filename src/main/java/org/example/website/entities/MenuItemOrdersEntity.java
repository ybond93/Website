package org.example.website.entities;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery( name = "MenuItemOrdersEntity.findAll",
                query = "SELECT l FROM MenuItemOrdersEntity l"),
        @NamedQuery(
                name = "MenuItemOrdersEntity.findInProgress",
                query = "SELECT m FROM MenuItemOrdersEntity m JOIN m.order o WHERE o.status = 'In Progress'"
        )
})
@Entity
@Table(name = "menu_item_orders", schema = "restaurang", catalog = "")
//@IdClass(org.example.website.entities.MenuItemOrdersEntityPK.class)
public class MenuItemOrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", nullable = false)
    private OrdersEntity order;

    // Bidirectional @ManyToOne association back to MenuItemsEntity
    @ManyToOne
    @JoinColumn(name = "MENU_ITEM_ID", referencedColumnName = "MENU_ITEM_ID", nullable = false)
    private MenuItemsEntity menuItem;

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

    public MenuItemsEntity getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemsEntity menuItem) {
        this.menuItem = menuItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemOrdersEntity that = (MenuItemOrdersEntity) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}