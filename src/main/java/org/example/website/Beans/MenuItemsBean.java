package org.example.website.Beans;

import org.example.website.Modles.MenuItemsEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MenuItemsBean implements Serializable {
    /*
  rest api for mobile app
  bean uses EntityManager for the web application

  borttagning och inlägg av arbetspass bör vara @Transactional
   */
    @PersistenceContext
    private EntityManager em;

    // for fetching
    private List<MenuItemsEntity> menu_items;

    // creates a **direct binding** from form to menu items entity
    private MenuItemsEntity menuItem = new MenuItemsEntity();

    @PostConstruct
    public void init() {
        try (Client client = ClientBuilder.newClient()) {
            try { Response response = client.target("http://localhost:8080/adminWebsite/api/menu_items")
                    .request()
                    .get();

                if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
                    // Assuming the response contains a JSON array of menu items
                    this.menu_items = response.readEntity(new GenericType<List<MenuItemsEntity>>(){});
                }
                else { }
            }
            catch (Exception e) { }
        }
        /*
        try (Client client = ClientBuilder.newClient()) {
            this.menu_items = client.target("http://localhost:8080/adminWebsite/api/menu_items")
                    .request()
                    .get(new GenericType<List<MenuItemsEntity>>() {
                    });
        }
         */
    }

    public MenuItemsEntity getMenuItem() { return menuItem; }

    public List<MenuItemsEntity> getMenuItems() {
        return menu_items;
    }

    public void addMenuItem() {
        try (Client client = ClientBuilder.newClient()) {
            try (Response response = client.target("http://localhost:8080/adminWebsite/api/menu_items")
                    .request()
                    .post(Entity.json(menuItem))) {

                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    // Handle success (e.g., adding to the local list, showing success message)
                    menu_items.add(menuItem);
                } else {
                    // for git
                    // Handle error
                }
            }
        }
        // Reset the newMenuItem for the next entry
        menuItem = new MenuItemsEntity();
    }
}
