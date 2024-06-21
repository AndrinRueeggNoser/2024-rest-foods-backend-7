-- DISH
INSERT INTO dish (dish_name, description, image, is_chefs_choice, region, price)
VALUES ('Pizza', 'Italian speciality', 'https://img.zeit.de/zeit-magazin/wochenmarkt/2023-08/pizza-margherita-giovanni-stincone-rezept/square__960x960', FALSE, 'Italy', 12),
       ('Sushi', 'Japanese speciality', 'https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/full_width_tablet_4_3/public/2022-12/2022_sake-maki-sushi_aufmacher.jpg?h=4521fff0&itok=omM42IC2', TRUE, 'Japan', 20.00),
       ('Taco', 'Mexican speciality', 'https://www.thespruceeats.com/thmb/ereeBcFkDEbDT2VSlDe34sqXO_8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chicken-tinga-tinga-de-pollo-4773239-Hero_01-1bd1d960c02a4fdb812323b8c60fd55b.jpg', FALSE, 'Mexico', 8.50);

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
VALUES ('ADMIN', 'ADMIN', '1234567890', 'admin@admin.com', '1234', 1),
       ('USER', 'USER', '0987654321', 'user@user.dom', '1234', 2);