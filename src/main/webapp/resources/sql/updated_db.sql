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
    TYPE VARCHAR(30),
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
    EVENT_MONTH INT,
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
     DAY VARCHAR(30)
);

CREATE TABLE TABLES (
    TABLE_NUM INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    STATUS BOOLEAN
);

CREATE TABLE ORDERS (
    ORDER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    TABLE_NUM INT NOT NULL, -- dont need to always have number?
    ORDER_DATE DATE,
    TIMESTAMP TIME,
    STATUS_ORDER BOOLEAN,
    STATUS_MAINS BOOLEAN,
    STATUS_START BOOLEAN

);

CREATE TABLE MENU_ITEM_ORDERS (
    MENU_ITEM_ID INT NOT NULL,
    ORDER_ID INT NOT NULL,
    AMOUNT INT,

    PRIMARY KEY ( ORDER_ID ,  MENU_ITEM_ID ),
    CONSTRAINT fk_order
    FOREIGN KEY (ORDER_ID)
    REFERENCES ORDERS(ORDER_ID),
    CONSTRAINT fk_menu_item
    FOREIGN KEY (MENU_ITEM_ID)
    REFERENCES MENU_ITEMS(MENU_ITEM_ID)
);



-- Primary Key Constraints

-- Foreign Key Constraints
ALTER TABLE LUNCHES
    ADD CONSTRAINT lunches_fk FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ALACARTE_MENU_ITEMS
    ADD CONSTRAINT a_la_carte_fk FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE WORK_SHIFTS
    ADD CONSTRAINT work_shifts_fk FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES(EMP_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ORDERS
    ADD CONSTRAINT orders_fk_2 FOREIGN KEY (TABLE_NUM) REFERENCES TABLES(TABLE_NUM) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE MENU_ITEM_ORDERS
    ADD CONSTRAINT menu_item_orders_fk_1 FOREIGN KEY (MENU_ITEM_ID) REFERENCES MENU_ITEMS(MENU_ITEM_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE MENU_ITEM_ORDERS
    ADD CONSTRAINT menu_item_orders_fk_2 FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID) ON UPDATE CASCADE ON DELETE CASCADE;

create view KITCHEN_VIEW as
select `o`.`ORDER_ID`       AS `ORDER_ID`,
       `o`.`TABLE_NUM`      AS `TABLE_NUM`,
       `o`.`STATUS_ORDER`   AS `STATUS_ORDER`,
       `o`.`STATUS_MAINS`   AS `STATUS_MAINS`,
       `o`.`STATUS_START`   AS `STATUS_START`,
       `MIO`.`MENU_ITEM_ID` AS `MENU_ITEM_ID`,
       `MIO`.`AMOUNT`       AS `AMOUNT`,
       `MI`.`NAME`          AS `NAME`,
       `AMI`.`CATEGORY`     AS `CATEGORY`
FROM `restaurang`.`ORDERS` o
         JOIN `restaurang`.`MENU_ITEM_ORDERS` MIO ON o.`ORDER_ID` = MIO.`ORDER_ID`
         JOIN `restaurang`.`MENU_ITEMS` MI ON MIO.`MENU_ITEM_ID` = MI.`MENU_ITEM_ID`
         Left JOIN `restaurang`.`ALACARTE_MENU_ITEMS` AMI ON MI.`MENU_ITEM_ID` = AMI.`MENU_ITEM_ID`;

/************************** Inserts for testing purposes ****************************/
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

INSERT INTO LUNCHES (MENU_ITEM_ID, TYPE, DAY) VALUES
(1, 'SOUP', 'Monday'), (2, 'BUFFET', 'Monday'), (3, 'BUFFET', 'Monday'),
(4, 'SOUP', 'Tuesday'), (5, 'BUFFET', 'Tuesday'), (6, 'BUFFET', 'Tuesday'),
(7, 'SOUP', 'Wednesday'), (8, 'BUFFET', 'Wednesday'), (9, 'BUFFET', 'Wednesday'),
(10, 'SOUP', 'Thursday'), (11, 'BUFFET', 'Thursday'), (12, 'BUFFET', 'Thursday'),
(13, 'SOUP', 'Friday'), (14, 'BUFFET', 'Friday'), (15, 'BUFFET', 'Friday'),
(16, 'SOUP', 'Saturday'), (17, 'BUFFET', 'Saturday'), (18, 'BUFFET', 'Saturday'),
(19, 'SOUP', 'Sunday'), (20, 'BUFFET', 'Sunday'), (21, 'BUFFET', 'Sunday');

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
('Jazz Night', 15, 'An evening of classic jazz with live performers.', 2024, 3, 5),
('Wine Tasting', 30, 'Sample a variety of wines from local vineyards.', 2024, 3, 12),
('Salsa Dance Workshop', 25, 'Learn to salsa from professional dancers in a 3-hour workshop.', 2024, 4, 9),
('Italian Cooking Class', 40, 'Hands-on cooking class featuring regional Italian dishes.', 2024, 4, 22),
('Summer Solstice Yoga', 20, 'Sunrise yoga session to celebrate the longest day of the year.', 2024, 6, 21),
('Open Mic Comedy Night', 10, 'Laugh out loud with local comedians.', 2024, 7, 14),
('Indie Film Screening', 12, 'Exclusive screening of a new indie film.', 2024, 8, 18),
('Outdoor Rock Festival', 50, 'Spend the day rocking out to the biggest bands.', 2024, 9, 5),
('Halloween Haunted House', 18, 'Explore our spooky haunted house if you dare!', 2024, 10, 31),
('New Year’s Eve Bash', 100, 'Ring in the new year with a grand celebration.', 2024, 12, 31);

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
(1, 'Morning', 2024, 'March', 'Monday'),
(3, 'Evening', 2024, 'March', 'Monday'),
(5, 'Morning', 2024, 'March', 'Tuesday'),
(7, 'Evening', 2024, 'March', 'Tuesday'),
(9, 'Morning', 2024, 'March', 'Wednesday'),
(11, 'Evening', 2024, 'March', 'Wednesday'),
(13, 'Morning', 2024, 'March', 'Thursday'),
(15, 'Evening', 2024, 'March', 'Thursday');

-- Insert tables
INSERT INTO TABLES (TABLE_NUM, STATUS)
VALUES
(1, 0),
(2, 1),
(3, 0),
(4, 0),
(5, 1),
(6, 0),
(7, 1),
(8, 0),
(9, 1),
(10, TRUE);


-- Assume we have 10 menu items already, with IDs from 1 to 10
-- Insert orders for the 10 tables
INSERT INTO ORDERS (TABLE_NUM, ORDER_DATE, TIMESTAMP, STATUS_ORDER, STATUS_START, STATUS_MAINS)
VALUES
(1, '2024-02-29', '17:29:36',0 ,0,0),
(2, '2024-02-28', '17:18:36', 1,1,1),
(3, '2024-02-27', '17:31:36', 0, 0 ,1),
(4, '2024-02-26', '17:08:36', 1,1,1),
(5, '2024-02-25', '17:31:36', 0, 0,0),
(6, '2024-02-24', '17:35:36', 1,1,1),
(7, '2024-02-23', '16:54:36', 1,0 ,0),
(8, '2024-02-22', '16:57:36', 0, 1,0),
(9, '2024-02-21', '16:53:36', 0,0 ,0),
(10, '2024-02-20', '17:25:36', 1, 1,1);


-- Insert menu item orders
-- This will assign 2 menu items to each order
-- We're using a simple loop pattern for this example, but you might have specific requirements for each order
INSERT INTO MENU_ITEM_ORDERS (MENU_ITEM_ID, ORDER_ID, AMOUNT) VALUES
(1, 1, 2), (2, 1, 3),
(3, 2, 1), (4, 2, 2),
(5, 3, 2), (6, 3, 1),
(7, 4, 1), (8, 4, 2),
(9, 5, 1), (10, 5, 3),
(1, 6, 2), (2, 6, 1),
(32, 4, 1), (46, 7, 1),
(5, 8, 2), (6, 8, 2),
(7, 9, 3), (8, 9, 1),
(47, 7, 1), (4, 7, 1),
(22, 1, 2), (41, 8, 2),
(36, 4, 3), (42, 9, 1),
(45, 10, 2), (56, 10, 2);

INSERT INTO RESERVATIONS (NUM_OF_GUESTS, RES_YEAR, RES_MONTH, RES_DAY, RES_TIME, CUST_NAME)
VALUES
(2, 2024, 'April', 12, '18:00:00', 'John Smith'),
(4, 2024, 'April', 15, '19:30:00', 'Emily Johnson'),
(6, 2024, 'April', 20, '20:00:00', 'Michael Brown'),
(3, 2024, 'May', 5, '17:45:00', 'Jessica White'),
(5, 2024, 'May', 10, '18:30:00', 'David Harris'),
(8, 2024, 'May', 22, '20:00:00', 'Laura Jones'),
(2, 2024, 'June', 2, '19:00:00', 'Sarah Martinez'),
(10, 2024, 'June', 14, '18:30:00', 'Daniel Garcia'),
(7, 2024, 'June', 18, '19:45:00', 'Olivia Williams'),
(4, 2024, 'July', 4, '18:00:00', 'James Wilson');



/***************************** Queries *******************************/
-- Get all lunches
/*
 SELECT name, price, descr, day, type, menu_items.menu_item_id FROM menu_items
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

-- ****************** GET ALL ORDERS *******************
/*
 SELECT
    o.ORDER_ID,
    o.order_date,
    o.timestamp,
    t.TABLE_NUM,
    t.STATUS AS order_status,
    mi.NAME AS menu_item_name,
    mi.PRICE AS menu_item_price,
    mio.AMOUNT
FROM
    ORDERS o
JOIN
    TABLES t ON o.TABLE_NUM = t.TABLE_NUM
JOIN
    MENU_ITEM_ORDERS mio ON o.ORDER_ID = mio.ORDER_ID
JOIN
    MENU_ITEMS mi ON mio.MENU_ITEM_ID = mi.MENU_ITEM_ID;
 */