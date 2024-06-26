-- DISH
INSERT INTO dish (dish_name, description, image, is_chefs_choice, region, price)
VALUES ('Pizza', 'Italian speciality',
        'https://img.zeit.de/zeit-magazin/wochenmarkt/2023-08/pizza-margherita-giovanni-stincone-rezept/square__960x960',
        FALSE, 'Italy', 12),
       ('Sushi', 'Japanese speciality',
        'https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/full_width_tablet_4_3/public/2022-12/2022_sake-maki-sushi_aufmacher.jpg?h=4521fff0&itok=omM42IC2',
        TRUE, 'Japan', 20.00),
       ('Taco', 'Mexican speciality',
        'https://www.thespruceeats.com/thmb/ereeBcFkDEbDT2VSlDe34sqXO_8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chicken-tinga-tinga-de-pollo-4773239-Hero_01-1bd1d960c02a4fdb812323b8c60fd55b.jpg',
        FALSE, 'Mexico', 8.50),
       ('Croissant', 'French pastry',
        'https://www.einfachbacken.de/sites/einfachbacken.de/files/styles/facebook/public/2021-09/croissant_1.jpg?h=4521fff0&itok=u9_k6RIu',
        FALSE, 'France', 3.50),
       ('Paella', 'Spanish speciality', 'https://www.justspices.de/media/recipe/Kurkuma_Meeresfruechte_Paella-4.jpg',
        FALSE, 'Spain', 18.00),
       ('Butter Chicken', 'Indian speciality',
        'https://images.services.kitchenstories.io/DnuV2UQLbLsafICFmiMEUCMXXOg=/3840x0/filters:quality(85)/images.kitchenstories.io/wagtailOriginalImages/SV0207-photo-final-2.jpg',
        TRUE, 'India', 15.00),
       ('Fish and Chips', 'British speciality',
        'https://www.thespruceeats.com/thmb/sdVTq0h7xZvJjPr6bE2fhh5M3NI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/SES-best-fish-and-chips-recipe-434856-hero-01-27d8b57008414972822b866609d0af9b.jpg',
        TRUE, 'UK', 10.00),
       ('Baklava', 'Turkish speciality',
        'https://www.einfachbacken.de/sites/einfachbacken.de/files/styles/full_width_tablet_4_3/public/2020-04/baklava.jpg?h=4521fff0&itok=Bms1Mm86',
        FALSE, 'Turkey', 5.00);

-- RESTAURANT TABLE
INSERT INTO restaurant_table (table_id, amount_of_seats, is_available)
VALUES (1, 6, TRUE),
       (2, 4, TRUE),
       (3, 10, FALSE);

-- RESERVATION
INSERT INTO reservation (reservation_id, reservation_time, person_count, table_id)
VALUES (1, '2024-06-26 13:00:00', 4, 1),
       (2, '2024-06-26 15:00:00', 2, 2),
       (3, '2024-06-26 18:00:00', 6, 3);

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
