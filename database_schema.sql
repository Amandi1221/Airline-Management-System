CREATE DATABASE IF NOT EXISTS airline_management;
USE airline_management;

-- Table for Admin Credentials
CREATE TABLE IF NOT EXISTS admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Table for Employee Credentials & Details
CREATE TABLE IF NOT EXISTS employee (
    username VARCHAR(50) PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    phone VARCHAR(20)
);

-- Table for Passenger Profiles
CREATE TABLE IF NOT EXISTS passenger (
    username VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age VARCHAR(10),
    dob VARCHAR(20),
    address VARCHAR(200),
    phone VARCHAR(20),
    email VARCHAR(100),
    nationality VARCHAR(50),
    gender VARCHAR(20),
    passport VARCHAR(50) UNIQUE NOT NULL
);

-- Table for Flights
CREATE TABLE IF NOT EXISTS flight (
    flight_code VARCHAR(50) PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    capacity INT DEFAULT 100,
    price DOUBLE NOT NULL,
    class_Name VARCHAR(50),
    flight_date VARCHAR(50) DEFAULT '2026-10-01',
    flight_time VARCHAR(50) DEFAULT '10:00:00'
);

-- Table for Booked Flights
CREATE TABLE IF NOT EXISTS bookflight (
    Ticket_Id VARCHAR(50) PRIMARY KEY,
    Source VARCHAR(100),
    Destination VARCHAR(100),
    Class VARCHAR(50),
    Price VARCHAR(50),
    Journey_Date VARCHAR(50),
    Username VARCHAR(50),
    Name VARCHAR(100),
    flight_code VARCHAR(50),
    Booking_Status VARCHAR(50) DEFAULT 'Booked',
    Cancel_Reason VARCHAR(255) DEFAULT 'None',
    FOREIGN KEY (Username) REFERENCES passenger(username) ON DELETE SET NULL,
    FOREIGN KEY (flight_code) REFERENCES flight(flight_code) ON DELETE SET NULL
);

-- Table for Booking (Legacy / Alias table if needed)
CREATE TABLE IF NOT EXISTS booking (
    Ticket_Id VARCHAR(50) PRIMARY KEY,
    Username VARCHAR(50),
    Status VARCHAR(50) DEFAULT 'Booked'
);

-- Table for Payments
CREATE TABLE IF NOT EXISTS payment (
    Ticket_Id VARCHAR(50) PRIMARY KEY,
    Username VARCHAR(50),
    Amount DOUBLE,
    Status VARCHAR(50) DEFAULT 'Paid',
    FOREIGN KEY (Ticket_Id) REFERENCES bookflight(Ticket_Id) ON DELETE CASCADE
);

-- Sample Data Insertion
INSERT IGNORE INTO admin (username, password) VALUES ('admin', 'admin123');
INSERT IGNORE INTO employee (username, employee_name, password, phone) VALUES ('emp1', 'John Doe', 'emp123', '0771234567');
INSERT IGNORE INTO flight (flight_code, source, destination, capacity, price, class_Name, flight_date, flight_time) VALUES ('UL-101', 'Colombo', 'London', 200, 750.00, 'Economy', '2026-10-01', '08:30:00');
INSERT IGNORE INTO flight (flight_code, source, destination, capacity, price, class_Name, flight_date, flight_time) VALUES ('UL-202', 'Colombo', 'Dubai', 150, 450.00, 'Business', '2026-10-05', '14:00:00');
INSERT IGNORE INTO passenger (username, name, age, dob, address, phone, email, nationality, gender, passport) VALUES ('user1', 'Kamal Perera', '30', '1996-05-12', '123 Main St, Kandy', '0719876543', 'kamal@gmail.com', 'Sri Lankan', 'Male', 'N1234567');
