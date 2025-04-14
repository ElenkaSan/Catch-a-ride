drop database if exists catch_ride_db;
create database catch_ride_db;
use catch_ride_db;

CREATE TABLE location (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(10) NOT NULL,
    zip_code INT NOT NULL
);

 CREATE TABLE dealership (
    dealership_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `description` VARCHAR(500),
    location_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE `user` (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    date_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location_id INT NOT NULL,
    is_admin BOOLEAN,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE vehicle (
 vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
 make VARCHAR(50) NOT NULL,
 model VARCHAR(50) NOT NULL,
 year INT NOT NULL,
 color VARCHAR(20),
 trim VARCHAR(20),
 rent_rate DECIMAL(2),
 lease_rate DECIMAL(2),
 dealership_id INT NOT NULL,
 FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id)
 );

CREATE TABLE booking (
 booking_id INT PRIMARY KEY AUTO_INCREMENT,
 vehicle_id INT NOT NULL,
 user_id INT,
 dealership_location_id INT NOT NULL,
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 booking_type INT NOT NULL, -- 1 for leased, 2 for rented
 date_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
 FOREIGN KEY (user_id) REFERENCES `user`(user_id),
 FOREIGN KEY (dealership_location_id) REFERENCES location(location_id)
 );