
-- Log into MySQL:
-- mysql -u root -p

-- Change root password if needed
-- ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';

-- Create New User:
-- CREATE USER 'root'@'localhost' IDENTIFIED BY 'dt170g';

-- Grant Privileges:
-- GRANT ALL PRIVILEGES ON Restaurang.* TO 'root'@'localhost';

-- FLUSH PRIVILEGES;

-- EXIT;

-- SELECT user, host FROM mysql.user WHERE user = 'arfr1901';

-- inga komposita nycklar för att ORM ska fungera bra
-- simplifiera databasen, entiteter och relationer
DROP DATABASE restaurang;

CREATE DATABASE restaurang;
USE restaurang;

-- Creates
CREATE TABLE MENU_ITEMS (
    MENU_ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    PRICE DOUBLE,
    DESCR VARCHAR(200)
);

CREATE TABLE LUNCHES (
    LUNCH_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MENU_ITEM_ID INT NOT NULL,
    YEAR VARCHAR(30),
    MONTH VARCHAR(30),
    DAY VARCHAR(30)
);

CREATE TABLE ALACARTE_MENU_ITEMS (
    CARTE_ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MENU_ITEM_ID INT NOT NULL,
    CATEGORY VARCHAR(40)
);

CREATE TABLE EVENTS (
    EVENT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    PRICE INT,
    DESCR VARCHAR(200),
    EVENT_YEAR INT,
    EVENT_MONTH VARCHAR(30),
    EVENT_DAY INT,
    START_TIME TIME -- dont need startTime can be special day
);

CREATE TABLE RESERVATIONS (
    RES_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NUM_OF_GUESTS INT NOT NULL,
    RES_YEAR INT,
    RES_MONTH VARCHAR(30),
    RES_DAY INT,
    RES_TIME TIME NOT NULL,
    CUST_NAME VARCHAR(40) NOT NULL
);

CREATE TABLE EMPLOYEES (
    EMP_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    F_NAME VARCHAR(40) NOT NULL,
    L_NAME VARCHAR(40) NOT NULL
);

CREATE TABLE WORK_SHIFTS (
    SHIFT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    EMP_ID INT NOT NULL,
    SHIFT_TYPE VARCHAR(20) NOT NULL, -- morning or evening shift?
    YEAR INT,
    MONTH VARCHAR(30),
    DAY INT
);

CREATE TABLE TABLES (
    TABLE_NUM INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CAPACITY INT,
    STATUS VARCHAR(20)
);

CREATE TABLE ORDERS (
    ORDER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    EMP_ID INT NOT NULL,
    TABLE_NUM INT NOT NULL -- dont need to always have number?
);

CREATE TABLE MENU_ITEM_ORDERS (
    MENU_ITEM_ID INT NOT NULL,
    ORDER_ID INT NOT NULL,
    ORDER_DATE DATE NOT NULL,
    ORDER_QUANTITY INT NOT NULL
);

-- Primary Key Constraints
ALTER TABLE MENU_ITEM_ORDERS
    ADD CONSTRAINT menu_item_orders_pk PRIMARY KEY (MENU_ITEM_ID, ORDER_ID, ORDER_DATE); -- item and orderid and date

-- Foreign Key Constraints
ALTER TABLE LUNCHES
    ADD CONSTRAINT lunches_fk FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE;

ALTER TABLE ALACARTE_MENU_ITEMS
    ADD CONSTRAINT a_la_carte_fk FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE;

ALTER TABLE WORK_SHIFTS
    ADD CONSTRAINT work_shifts_fk FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES(EMP_ID) ON UPDATE CASCADE;

ALTER TABLE ORDERS
    ADD CONSTRAINT orders_fk_1 FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES(EMP_ID) ON UPDATE CASCADE;

ALTER TABLE ORDERS
    ADD CONSTRAINT orders_fk_2 FOREIGN KEY (TABLE_NUM) REFERENCES TABLES(TABLE_NUM) ON UPDATE CASCADE;

ALTER TABLE MENU_ITEM_ORDERS
    ADD CONSTRAINT menu_item_orders_fk_1 FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE;

ALTER TABLE MENU_ITEM_ORDERS
    ADD CONSTRAINT menu_item_orders_fk_2 FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID) ON UPDATE CASCADE;


/*************** Inserts for testing purposes ****************/
-- -------------------------------------------------------
-- LUNCHES OF THE WEEK
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR)
VALUES
-- Monday
('Tomato Basil Soup', 5.99, 'Creamy tomato soup with fresh basil and a touch of garlic, served with a side of artisan bread.'),
('Grilled Cheese Sandwich', 6.99, 'Classic grilled cheese sandwich with a mix of cheddar and mozzarella on sourdough bread.'),
('Caesar Salad', 7.99, 'Crisp romaine lettuce, shaved Parmesan, croutons, and Caesar dressing.'),
-- Tuesday
('Chicken Noodle Soup', 6.49, 'Homemade chicken noodle soup with carrots, celery, and egg noodles.'),
('Turkey Club Wrap', 8.99, 'Sliced turkey, bacon, lettuce, tomato, and ranch dressing in a whole wheat wrap.'),
('Quinoa Avocado Salad', 9.49, 'Quinoa, avocado, cherry tomatoes, cucumber, and feta cheese with lemon vinaigrette.'),
-- Wednesday
('Minestrone Soup', 6.99, 'Italian vegetable soup with beans, zucchini, carrots, and pasta in a tomato broth.'),
('Italian Panini', 8.99, 'Ham, salami, provolone cheese, and pesto aioli on pressed ciabatta bread.'),
('Spinach and Goat Cheese Salad', 8.49, 'Fresh spinach, goat cheese, walnuts, strawberries, and balsamic glaze.'),
-- Thursday
('Butternut Squash Soup', 7.49, 'Roasted butternut squash soup with a hint of cinnamon and cream, served with toasted pumpkin seeds.'),
('Chicken Caesar Wrap', 8.99, 'Grilled chicken, romaine lettuce, Parmesan cheese, and Caesar dressing in a spinach wrap.'),
('Mediterranean Bowl', 10.49, 'Quinoa, hummus, falafel, tomatoes, cucumber, and a dollop of tzatziki.'),
-- Friday
('French Onion Soup', 7.99, 'Caramelized onion soup in a beef broth, topped with a toasted baguette and melted Gruyere cheese.'),
('Beef Brisket Sandwich', 11.99, 'Slow-cooked beef brisket with BBQ sauce on a brioche bun, served with pickles.'),
('Caprese Salad', 8.99, 'Sliced tomatoes, fresh mozzarella, basil, and balsamic reduction.'),
-- Saturday
('Corn Chowder Soup', 6.99, 'Creamy corn chowder with potatoes, bell peppers, and bacon.'),
('BBQ Pulled Pork Sliders', 9.99, 'BBQ pulled pork on mini buns, served with coleslaw on the side.'),
('Asian Chicken Salad', 9.99, 'Mixed greens, grilled chicken, mandarin oranges, almonds, and sesame dressing.'),
-- Sunday
('Broccoli Cheddar Soup', 7.49, 'Creamy broccoli soup with sharp cheddar cheese.'),
('BLT Sandwich', 7.99, 'Bacon, lettuce, and tomato on toasted whole grain bread with mayo.'),
('Greek Salad', 8.99, 'Mixed greens, Kalamata olives, cucumber, tomato, feta cheese, and Greek dressing.');

-- A LA CARTE - DRINKS
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR)
VALUES
    ('Fresh Lemonade', 3.99, 'Homemade lemonade made with freshly squeezed lemons and a touch of mint.'),
    ('Iced Tea', 2.99, 'Brewed black tea served cold with a lemon wedge.'),
    ('Sparkling Water', 1.99, 'Carbonated natural mineral water.'),
    ('Chardonnay', 7.99, 'A glass of crisp and refreshing white wine with notes of apple and citrus.'),
    ('Cabernet Sauvignon', 8.99, 'Full-bodied red wine with flavors of black currant and dark chocolate.'),
    ('Pinot Noir', 9.49, 'Medium-bodied red wine with cherry and raspberry aromas.'),
    ('Sauvignon Blanc', 7.99, 'Crisp and elegant white wine with citrus and tropical fruit flavors.'),
    ('Craft Beer', 6.99, 'Locally brewed craft beer with a unique blend of hops and malts.'),
    ('Pilsner', 5.99, 'Classic pilsner with a crisp and refreshing taste.'),
    ('Merlot', 8.49, 'Smooth red wine with plum and black cherry notes.');

-- A LA CARTE - STARTERS
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR)
VALUES
    ('Bruschetta', 5.99, 'Grilled bread with tomato, basil, garlic, and olive oil.'),
    ('Garlic Bread', 4.99, 'Freshly baked bread with garlic butter and Parmesan cheese.'),
    ('Caprese Skewers', 6.49, 'Fresh mozzarella, cherry tomatoes, and basil drizzled with balsamic glaze.'),
    ('Stuffed Mushrooms', 7.99, 'Mushrooms filled with a creamy garlic and cheese stuffing.'),
    ('Chicken Wings', 8.99, 'Spicy buffalo or BBQ chicken wings served with blue cheese dressing.'),
    ('Caesar Salad', 5.99, 'Crisp romaine lettuce, Parmesan, croutons, and Caesar dressing.'),
    ('Mozzarella Sticks', 6.99, 'Crispy breaded mozzarella served with marinara sauce.'),
    ('Shrimp Cocktail', 9.49, 'Chilled shrimp served with a tangy cocktail sauce.'),
    ('Vegetable Spring Rolls', 7.49, 'Crispy spring rolls filled with fresh vegetables and served with sweet chili sauce.'),
    ('Nachos', 8.99, 'Tortilla chips topped with cheese, jalapenos, salsa, and sour cream.');

-- A LA CARTE - MAINS
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR)
VALUES
    ('Ribeye Steak', 19.99, 'Grilled ribeye steak with herb butter, served with mashed potatoes and asparagus.'),
    ('Salmon Fillet', 17.99, 'Oven-baked salmon with a honey glaze, served with quinoa and steamed broccoli.'),
    ('Chicken Parmesan', 15.99, 'Breaded chicken breast topped with marinara sauce and mozzarella, served over spaghetti.'),
    ('Vegetarian Lasagna', 13.99, 'Layers of pasta, ricotta, mozzarella, spinach, and marinara sauce.'),
    ('Duck Confit', 18.99, 'Slow-cooked duck leg with a crispy skin, served with lentils and red wine sauce.'),
    ('Beef Bourguignon', 16.99, 'Tender beef stewed in red wine with mushrooms, onions, and carrots, served over mashed potatoes.'),
    ('Pork Chop', 14.99, 'Pan-seared pork chop with apple compote and roasted potatoes.'),
    ('Shrimp Scampi', 16.49, 'Sautéed shrimp in a garlic lemon butter sauce, served over linguini.'),
    ('Mushroom Risotto', 12.99, 'Creamy Arborio rice with wild mushrooms and Parmesan cheese.'),
    ('Thai Green Curry', 13.99, 'Spicy green curry with chicken or tofu, eggplant, bell peppers, and basil, served with jasmine rice.');

-- A LA CARTE - DESSERTS
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR)
VALUES
    ('Cheesecake', 6.99, 'Creamy cheesecake on a graham cracker crust with a berry compote.'),
    ('Chocolate Lava Cake', 7.49, 'Warm chocolate cake with a gooey center, served with vanilla ice cream.'),
    ('Tiramisu', 6.49, 'Classic Italian dessert with layers of coffee-soaked ladyfingers and mascarpone cream.'),
    ('Apple Pie', 5.99, 'Traditional apple pie with a flaky crust, served with whipped cream.'),
    ('Creme Brulee', 6.99, 'Rich custard base topped with a layer of hardened caramelized sugar.'),
    ('Lemon Tart', 5.99, 'Tart and refreshing lemon custard in a crisp pastry shell.'),
    ('Panna Cotta', 6.49, 'Smooth Italian dessert of sweetened cream thickened with gelatin and molded.'),
    ('Fruit Sorbet', 4.99, 'Light and refreshing sorbet made with seasonal fruit.'),
    ('Brownie Sundae', 7.99, 'Warm brownie topped with vanilla ice cream, chocolate sauce, and whipped cream.'),
    ('Baklava', 5.49, 'Sweet dessert pastry made of layers of filo filled with chopped nuts and sweetened with honey.');

INSERT INTO LUNCHES (MENU_ITEM_ID, DAY) VALUES
                                            (1, 'Monday'), (2, 'Monday'), (3, 'Monday'),
                                            (4, 'Tuesday'), (5, 'Tuesday'), (6, 'Tuesday'),
                                            (7, 'Wednesday'), (8, 'Wednesday'), (9, 'Wednesday'),
                                            (10, 'Thursday'), (11, 'Thursday'), (12, 'Thursday'),
                                            (13, 'Friday'), (14, 'Friday'), (15, 'Friday'),
                                            (16, 'Saturday'), (17, 'Saturday'), (18, 'Saturday'),
                                            (19, 'Sunday'), (20, 'Sunday'), (21, 'Sunday');

INSERT INTO ALACARTE_MENU_ITEMS (MENU_ITEM_ID, CATEGORY) VALUES
(22, 'Drink'), (23, 'Drink'), (24, 'Drink'), (25, 'Drink'),
(26, 'Drink'), (27, 'Drink'), (28, 'Drink'), (29, 'Drink'),
(30, 'Drink'), (31, 'Drink'),
(32, 'Starter'), (33, 'Starter'), (34, 'Starter'), (35, 'Starter'),
(36, 'Starter'), (37, 'Starter'), (38, 'Starter'), (39, 'Starter'),
(40, 'Starter'), (41, 'Starter'),
(42, 'Main'), (43, 'Main'), (44, 'Main'), (45, 'Main'),
(46, 'Main'), (47, 'Main'), (48, 'Main'), (49, 'Main'),
(50, 'Main'), (51, 'Main'),
(52, 'Dessert'), (53, 'Dessert'), (54, 'Dessert'), (55, 'Dessert'),
(56, 'Dessert'), (57, 'Dessert'), (58, 'Dessert'), (59, 'Dessert'),
(60, 'Dessert'), (61, 'Dessert');

INSERT INTO EVENTS (NAME, PRICE, DESCR, EVENT_YEAR, EVENT_MONTH, EVENT_DAY) VALUES
    ('Jazz Night', 15, 'An evening of classic jazz with live performers.', 2024, 'March', 5),
    ('Wine Tasting', 30, 'Sample a variety of wines from local vineyards.', 2024, 'March', 12),
    ('Salsa Dance Workshop', 25, 'Learn to salsa from professional dancers in a 3-hour workshop.', 2024, 'April', 9),
    ('Italian Cooking Class', 40, 'Hands-on cooking class featuring regional Italian dishes.', 2024, 'April', 22),
    ('Summer Solstice Yoga', 20, 'Sunrise yoga session to celebrate the longest day of the year.', 2024, 'June', 21),
    ('Open Mic Comedy Night', 10, 'Laugh out loud with local comedians.', 2024, 'July', 14),
    ('Indie Film Screening', 12, 'Exclusive screening of a new indie film.', 2024, 'August', 18),
    ('Outdoor Rock Festival', 50, 'Spend the day rocking out to the biggest bands.', 2024, 'September', 5),
    ('Halloween Haunted House', 18, 'Explore our spooky haunted house if you dare!', 2024, 'October', 31),
    ('New Year’s Eve Bash', 100, 'Ring in the new year with a grand celebration.', 2024, 'December', 31);

INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Mark', 'Dillon');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Sandra', 'Roman');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Joseph', 'Armstrong');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Michael', 'Allen');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('William', 'Turner');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Amanda', 'Tran');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Wanda', 'Lynch');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Kristi', 'Sanders');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Christine', 'Hunt');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Ricardo', 'Sandoval');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Andrew', 'Lang');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Ryan', 'Jones');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Adam', 'Dodson');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Collin', 'Jones');
INSERT INTO EMPLOYEES (F_NAME, L_NAME) VALUES ('Maurice', 'Harris');

INSERT INTO WORK_SHIFTS (EMP_ID, SHIFT_TYPE, YEAR, MONTH, DAY) VALUES
(1, 'Morning', 2024, 'March', 1),
(3, 'Evening', 2024, 'March', 1),
(5, 'Morning', 2024, 'March', 2),
(7, 'Evening', 2024, 'March', 2),
(9, 'Morning', 2024, 'April', 3),
(11, 'Evening', 2024, 'April', 3),
(13, 'Morning', 2024, 'April', 4),
(15, 'Evening', 2024, 'April', 4);

/***************************** Queries *******************************/
-- Get all lunches
/*
 SELECT name, price, descr, day FROM menu_items
 JOIN lunches ON
 menu_items.menu_item_id=lunches.menu_item_id;
 */

/*************** Get the entire a la carte menu *******************/
/*
 -- GET STARTERS
 select name, price, descr FROM menu_items mi JOIN alacarte_menu_items carte
 ON mi.menu_item_id=carte.menu_item_id WHERE category="Starter";
 -- GET MAINS
 select name, price, descr FROM menu_items mi JOIN alacarte_menu_items carte
 ON mi.menu_item_id=carte.menu_item_id WHERE category="Main";
 -- GET DESSERTS
 select name, price, descr FROM menu_items mi JOIN alacarte_menu_items carte
 ON mi.menu_item_id=carte.menu_item_id WHERE category="Dessert";
 -- GET DRINKS
 select name, price, descr, mi.menu_item_id FROM menu_items mi JOIN alacarte_menu_items carte
 ON mi.menu_item_id=carte.menu_item_id WHERE category="Drink";
 */

-- Get all workshifts and which employee is working
/*
 SELECT F_NAME, L_NAME, SHIFT_TYPE, MONTH, DAY
 FROM work_shifts JOIN employees ON work_shifts.emp_id=employees.emp_id;
*/
