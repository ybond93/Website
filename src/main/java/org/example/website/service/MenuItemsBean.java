package org.example.website.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.website.entities.AlacarteMenuItemsEntity;
import org.example.website.entities.LunchesEntity;
import org.example.website.entities.MenuItemsEntity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Map<String, Integer> DAY_ORDER = new HashMap<>(); //this is for saying what day is first
    static {
        DAY_ORDER.put("Monday", 1);
        DAY_ORDER.put("Tuesday", 2);
        DAY_ORDER.put("Wednesday", 3);
        DAY_ORDER.put("Thursday", 4);
        DAY_ORDER.put("Friday", 5);
        DAY_ORDER.put("Saturday", 6);
        DAY_ORDER.put("Sunday", 7);
    }

    @PostConstruct
    public void init() {
        lunchItemsList = em.createNamedQuery("LunchesEntity.findAll", LunchesEntity.class).getResultList();
        startersList = em.createNamedQuery("ALaCarteMenuItems.findStarters", AlacarteMenuItemsEntity.class).getResultList();
        mainsList = em.createNamedQuery("ALaCarteMenuItems.findMains", AlacarteMenuItemsEntity.class).getResultList();
        dessertsList = em.createNamedQuery("ALaCarteMenuItems.findDesserts", AlacarteMenuItemsEntity.class).getResultList();
        drinksList = em.createNamedQuery("ALaCarteMenuItems.findDrinks", AlacarteMenuItemsEntity.class).getResultList();
    }

    public List<LunchesEntity> getLunchesBy(String day) {
        TypedQuery<LunchesEntity> query = em.createNamedQuery("LunchesEntity.findLunchesByDay", LunchesEntity.class);
        query.setParameter("dayOfWeek", day);
        return query.getResultList();
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
    public List<LunchesEntity> getLunchItemsList() {
        if (lunchItemsList != null) {
            lunchItemsList.sort(Comparator.comparingInt(lunch -> DAY_ORDER.getOrDefault(lunch.getDay(), 0)));
        }
        return lunchItemsList;
    }

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