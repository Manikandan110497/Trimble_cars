# Trimble_cars

MYSQL TABLE---

CREATE DATABASE trimble_cars;


CREATE TABLE trimble_cars.users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('OWNER', 'CUSTOMER', 'ADMIN') NOT NULL
);


CREATE TABLE trimble_cars.cars (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);


CREATE TABLE trimble_cars.leases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lease_start DATETIME NOT NULL,
    lease_end DATETIME DEFAULT NULL,
    car_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE
);


select * from trimble_cars.users;
select * from trimble_cars.cars;
ALTER TABLE trimble_cars.users MODIFY COLUMN role VARCHAR(20);

INSERT INTO trimble_cars.users (username, password, role) VALUES 
('admin', '$2a$10$D9QkD.3f1JsJ/NdtDAE2u.WO7oA8L0xdDyyOGCO9G6w2/oGFiJb1K', 'ROLE_ADMIN'),
('owner', '$2a$10$D9QkD.3f1JsJ/NdtDAE2u.WO7oA8L0xdDyyOGCO9G6w2/oGFiJb1K', 'ROLE_OWNER'),
('customer', '$2a$10$D9QkD.3f1JsJ/NdtDAE2u.WO7oA8L0xdDyyOGCO9G6w2/oGFiJb1K', 'ROLE_CUSTOMER');
