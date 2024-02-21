
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
    DATE DATE,
    DAY VARCHAR(30)
);

CREATE TABLE A_LA_CARTE_MENU_ITEMS (
    CARTE_ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MENU_ITEM_ID INT NOT NULL,
    CATEGORY VARCHAR(40)
);

CREATE TABLE EVENTS (
    EVENT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    PRICE INT,
    DESCR VARCHAR(200),
    EVENT_DATE DATE,
    START_TIME TIME -- dont need startTime can be special day
);

CREATE TABLE RESERVATIONS (
    RES_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NUM_OF_GUESTS INT NOT NULL,
    RES_DATE DATE NOT NULL,
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
    SHIFT_DATE DATE NOT NULL
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

ALTER TABLE A_LA_CARTE_MENU_ITEMS
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


-- Inserts for testing purposes
-- -------------------------------------------------------
INSERT INTO MENU_ITEMS (NAME, PRICE, DESCR) VALUES
('Classic Cheeseburger', 8.99, 'A juicy beef patty with melted cheese, lettuce, tomato, onions, and pickles on a toasted bun'),
('Vegetarian Pizza', 12.50, 'Fresh bell peppers, onions, mushrooms, olives, and tomatoes on a classic marinara base with mozzarella'),
('Spicy Chicken Wings', 7.50,'Ten wings tossed in a fiery buffalo sauce, served with celery sticks and blue cheese dressing'),
('Tom Yum Soup', 5.99, 'A hot and sour Thai soup with shrimp, mushrooms, lemongrass, and lime leaves'),
('Caesar Salad', 6.99, 'Crispy romaine lettuce, Parmesan cheese, croutons, and Caesar dressing'),
('Mushroom Risotto', 11.99, 'Creamy Arborio rice with sautéed mushrooms and a hint of truffle oil'),
('Fish Tacos', 9.99, 'Grilled white fish, cabbage slaw, and avocado lime dressing in corn tortillas'),
('Beef Pho', 8.99, 'Vietnamese noodle soup with thinly sliced beef, rice noodles, and herbs'),
('Falafel Wrap', 7.99, 'Crispy falafel balls, tahini sauce, lettuce, tomatoes, and cucumbers in a pita wrap'),
('Chocolate Lava Cake', 6.50, 'Warm chocolate cake with a gooey center, served with vanilla ice cream'),
('Sushi Platter', 14.99, 'An assortment of fresh sushi rolls, including salmon, tuna, and cucumber'),
('BBQ Pork Ribs', 15.99, 'Slow-cooked ribs smothered in BBQ sauce, served with fries and coleslaw'),
('Pad Thai', 10.99, 'Stir-fried rice noodles with shrimp, peanuts, egg, and bean sprouts in a tangy sauce'),
('Quinoa Salad', 8.99, 'Quinoa, arugula, avocado, cherry tomatoes, and feta cheese with a lemon vinaigrette'),
('Lamb Curry', 13.99, 'Tender lamb pieces in a rich and spicy curry sauce, served with rice'),
('Chicken Caesar Salad', 10.99, 'Grilled chicken, romaine lettuce, croutons, and Caesar dressing'),
('Margherita Pizza', 12.99, 'Tomato sauce, mozzarella cheese, and fresh basil on thin crust'),
('Spaghetti Carbonara', 14.99, 'Spaghetti pasta with creamy carbonara sauce, pancetta, and Parmesan cheese'),
('Beef Burger', 11.99, 'Grilled beef patty, lettuce, tomato, onion, and pickles on a sesame seed bun'),
('Vegetable Stir-Fry', 9.99, 'Assorted vegetables stir-fried in a savory sauce, served with steamed rice'),
('Soup of the Day', 5.99, 'Chef\'s daily selection of homemade soup, served with bread'),
('Tomato Basil Soup', 4.99, 'Classic tomato soup with fresh basil, served with garlic bread'),
('Minestrone Soup', 4.99, 'Hearty Italian vegetable soup with pasta, served with bread'),
('Cream of Mushroom Soup', 5.99, 'Creamy mushroom soup garnished with parsley, served with croutons'),
('French Onion Soup', 6.99, 'Rich beef broth with caramelized onions, topped with melted cheese and croutons'),
('Garlic Bread', 3.99, 'Toasted baguette slices brushed with garlic butter and herbs'),
('Caprese Salad', 8.99, 'Fresh mozzarella, ripe tomatoes, basil leaves, olive oil, and balsamic glaze'),
('Bruschetta', 6.99, 'Toasted bread topped with diced tomatoes, garlic, basil, and olive oil'),
('Crispy Calamari', 9.99, 'Lightly battered and fried calamari rings served with marinara sauce'),
('Spinach and Artichoke Dip', 7.99, 'Creamy spinach and artichoke dip served with tortilla chips'),
('Grilled Salmon', 16.99, 'Fresh Atlantic salmon fillet grilled to perfection, served with roasted vegetables'),
('Beef Tenderloin', 22.99, 'Juicy beef tenderloin steak cooked to your liking, served with mashed potatoes'),
('Chicken Parmesan', 13.99, 'Breaded chicken breast topped with marinara sauce and melted mozzarella, served with spaghetti'),
('Vegetable Lasagna', 12.99, 'Layers of pasta, marinara sauce, assorted vegetables, and melted cheese'),
('Shrimp Scampi', 18.99, 'Sautéed shrimp in garlic butter sauce, served over linguine pasta'),
('New York Cheesecake', 7.99, 'Creamy cheesecake on a graham cracker crust, topped with strawberry sauce'),
('Chocolate Lava Cake', 8.99, 'Warm chocolate cake with a molten chocolate center, served with vanilla ice cream'),
('Tiramisu', 6.99, 'Classic Italian dessert made with layers of coffee-soaked ladyfingers and mascarpone cheese'),
('Apple Pie', 6.99, 'Traditional apple pie with a flaky crust, served with a scoop of vanilla ice cream'),
('Mojito', 8.99, 'Refreshing cocktail made with rum, lime juice, mint leaves, and soda water'),
('Margarita', 7.99, 'Classic cocktail made with tequila, lime juice, and triple sec, served with a salt rim'),
('Cosmopolitan', 9.99, 'Vodka, cranberry juice, triple sec, and lime juice, shaken with ice and strained'),
('Old Fashioned', 10.99, 'Bourbon whiskey muddled with sugar, bitters, and a twist of orange peel'),
('Mai Tai', 11.99, 'Rum, lime juice, orgeat syrup, and triple sec, served over crushed ice with a pineapple garnish'),
('White Russian', 9.99, 'Vodka, coffee liqueur, and cream served over ice in an old-fashioned glass'),
('Mimosa', 6.99, 'Champagne mixed with orange juice, served in a champagne flute'),
('Pina Colada', 8.99, 'Rum, coconut cream, and pineapple juice blended with ice, served with a pineapple wedge'),
('Bellini', 7.99, 'Prosecco mixed with peach purée, served in a champagne flute'),
('Lemonade', 3.99, 'Freshly squeezed lemon juice sweetened with sugar, served over ice'),
('Iced Tea', 3.99, 'Chilled black tea served over ice with a slice of lemon'),
('Orange Juice', 2.99, 'Freshly squeezed orange juice, served cold'),
('Cappuccino', 4.99, 'Espresso topped with steamed milk and a layer of froth'),
('Café Latte', 4.99, 'Espresso mixed with steamed milk, topped with a small amount of frothed milk'),
('Espresso', 3.99, 'Strong black coffee made by forcing hot water through finely-ground coffee beans'),
('Hot Chocolate', 4.99, 'Rich chocolate drink made with steamed milk and cocoa powder'),
('Green Salad', 5.99, 'Mixed greens, cucumber, cherry tomatoes, and balsamic vinaigrette'),
('Fettuccine Alfredo', 15.99, 'Fettuccine pasta with creamy Alfredo sauce, topped with Parmesan cheese'),
('Grilled Chicken Sandwich', 10.99, 'Grilled chicken breast, lettuce, tomato, and mayo on a toasted bun'),
('Fish and Chips', 13.99, 'Beer-battered fish fillets served with French fries and tartar sauce'),
('Caesar Salad', 9.99, 'Romaine lettuce, croutons, Parmesan cheese, and Caesar dressing'),
('BBQ Ribs', 17.99, 'Slow-cooked pork ribs glazed with barbecue sauce, served with coleslaw and fries'),
('Key Lime Pie', 7.99, 'Tangy key lime filling in a graham cracker crust, topped with whipped cream'),
('Banoffee Pie', 8.99, 'Toffee, bananas, and whipped cream in a buttery biscuit crust'),
('Gelato', 6.99, 'Italian-style ice cream available in various flavors'),
('Mint Julep', 9.99, 'Bourbon, sugar, water, and mint leaves, served over crushed ice'),
('Sangria', 10.99, 'Red wine mixed with fruit juice, soda water, and chopped fruit'),
('Long Island Iced Tea', 12.99, 'Vodka, tequila, rum, gin, triple sec, sour mix, and a splash of cola'),
('Bloody Mary', 8.99, 'Vodka, tomato juice, Worcestershire sauce, hot sauces, and various spices'),
('Martini', 11.99, 'Gin and dry vermouth, stirred or shaken with ice, and garnished with an olive or lemon twist');


INSERT INTO LUNCHES (MENU_ITEM_ID, DAY) VALUES
(1, 'Monday'), (2, 'Monday'), (3, 'Monday'),
(4, 'Tuesday'), (5, 'Tuesday'), (6, 'Tuesday'),
(7, 'Wednesday'), (8, 'Wednesday'), (9, 'Wednesday'),
(10, 'Thursday'), (11, 'Thursday'), (12, 'Thursday'),
(13, 'Friday'), (14, 'Friday'), (15, 'Friday'),
(16, 'Saturday'), (17, 'Saturday'), (18, 'Saturday'),
(19, 'Sunday'), (20, 'Sunday'), (21, 'Sunday');

-- Get all lunches
/*
 SELECT name, price, descr, day FROM menu_items
 JOIN lunches ON
 menu_items.menu_item_id=lunches.menu_item_id;
 */

INSERT INTO A_LA_CARTE_MENU_ITEMS (MENU_ITEM_ID, CATEGORY) VALUES
(40, 'Drink'), (41, 'Drink'), (47, 'Drink'),
(38, 'Dessert'), (39, 'Dessert'), (37, 'Dessert'),
(31, 'Starter'), (35, 'Starter'), (28, 'Starter'),
(33, 'Main'), (59, 'Main'), (61, 'Main');

-- Get the entire a la carte menu
/*

 */

INSERT INTO EVENTS (NAME, PRICE, DESCR, EVENT_DATE) VALUES
('Jazz Night', 15, 'An evening of classic jazz with live performers.', '2024-03-05'),
('Wine Tasting', 30, 'Sample a variety of wines from local vineyards.', '2024-03-12'),
('Salsa Dance Workshop', 25, 'Learn to salsa from professional dancers in a 3-hour workshop.', '2024-04-09'),
('Italian Cooking Class', 40, 'Hands-on cooking class featuring regional Italian dishes.', '2024-04-22'),
('Summer Solstice Yoga', 20, 'Sunrise yoga session to celebrate the longest day of the year.', '2024-06-21'),
('Open Mic Comedy Night', 10, 'Laugh out loud with local comedians.', '2024-07-14'),
('Indie Film Screening', 12, 'Exclusive screening of a new indie film.', '2024-08-18'),
('Outdoor Rock Festival', 50, 'Spend the day rocking out to the biggest bands.', '2024-09-05'),
('Halloween Haunted House', 18, 'Explore our spooky haunted house if you dare!', '2024-10-31'),
('New Year’s Eve Bash', 100, 'Ring in the new year with a grand celebration.', '2024-12-31');

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

INSERT INTO WORK_SHIFTS (EMP_ID, SHIFT_TYPE, SHIFT_DATE) VALUES
(1, 'Morning', '2024-03-01'),
(3, 'Evening', '2024-03-01'),
(5, 'Morning', '2024-03-02'),
(7, 'Evening', '2024-03-02'),
(9, 'Morning', '2024-03-03'),
(11, 'Evening', '2024-03-03'),
(13, 'Morning', '2024-03-04'),
(15, 'Evening', '2024-03-04');

-- Queries
/* ---------- get all workshifts and what employee is working --------------
SELECT F_NAME, L_NAME, SHIFT_TYPE, SHIFT_DATE
FROM work_shifts JOIN employees ON work_shifts.emp_id=employees.emp_id;
*/
