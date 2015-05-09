INSERT INTO service_user
(user_id, email, password, phone, sex, driver, admin, group_name, car_number, driver_license, ignored_times, activated, registration_date)
VALUES
  (12345, 'vadimka2995@gmail.com', 'password2A', '+38063111111', null, false, true, null, null, null, 0, true, '2015-05-09 21:37:19.145000');

INSERT INTO service_user
(user_id, email, password, phone, sex, driver, admin, group_name, car_number, driver_license, ignored_times, activated, registration_date)
VALUES
  (123456, 'rusan.rus@gmail.com1', 'password2A', '+380635293836', 'M', true, false, null, null, 'A, B, C', 0, true, '2015-05-09 21:37:19.145000');


INSERT INTO taxi_order
(tracking_number, status, service, price, user_id, description, car_category, way_of_payment, driver_sex, ordered_date, arrive_date, end_date, music_style, animal_transportation, free_wifi, non_smoking_driver, air_conditioner, comment)
VALUES
  (1, 'QUEUED', 'SIMPLE_TAXI', 121.00, 12345, 'Black man', 'BUSINESS_CLASS', 'CASH', 'M', '2015-07-02 21:34:45.488000', null, null, 'BLUES', true, null, null, true, null);

INSERT INTO taxi_order
(tracking_number, status, service, price, user_id, description, car_category, way_of_payment, driver_sex, ordered_date, arrive_date, end_date, music_style, animal_transportation, free_wifi, non_smoking_driver, air_conditioner, comment) VALUES (3, 'COMPLETED', 'SOBER_DRIVER', 455.00, 12345, 'I will be standing on my head', 'ECONOMY_CLASS', 'VISA_CARD', 'F', '2015-01-01 00:00:00.000000', '2015-01-01 01:00:00.000000', '2015-01-01 04:00:04.000000', null, null, null, null, null, null);

INSERT INTO taxi_order
(tracking_number, status, service, price, user_id, description, car_category, way_of_payment, driver_sex, ordered_date, arrive_date, end_date, music_style, animal_transportation, free_wifi, non_smoking_driver, air_conditioner, comment)
VALUES
  (2, 'ASSIGNED', 'SIMPLE_TAXI', 250.00, 12345, 'Long man', 'VAN', 'CASH', 'A', '2015-07-02 21:34:45.488000', null, null, 'ROCK', false, true, true, false, null);

INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, ordered_quantity, driver_id) VALUES (1, 1, '[(50.454660, 30.523800), (50.419502, 30.400336)]', 50.0, 123456);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, ordered_quantity, driver_id) VALUES (2, 2, '[(50.398003, 30.634574), (50.460360, 30.630363), (50.456112, 30.366299)]', 150.0, 123456);
INSERT INTO taxi_order_item (taxi_item_id, tracking_numer, path, ordered_quantity, driver_id) VALUES (3, 3, '[(50.398003, 30.634574), (50.419502, 30.400336)]', 1234.0, 123456);
