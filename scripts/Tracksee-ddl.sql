DROP VIEW IF EXISTS music_report;
DROP VIEW IF EXISTS driver_report;
DROP VIEW IF EXISTS car_report;
DROP TABLE IF EXISTS tracksee.public.Taxi_Order_Item;
DROP TABLE IF EXISTS tracksee.public.Favorite_Place;
DROP TABLE IF EXISTS tracksee.public.Taxi_Order;
DROP TABLE IF EXISTS tracksee.public.Service_User;
DROP TABLE IF EXISTS tracksee.public.Car;
DROP TABLE IF EXISTS tracksee.public.Taxi_Price;
DROP TABLE IF EXISTS tracksee.public.Config;

-- CREATION --

CREATE TABLE IF NOT EXISTS tracksee.public.Config
(
  name  VARCHAR(55) UNIQUE NOT NULL,
  value VARCHAR(120)       NOT NULL
);

CREATE TABLE IF NOT EXISTS tracksee.public.Taxi_Price
(
  price_per_km  NUMERIC(10, 2) NOT NULL,
  price_per_min NUMERIC(10, 2) NOT NULL,
  car_category  VARCHAR(28),
  weekend       BOOLEAN,
  night_tariff  BOOLEAN,
  PRIMARY KEY (car_category, weekend, night_tariff)
);

CREATE TABLE IF NOT EXISTS tracksee.public.Car
(
  car_number                       VARCHAR(55) PRIMARY KEY,
  car_model                        VARCHAR(55) NOT NULL,
  color                            VARCHAR(28) NOT NULL,
  car_category                     VARCHAR(28) NOT NULL,
  accepts_visa                     BOOLEAN     NOT NULL,
  animal_transportation_applicable BOOLEAN     NOT NULL,
  free_wifi                        BOOLEAN     NOT NULL,
  air_conditioner                  BOOLEAN     NOT NULL
);

CREATE TABLE IF NOT EXISTS tracksee.public.Service_User
(
  user_id           SERIAL PRIMARY KEY,
  email             VARCHAR(254) UNIQUE NOT NULL,
  password          CHAR(64)            NOT NULL,
  salt              CHAR(8)             NOT NULL,
  phone             VARCHAR(28),
  first_name        VARCHAR(50),
  last_name         VARCHAR(50),
  group_name        VARCHAR(250),
  sex               CHAR(1),
  driver            BOOLEAN             NOT NULL DEFAULT FALSE,
  admin             BOOLEAN             NOT NULL DEFAULT FALSE,
  car_number        VARCHAR(55) UNIQUE REFERENCES Car (car_number) ON DELETE RESTRICT,
  driver_license    VARCHAR(55),
  ignored_times     INT                 NOT NULL DEFAULT 0,
  activated         BOOLEAN             NOT NULL DEFAULT FALSE,
  registration_date TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tracksee.public.Favorite_Place
(
  name     VARCHAR(120) NOT NULL,
  user_id  INT          NOT NULL REFERENCES Service_User (user_id) ON DELETE CASCADE,
  location GEOMETRY,
  PRIMARY KEY (name, user_id)
);

CREATE TABLE IF NOT EXISTS tracksee.public.Taxi_Order
(
  tracking_number       BIGSERIAL PRIMARY KEY,
  status                VARCHAR(28) NOT NULL,
  service               VARCHAR(28),
  price                 NUMERIC(10, 2),
  user_id               INT REFERENCES Service_User (user_id) ON DELETE SET NULL,
  description           VARCHAR(250),
  car_category          VARCHAR(28),
  way_of_payment        VARCHAR(28),
  driver_sex            CHAR(1),
  ordered_date          TIMESTAMP,
  arrive_date           TIMESTAMP,
  amount_of_hours       INT DEFAULT NULL,
  amount_of_minutes     INT DEFAULT NULL,
  amount_of_cars        INT DEFAULT NULL,
  music_style           VARCHAR(50),
  animal_transportation BOOLEAN,
  free_wifi             BOOLEAN,
  non_smoking_driver    BOOLEAN,
  air_conditioner       BOOLEAN,
  comment               VARCHAR(400)
);

CREATE TABLE IF NOT EXISTS tracksee.public.Taxi_Order_Item
(
  taxi_item_id     BIGSERIAL PRIMARY KEY,
  tracking_number   INT NOT NULL REFERENCES Taxi_Order (tracking_number) ON DELETE CASCADE,
  path             GEOMETRY,
  ordered_quantity NUMERIC(15, 1),
  -- need to be checked if this 'user_id' is a driver's id on code layer
  driver_id        INT REFERENCES Service_User (user_id) ON DELETE SET NULL
);

-- VIEW for music-style overall reports
CREATE OR REPLACE VIEW music_report
AS SELECT max(tracking_number) id, music_style, count(music_style) music_count
FROM taxi_order
WHERE music_style IS NOT NULL
GROUP BY music_style;

-- VIEW for driver-sex reports
CREATE OR REPLACE VIEW driver_report AS select max(tracking_number) id, driver_sex, count(driver_sex) order_count from taxi_order GROUP BY driver_sex;
-- VIEW for car-category reports
CREATE OR REPLACE VIEW car_report AS SELECT max(tracking_number) id, car_category, count(car_category) ordered_count FROM taxi_order GROUP BY car_category;
