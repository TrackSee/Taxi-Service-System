DROP TABLE IF EXISTS Taxi_Order_Item;
DROP TABLE IF EXISTS Taxi_Order;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Service_User;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Taxi_Price;

DROP TYPE IF EXISTS Service;
DROP TYPE IF EXISTS Sex;
DROP TYPE IF EXISTS Car_Category;
DROP TYPE IF EXISTS Way_Of_Payment;
DROP TYPE IF EXISTS Order_Status;

-- CREATION --

CREATE TYPE Car_Category AS ENUM ('BUSINESS_CLASS', 'ECONOMY_CLASS', 'VAN');
CREATE TYPE Sex AS ENUM ('MALE', 'FEMALE');
CREATE TYPE Way_Of_Payment AS ENUM ('VISA_CARD', 'CASH');
CREATE TYPE Order_Status AS ENUM ('QUEUED', 'ASSIGNED', 'UPDATED', 'REFUSED', 'IN_PROGRESS', 'COMPLETED');
CREATE TYPE Service AS ENUM ('SOBER_DRIVER', 'CONVEY_CORPORATION_EMPLOYEES', 'GUEST_DELIVERY',
  'CARGO_TAXI', 'MEET_MY_GUEST', 'CELEBRATION_TAXI', 'FOODSTUFF_DELIVERY');

CREATE TABLE IF NOT EXISTS Config
(
  name  VARCHAR(55) UNIQUE NOT NULL,
  value VARCHAR(120)       NOT NULL
);

CREATE TABLE IF NOT EXISTS Taxi_Price
(
  price_per_km  MONEY NOT NULL,
  price_per_min MONEY NOT NULL,
  car_category  Car_Category,
  weekend       BOOLEAN,
  night_tariff  BOOLEAN,
  PRIMARY KEY (car_category, weekend, night_tariff)
);

CREATE TABLE IF NOT EXISTS Car
(
  car_number                       VARCHAR(55) PRIMARY KEY,
  car_model                        VARCHAR(55) NOT NULL,
  color                            VARCHAR(28),
  car_category                     Car_Category,
  animal_transportation_applicable BOOLEAN,
  free_wifi                        BOOLEAN,
  air_conditioner                  BOOLEAN
);

CREATE TABLE IF NOT EXISTS Service_User
(
  user_id           SERIAL PRIMARY KEY,
  email             VARCHAR(254) UNIQUE NOT NULL,
  password          VARCHAR(28)         NOT NULL,
  phone             VARCHAR(28),
  sex               Sex,
  driver            BOOLEAN             NOT NULL DEFAULT FALSE,
  admin             BOOLEAN             NOT NULL DEFAULT FALSE,
  group_name        VARCHAR(250),
  car_number        VARCHAR(55) UNIQUE REFERENCES Car (car_number) ON DELETE RESTRICT,
  driver_license    VARCHAR(55),
  ignored_times     INT                 NOT NULL DEFAULT 0,
  activated         BOOLEAN             NOT NULL DEFAULT FALSE,
  registration_date TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Address
(
  name                  VARCHAR(120) NOT NULL,
  user_id               INT          NOT NULL REFERENCES Service_User (user_id) ON DELETE CASCADE,
  string_representation VARCHAR(250),
  location              POINT,
  PRIMARY KEY (name, user_id)
);

CREATE TABLE IF NOT EXISTS Taxi_Order
(
  tracking_number       SERIAL PRIMARY KEY,
  description           VARCHAR(250),
  status                Order_Status NOT NULL,
  price                 MONEY,
  user_id               INT REFERENCES Service_User (user_id) ON DELETE SET NULL,
  service               Service,
  car_category          Car_Category,
  way_of_payment        Way_Of_Payment,
  driver_sex            Sex,
  music_style           VARCHAR(50),
  animal_transportation BOOLEAN,
  free_wifi             BOOLEAN,
  smoking_driver        BOOLEAN,
  air_conditioner       BOOLEAN,
  comment               VARCHAR(400)
);

CREATE TABLE IF NOT EXISTS Taxi_Order_Item
(
  taxi_item_id   SERIAL PRIMARY KEY,
  tracking_numer INT REFERENCES Taxi_Order (tracking_number) ON DELETE CASCADE,
  path           PATH NOT NULL,
  -- need to be checked if this 'user_id' is a driver's id on code layer
  driver_id      INT REFERENCES Service_User (user_id) ON DELETE SET NULL
);
