INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (2, 'order', 'Completed', 30.00, 1, 'SOBER_DRIVER', 'ECONOMY_CLASS', 'CASH', 'F', 'BLUES', false, false, false, false, 'null');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (3, 'order', 'Refused', 0.00 , 1, 'CELEBRATION_TAXI', 'ECONOMY_CLASS', 'CASH', 'F', 'CLASSICAL_MUSIC', false, false, false, false, 'null');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (4, 'order', 'Completed', 72.00, 1, 'CELEBRATION_TAXI', 'BUSINESS_CLASS', 'CASH', 'F', 'ROCK', false, true, true, false, 'everything is good');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (5, 'order', 'Completed', 64.00, 1, 'null', 'ECONOMY_CLASS', 'VISA_CARD', 'F', 'ROCK', false, true, true, true, 'Not bad');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (6, 'order', 'In progress', 70.00, 1, 'null', 'BUSINESS_CLASS', 'CASH', 'M', 'JAZZ', true, true, true, true, 'null');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (7, 'order', 'Completed', 36.00, 1, 'CONVEY_CORPORATION_EMPLOYEES', 'VAN', 'VISA_CARD', 'M', 'DANCE_MUSIC', false, false, false, false, 'Comfortable');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (8, 'order', 'Assigned', 112.00, 1, 'MEET_MY_GUEST', 'BUSINESS_CLASS', 'VISA_CARD', 'F', 'BLUES', true, true, true, true, 'null');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (9, 'order', 'Completed', 65.00, 1, 'MEET_MY_GUEST', 'ECONOMY_CLASS', 'CASH', 'F', 'JAZZ', true, true, true, true, 'Fun driver');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (11, 'order', 'Completed', 86.00, 1, 'GUEST_DELIVERY', 'ECONOMY_CLASS', 'CASH', 'M', 'ELECTRONIC_MUSIC', false, true, true, true, 'everything is good');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (1, 'order', 'Completed', 40.00, 1, 'null', 'BUSINESS_CLASS', 'VISA_CARD', 'M', 'DEFAULT', true, true, true, true, 'everything is good');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (12, 'order', 'Completed', 75.00, 1, 'CARGO_TAXI', 'VAN', 'CASH', 'F', 'HIP_HOP', true, true, false, true, 'null');
INSERT INTO taxi_order (tracking_number, description, status, price, user_id, service, car_category, way_of_payment, driver_sex, music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment) VALUES (10, 'order', 'Queued', 78.00, 1, 'CONVEY_CORPORATION_EMPLOYEES', 'BUSINESS_CLASS', 'CASH', 'F', 'CLASSICAL_MUSIC', false, true, true, true, 'null');


--
-- TOC entry 2061 (class 0 OID 26464)
-- Dependencies: 181
-- Data for Name: taxi_order_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (1, 1, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (2, 2, '((-122.34999999999999,-142.34299999999999))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (3, 3, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (4, 4, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (5, 5, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (6, 6, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (7, 7, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (8, 8, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (9, 9, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (10, 10, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (11, 10, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (12, 10, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (13, 10, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (14, 11, '((-122.36,-122.343))', 1);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, driver_id) VALUES (15, 12, '((-122.36,-122.343))', 1);