package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.website.entities.AlacarteMenuItemsEntity;
import org.example.website.entities.LunchesEntity;
import org.example.website.entities.MenuItemsEntity;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MenuItemsBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private MenuItemsEntity menuItem = new MenuItemsEntity();
    private LunchesEntity lunchItem = new LunchesEntity();
    private List<LunchesEntity> lunchItemsList;

    private AlacarteMenuItemsEntity aLaCarteItem = new AlacarteMenuItemsEntity();
    private List<AlacarteMenuItemsEntity> aLaCarteItemsList;
    private List<AlacarteMenuItemsEntity> startersList;
    private List<AlacarteMenuItemsEntity> mainsList;
    private List<AlacarteMenuItemsEntity> dessertsList;
    private List<AlacarteMenuItemsEntity> drinksList;

    @PostConstruct
    public void init() {
        lunchItemsList = em.createNamedQuery("LunchesTestEntity.findAll", LunchesEntity.class).getResultList();
        startersList = em.createNamedQuery("ALaCarteMenuItems.findStarters", AlacarteMenuItemsEntity.class).getResultList();
        mainsList = em.createNamedQuery("ALaCarteMenuItems.findMains", AlacarteMenuItemsEntity.class).getResultList();
        dessertsList = em.createNamedQuery("ALaCarteMenuItems.findDesserts", AlacarteMenuItemsEntity.class).getResultList();
        drinksList = em.createNamedQuery("ALaCarteMenuItems.findDrinks", AlacarteMenuItemsEntity.class).getResultList();
    }

    @Transactional
    public void addLunch() {
        em.persist(menuItem);
        lunchItem.setMenuItem(menuItem);
        em.persist(lunchItem);
        // Clear the event object for the next entry
        menuItem = new MenuItemsEntity();
        lunchItem = new LunchesEntity();
        init(); // Refresh the events list
    }

    private void clearEntities() {
        menuItem = new MenuItemsEntity();
        aLaCarteItem = new AlacarteMenuItemsEntity();
    }

    @Transactional
    public void addStarter() {
        menuItem = em.merge(menuItem); // Persist or merge the menuItem
        aLaCarteItem.setMenuItem(menuItem);
        aLaCarteItem.setCategory("Starter");
        em.persist(aLaCarteItem);
        clearEntities();  // Clear entities for the next entry
        init(); // Refresh the events list
    }

    @Transactional
    public void addMain() {
        menuItem = em.merge(menuItem); // Persist or merge the menuItem
        aLaCarteItem.setMenuItem(menuItem);
        aLaCarteItem.setCategory("Main");
        em.persist(aLaCarteItem);
        clearEntities();  // Clear entities for the next entry
        init(); // Refresh the events list
    }

    @Transactional
    public void addDessert() {
        menuItem = em.merge(menuItem); // Persist or merge the menuItem
        aLaCarteItem.setMenuItem(menuItem);
        aLaCarteItem.setCategory("Dessert");
        em.persist(aLaCarteItem);
        clearEntities();  // Clear entities for the next entry
        init(); // Refresh the events list
    }

    @Transactional
    public void addDrink() {
        menuItem = em.merge(menuItem); // Persist or merge the menuItem
        aLaCarteItem.setMenuItem(menuItem);
        aLaCarteItem.setCategory("Drink");
        em.persist(aLaCarteItem);
        clearEntities();  // Clear entities for the next entry
        init(); // Refresh the events list
    }

    /****** Getters and Setters *******/
    // Menu items
    public MenuItemsEntity getMenuItem() { return menuItem; }
    public void setMenuItem(MenuItemsEntity menuItem) {
        this.menuItem = menuItem;
    }

    // Lunches
    public LunchesEntity getLunchItem() { return lunchItem; }
    public void setLunchItem(LunchesEntity lunchItem) {
        this.lunchItem = lunchItem;
    }
    public List<LunchesEntity> getLunchItemsList() { return lunchItemsList; }

    // A La Carte items
    public AlacarteMenuItemsEntity getaLaCarteItem() { return aLaCarteItem; }
    public void setaLaCarteItem(AlacarteMenuItemsEntity aLaCarteItem) {
        this.aLaCarteItem = aLaCarteItem;
    }
    public List<AlacarteMenuItemsEntity> getaLaCarteItemsList() { return aLaCarteItemsList; }

    public List<AlacarteMenuItemsEntity> getStartersList() { return startersList; }
    public List<AlacarteMenuItemsEntity> getMainsList() { return mainsList; }
    public List<AlacarteMenuItemsEntity> getDessertsList() { return dessertsList; }
    public List<AlacarteMenuItemsEntity> getDrinksList() { return drinksList; }

}
