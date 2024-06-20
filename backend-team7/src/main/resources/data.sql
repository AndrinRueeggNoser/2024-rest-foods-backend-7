-- DISH
INSERT INTO dish (dish_name, description, image, is_chefs_choice, region, price)
VALUES ('Pizza', 'Italian speciality', 'https://www.pizza.png', FALSE, 'Italy', 12),
       ('Sushi', 'Japanese speciality', 'https://www.sushi.png', TRUE, 'Japan', 20.00),
       ('Taco', 'Mexican speciality', 'https://www.taco.png', FALSE, 'Mexico', 8.50);

-- RESTAURANT TABLE
INSERT INTO restaurant_table (table_id, amount_of_seats, is_available)
VALUES (1, 6, TRUE),
       (2, 4, TRUE),
       (3, 10, FALSE);


-- RESERVATION
INSERT INTO reservation (reservation_id, start_time, end_time, person_count)
VALUES (1, 1800, 2000, 4),
       (2, 1900, 2100, 2),
       (3, 2000, 2200, 6);

-- ROLES
INSERT INTO role(role_id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'USER');


-- AUTHORITIES
INSERT INTO authority (authority_id, authority_name)
VALUES (1, 'GET'),
       (2, 'POST'),
       (3, 'PUT'),
       (4, 'DELETE');

-- ADMIN can CRUD, User can R
INSERT INTO role_authorities (id_authority, id_role)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (1, 2);

-- USER
INSERT INTO users (first_name, last_name, phone_number, email, password, id_role)
VALUES ('ADMIN', 'ADMIN', '1234567890', 'aaaa@aaa', '1234', 1),
       ('USER', 'USER', '0987654321', 'aaa@aaa', '1234', 2);