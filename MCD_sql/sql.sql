
CREATE DATABASE IF NOT EXISTS formation
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE formation;

GRANT ALL PRIVILEGES ON formation.* TO 'abinet002'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE f_Adress(
   ad_id_adress INT NOT NULL AUTO_INCREMENT ,
   ad_street VARCHAR(50) NOT NULL,
   ad_city VARCHAR(50) NOT NULL,
   ad_postal_code VARCHAR(50) NOT NULL,
   PRIMARY KEY(ad_id_adress),
   UNIQUE(ad_street)
);

CREATE TABLE f_user(
   u_id_user INT NOT NULL AUTO_INCREMENT,
   u_login VARCHAR(50) NOT NULL,
   u_pasword VARCHAR(50) NOT NULL,
   u_type VARCHAR(50) NOT NULL,
   PRIMARY KEY(u_id_user),
   UNIQUE(u_login),
   UNIQUE(u_pasword)
);

CREATE TABLE f_course(
   co_id_course INT NOT NULL AUTO_INCREMENT,
   co_description VARCHAR(200),
   co_duration INT NOT NULL,
   co_presentiel BOOLEAN  NOT NULL,
   co_distanciel BOOLEAN  NOT NULL,
   co_price DECIMAL(15,2) NOT NULL,
   co_name VARCHAR(50) NOT NULL,
   PRIMARY KEY(co_id_course),
   UNIQUE(co_description),
   UNIQUE(co_name)
);

CREATE TABLE f_buyer(
   b_id_buyer INT NOT NULL AUTO_INCREMENT,
   b_clients VARCHAR(500),
   b_baskets VARCHAR(500),
   u_id_user INT NOT NULL,
   PRIMARY KEY(b_id_buyer),
   UNIQUE(u_id_user),
   FOREIGN KEY(u_id_user) REFERENCES f_user(u_id_user)
);

CREATE TABLE f_Admin(
   u_id_user INT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY(u_id_user),
   FOREIGN KEY(u_id_user) REFERENCES f_user(u_id_user)
);

CREATE TABLE f_basket(
   b_id_basket INT NOT NULL AUTO_INCREMENT,
   b_client_id INT,
   b_formation_id VARCHAR(50),
   b_id_buyer INT NOT NULL,
   PRIMARY KEY(b_id_basket, b_client_id),
   FOREIGN KEY(b_id_buyer) REFERENCES f_buyer(b_id_buyer)
);

CREATE TABLE f_Client(
   c_id_client INT NOT NULL AUTO_INCREMENT,
   c_first_name VARCHAR(50) NOT NULL,
   c_last_name VARCHAR(50) NOT NULL,
   c_email VARCHAR(50) NOT NULL,
   c_tel VARCHAR(50) NOT NULL,
   b_id_buyer INT NOT NULL,
   ad_id_adress INT NOT NULL,
   PRIMARY KEY(c_id_client),
   FOREIGN KEY(b_id_buyer) REFERENCES f_buyer(b_id_buyer),
   FOREIGN KEY(ad_id_adress) REFERENCES f_Adress(ad_id_adress)
);

CREATE TABLE f_contains(
   b_id_basket INT,
   b_client_id INT,
   co_id_course INT,
   PRIMARY KEY(b_id_basket, b_client_id, co_id_course),
   FOREIGN KEY(b_id_basket, b_client_id) REFERENCES f_basket(b_id_basket, b_client_id),
   FOREIGN KEY(co_id_course) REFERENCES f_course(co_id_course)
);

INSERT INTO f_user (u_id_user, u_login, u_pasword, u_type) VALUES
(1, "anaisbinet22@gmail.com", "tsuki", "admin"),
(2, "victorsueur30@gmail.com", "ariane", "buyer");

INSERT INTO f_Admin (u_id_user) VALUES
(1);

INSERT INTO f_Adress (ad_id_adress, ad_street, ad_city, ad_postal_code) VALUES
(1, "67 avenue maurice bourges maunoury", "Toulouse", "31200"),
(2, "69 avenue jean moulin", "Lesparre", "33340");

INSERT INTO f_buyer (b_id_buyer, b_clients, b_baskets, u_id_user) VALUES
(1,"1","1",2);

INSERT INTO f_Client (c_id_client, c_first_name, c_last_name, c_email, c_tel, b_id_buyer, ad_id_adress) VALUES
(1, "Christine", "Pertus", "christinepertus11@gmail.com", "0651454448", 1, 2);

INSERT INTO f_course (co_id_course, co_name, co_description, co_duration, co_presentiel, co_distanciel, co_price) VALUES
(1, "Python", "Programation orienté objet", 30, True, False, 60.30);

INSERT INTO f_basket (b_id_basket, b_formation_id, b_client_id, b_id_buyer) VALUES
(1, 1, 1, 1);

INSERT INTO f_contains (b_id_basket, b_client_id, co_id_course) VALUES
(1,1,1);

