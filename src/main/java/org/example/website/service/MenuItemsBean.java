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
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Named
@RequestScoped
public class MenuItemsBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private MenuItemsEntity menuItem = new MenuItemsEntity();
    private List<MenuItemsEntity> menuItemList;
    private LunchesEntity lunchItem = new LunchesEntity();
    private List<LunchesEntity> lunchItemsList;
    private String selectedDay; // +getter and setter

    // List or Map to hold weekdays
    private Map<String, String> weekdays;
    private String selectedType;
    private ArrayList<String> types = new ArrayList<>();


    private AlacarteMenuItemsEntity aLaCarteItem = new AlacarteMenuItemsEntity();
    private List<AlacarteMenuItemsEntity> aLaCarteItemsList;
    private List<AlacarteMenuItemsEntity> startersList;
    private List<AlacarteMenuItemsEntity> mainsList;
    private List<AlacarteMenuItemsEntity> dessertsList;
    private List<AlacarteMenuItemsEntity> drinksList;

    // Method to get the current day of the week
    public String getCurrentDay() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // Or any Locale you need
    }

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
        menuItemList = em.createNamedQuery("MenuItemsEntity.findAll", MenuItemsEntity.class).getResultList();

        weekdays = new LinkedHashMap<>(); // Preserve insertion order
        weekdays.put("Monday", "Monday");
        weekdays.put("Tuesday", "Tuesday");
        weekdays.put("Wednesday", "Wednesday");
        weekdays.put("Thursday", "Thursday");
        weekdays.put("Friday", "Friday");
        weekdays.put("Saturday", "Saturday");
        weekdays.put("Sunday", "Sunday");

        types.add("Soup");
        types.add("Buffet");
    }

    public List<String> getTypes() { return types; }

    public List<LunchesEntity> getLunchesBy(String day) {
        TypedQuery<LunchesEntity> query = em.createNamedQuery("LunchesEntity.findLunchesByDay", LunchesEntity.class);
        query.setParameter("dayOfWeek", day);
        return query.getResultList();
    }

    public List<LunchesEntity> getLunchesBy(String day, String type) {
        TypedQuery<LunchesEntity> query = em.createNamedQuery("LunchesEntity.findLunchByDayAndType", LunchesEntity.class);
        query.setParameter("dayOfWeek", day);
        query.setParameter("type", type);
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

    @Transactional
    public void deleteLunch(LunchesEntity lunch) {
        LunchesEntity toDelete = em.find(LunchesEntity.class, lunch.getId());
        if (toDelete != null) { em.remove(toDelete); }
        // Refresh the list of lunches to reflect the deletion
        // init(); // Assuming init() method populates the list of lunches
    }

    @Transactional
    public void deleteALaCarteItem(AlacarteMenuItemsEntity item) {
        AlacarteMenuItemsEntity toDelete = em.find(AlacarteMenuItemsEntity.class, item.getCarteItemId());
        if (!em.contains(item)) { item = em.merge(item);}
        em.remove(item);
        // Refresh the list of lunches to reflect the deletion
        init(); // Assuming init() method populates the list of lunches
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

    public List<MenuItemsEntity> getMenuItemList() {
        return menuItemList;
    }
    public MenuItemsEntity getItem(){
        return  menuItem;
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

    public String getSelectedDay() {
        return selectedDay;
    }
    public void setSelectedDay(String day) {
        this.selectedDay = day;
    }
    public Map<String, String> getWeekDays() {
        return weekdays;
    }
}