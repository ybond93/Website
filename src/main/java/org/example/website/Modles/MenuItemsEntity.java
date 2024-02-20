package org.example.website.Modles;

import jakarta.persistence.*;

@NamedQuery(
        name = "MenuItemsEntity.findAll",
        query = "Select l FROM MenuItemsEntity l"
)
@Entity
@Table(name = "menu_items", schema = "restaurang", catalog = "")
public class MenuItemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MENU_ITEM_ID")
    private int menuItemId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PRICE")
    private Double price;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "DESCR")
    private String descr;
    @Basic
    @Column(name = "CATEGORY")
    private String category;

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemsEntity that = (MenuItemsEntity) o;

        if (menuItemId != that.menuItemId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuItemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
