drop database if exists catch_ride_db;
create database catch_ride_db;
use catch_ride_db;


create table app_user (
    app_user_id int primary key auto_increment,
    username varchar (50) not null unique,
    password_hash varchar (2048) not null,
    disabled boolean not null default (0)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar (50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);


CREATE TABLE location (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(10) NOT NULL,
    zip_code INT NOT NULL
);

 CREATE TABLE dealership (
    dealership_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(500),
    location_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE `user` (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    date_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location_id INT NOT NULL,
    app_user_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id),
    FOREIGN KEY (app_user_id) REFERENCES app_user(app_user_id)
);

CREATE TABLE vehicle (
 vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
 make VARCHAR(50) NOT NULL,
 model VARCHAR(50) NOT NULL,
 year INT NOT NULL,
 color VARCHAR(20),
 trim VARCHAR(20),
 rent_rate DECIMAL(8,2) NOT NULL,
 lease_rate DECIMAL(8,2) NOT NULL,
 dealership_id INT NOT NULL,
 booking_status BOOLEAN NOT NULL,
 image_url TEXT,
 FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id)
 );

CREATE TABLE booking (
 booking_id INT PRIMARY KEY AUTO_INCREMENT,
 vehicle_id INT NOT NULL,
 user_id INT,
 dealership_location_id INT NOT NULL,
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 booking_type VARCHAR(5) NOT NULL, -- 1 for leased, 2 for rented
 date_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 total_cost decimal,
 FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
 FOREIGN KEY (user_id) REFERENCES `user`(user_id),
 FOREIGN KEY (dealership_location_id) REFERENCES location(location_id)
 );
 
 insert into location(location_id, address, city, state, zip_code) values
	(1, '18 Washington Ave', 'Boston','MA', 20235),
	(2, '13 Saragota St', 'New York','Ny', 19235),
    (3, '123 Main St', 'Trenton', 'NJ', 08753),
    (4, '42 2nd Ave', 'Philadelphia', 'PA', 19019),
    (5, '1313 Deadend Dr', 'Bellevue', 'WA', 98004);


insert into dealership (dealership_id, `name`, `description`, location_id)
values
	(1, 'Autoloco', 'Here for all your vehicular needs', 1),
	(2, 'Chucklemotor', 'Serving people since 1980', 2),
    (3, 'Wheelie Good Deals', 'Well steer you in the right direction', 3),
    (4, 'Miles of Smiles Auto', 'Satisfaction guaranteed', 4),
    (5, 'The Car-nival', 'Wheel have you rolling over laughing', 5);
    
insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');
    

insert into app_user (app_user_id, username, password_hash, disabled)
values
    (1, 'admin', '$2a$12$bBSa76Ak4yzWXWH4H.jMhurg/qj.axe7L1.PbnEasegj8b.vMcFr6', false),
    (2, 'username', '$2a$12$SQ1hVzFCyqanbDZFrm8sFOeOmXDLHyIlBidVznOnBOZSUx..O1yPq', false);
    
    insert into app_user_role (app_user_id, app_role_id) 
values
	(1,2),
    (2,1);

insert into `user`
	(user_id, first_name, last_name, email, date_created_at, location_id, app_user_id)
values
	(1,'James','Sauven','jamessauven@gmail.com', '2017-12-20',1,1),
	(2,'Jack','Wilson','JackWilson@gmail.com', '2017-9-17',2,2);

insert into vehicle
	(vehicle_id, make, model, year, color, trim, rent_rate, lease_rate, dealership_id, booking_status)
values
	(1, 'Ford', 'Maverick', '2021', 'Blue', 'Sport', 60.00, 480.00, 1, true),
	(2, 'Honda', 'Si Base', '2023', 'Silver', 'SE', 50.00, 400.00, 2, true),
    (3, 'Toyota', 'Camri', '2024', 'Red', 'LE', 50.00, 400.00, 3, false),
    (4, 'Jeep', 'Recon', '2022', 'Yellow', 'Sport', 50.00, 400.00, 4, false),
    (5, 'Tesla', 'Model S', '2019', 'Silver', 'P85D', 50.00, 400.00, 5, false),
    (6, 'Honda', 'Pilot', '2015', 'Grey', 'TrailSport', 50.00, 400.00, 1, false),
    (7, 'Genesis', 'GV70', '2025', 'Grey', '2.5T', 50.00, 400.00, 2, false),
    (8, 'Porsche', '911 S/T', '2017', 'Blue', 'Carrera T', 50.00, 400.00, 3, false),
    (9, 'Toyota', 'Mirai', '2022', 'Blue', 'XLE', 50.00, 400.00, 4, false),
    (10, 'Ford', 'Edge', '2024', 'White', 'ST-Line', 50.00, 400.00, 5, false),
    (11, 'Mercedes Benz', 'GLA SUV', '2019', 'Silver', 'GLA 250', 50.00, 400.00, 1, false),
    (12, 'Ford', 'F-150', '2015', 'Red', 'XL', 50.00, 400.00, 2, false),
    (13, 'Mercedes Benz', 'E-Class Sedan', '2020', 'Black', 'E 350', 50.00, 400.00, 3, false),
    (14, 'Toyota', 'Prius', '2024', 'White', ' XLE', 50.00, 400.00, 4, false),
    (15, 'Lamborghini', 'Urus', '2018', 'Grey', 'Performante', 50.00, 400.00, 5, false),
    (16, 'Mercedes Benz', 'AMG GT', '2011', 'White', '55', 50.00, 400.00, 1, false),
    (17, 'Mini', 'Convertible', '2021', 'Green', 'Plus', 50.00, 400.00, 2, false),
    (18, 'Kia', 'Stinger', '2014', 'Green', 'GT-Line', 50.00, 400.00, 3, false),
    (19, 'Toyota', 'Corolla', '2016', 'White', 'LE', 50.00, 400.00, 4, false),
    (20, 'Lexus', 'RX', '2019', 'Orange', 'RX 350', 50.00, 400.00, 5, false),
    (21, 'BMW', '8', '2025', 'Blue', '840i', 50.00, 400.00, 1, false),
    (22, 'Jeep', 'Renegade', '2012', 'Orange', 'Latitude', 50.00, 400.00, 2, false),
    (23, 'Mini', 'Hardtop', '2024', 'White', 'Cooper S', 50.00, 400.00, 3, false),
    (24, 'Audi', 'A5', '2023', 'White', 'Prestige', 50.00, 400.00, 4, false),
    (25, 'Hyundai', 'Palisade', '2020', 'Blue', 'SEL Premium', 50.00, 400.00, 5, false);

insert into `booking`
	(booking_id, vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost)
values
	(1,1,1,1,'2025-04-14', '2025-04-20',"RENT",'2025-03-12',360),
	(2,1,2,1,'2025-04-14', '2026-04-20', "LEASE",'2025-01-11',4800),
    (3,1,1,1,'2025-06-10', '2026-06-20', "RENT",'2025-03-12',4800);