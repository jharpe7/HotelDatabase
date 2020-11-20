use hoteldbfinal;
/* 
Create table */

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS EMPLOYEE
(fName varchar(20) NOT NULL,
lName varchar(20) NOT NULL,
ssn char(9) NOT NULL,
address varchar(40),
telephone numeric(10),
e_username varchar(20) NOT NULL,
e_password varchar(20) NOT NULL,
e_type varchar(15) NOT NULL,
shift int NOT NULL,
super_ssn char(9),
UNIQUE (e_username),
PRIMARY KEY(ssn),
FOREIGN KEY (super_ssn) REFERENCES EMPLOYEE(ssn));


CREATE TABLE IF NOT EXISTS ROOM
(r_num numeric(3) NOT NULL,
c_Ssn char(9) NOT NULL,
r_description VARCHAR(90) NOT NULL,
r_price int,
r_beds INT,
r_capacity INT,
r_name VARCHAR(100),
r_floor  INT NOT NULL,
PRIMARY KEY (r_num),
FOREIGN KEY (c_Ssn) REFERENCES EMPLOYEE(ssn));

CREATE TABLE IF NOT EXISTS RESERVATION
(res_num NUMERIC(8) NOT NULL,
r_ssn char(9) NOT NULL,
checkin_date date,
checkout_date date,
r_num numeric(3) NOT NULL,
guest_username VARCHAR(20) NOT NULL,
checked_in int,
checked_out int,
PRIMARY KEY (res_num),
FOREIGN KEY (r_ssn) REFERENCES EMPLOYEE(ssn),
FOREIGN KEY (guest_username) REFERENCES GUEST(g_username));

CREATE TABLE IF NOT EXISTS BILL
(res_num NUMERIC(8) NOT NULL,
paid_on date,
payment_amount FLOAT,
cc_info NUMERIC(16),
guest_username VARCHAR(20) NOT NULL,
FOREIGN KEY (res_num) REFERENCES RESERVATION(res_num),
FOREIGN KEY (guest_username) REFERENCES GUEST(g_username));

CREATE TABLE IF NOT EXISTS GUEST
(fName VARCHAR(20) NOT NULL,
lName VARCHAR(20) NOT NULL,
email VARCHAR(30),
phone NUMERIC(10) NOT NULL,
cc_info NUMERIC(16) NOT NULL,
g_username VARCHAR(20) NOT NULL,
g_password VARCHAR(20) NOT NULL,
PRIMARY KEY (g_username));