# ----------------------------------------
# SQL script to create the tables for the 
# Virginia Tech Rides Database (VTRidesDB)
# Created by Quinton Miller on November 26, 2019
# ----------------------------------------


/*
Tables to be dropped must be listed in a logical order based on dependency.
UserQuestionnaire and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS UserRides, AllRides, UserPhoto, User;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    address1 VARCHAR(128) NOT NULL,
    address2 VARCHAR(128),
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    emergency_contact_first_name VARCHAR(128) NOT NULL,      
    emergency_contact_last_name VARCHAR(128) NOT NULL,       
    emergency_contact_email VARCHAR(128) NOT NULL,      
    emergency_contact_phone_number VARCHAR(128) NOT NULL,      
    emergency_contact_phone_carrier VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's photo. */
CREATE TABLE UserPhoto
(
   id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
   extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
   user_id INT UNSIGNED,
   FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);


/* The UserRides table contains attributes of all rides. */
CREATE TABLE AllRides
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    driver_id INT NOT NULL,
    passanger_1_id INT NOT NULL,
    passanger_2_id INT NOT NULL,
    passanger_3_id INT NOT NULL,
    passanger_4_id INT NOT NULL,
    passanger_5_id INT NOT NULL,
    passanger_6_id INT NOT NULL,
	seats_available INT NOT NULL,
	starting_location VARCHAR(128) NOT NULL, 
	ending_location VARCHAR(128) NOT NULL, 
	trip_date DATE NOT NULL,
	number_of_passangers INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE UserRides
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT UNSIGNED,
    driver_username int NOT NULL,
    passanger_1_id int NOT NULL,
    passanger_2_id int NOT NULL,
    passanger_3_id int NOT NULL,
    passanger_4_id int NOT NULL,
    passanger_5_id int NOT NULL,
    passanger_6_id int NOT NULL,
	seats_available INT NOT NULL,
	starting_location VARCHAR(128) NOT NULL, 
	ending_location VARCHAR(128) NOT NULL, 
	trip_date DATE NOT NULL,
	number_of_passangers INT NOT NULL,
	PRIMARY KEY (id)
);





