SET FOREIGN_KEY_CHECKS = 0;
/* 
Create table */
CREATE TABLE IF NOT EXISTS EMPLOYEE
(Ssn numeric(9) NOT NULL,
Ename varchar(20),
Address VARCHAR(40),
Telephone numeric(10),
PRIMARY KEY (Ssn));

CREATE TABLE IF NOT EXISTS MANAGER
(m_uname varchar(15) NOT NULL,
is_admin boolean NOT NULL,
shift VARCHAR(15),
m_pword varchar(15) NOT NULL,
Essn numeric(10) NOT NULL,
PRIMARY KEY (m_uname),
FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn));

CREATE TABLE IF NOT EXISTS HOUSEKEEPER
(shift VARCHAR(15),
Essn numeric(10) NOT NULL,
Super_Ssn numeric(10) NOT NULL,
FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn),
FOREIGN KEY (Super_Ssn) REFERENCES EMPLOYEE(Ssn));

CREATE TABLE IF NOT EXISTS RECEPTIONIST
(r_uname varchar(15) NOT NULL,
shift VARCHAR(15),
r_pword varchar(15) NOT NULL,
Essn numeric(10) NOT NULL,
Super_Ssn numeric(10) NOT NULL,
PRIMARY KEY (r_uname),
FOREIGN KEY (Essn) REFERENCES EMPLOYEE(Ssn),
FOREIGN KEY (Super_Ssn) REFERENCES EMPLOYEE(Ssn));


CREATE TABLE IF NOT EXISTS ROOM
(r_num VARCHAR(4) NOT NULL,
m_Ssn NUMERIC(9) NOT NULL,
k_Ssn NUMERIC(9),
r_description VARCHAR(90) NOT NULL,
r_price FLOAT,
r_beds INT,
r_capacity INT,
r_name VARCHAR(100),
r_floor  INT NOT NULL,
is_available boolean,
PRIMARY KEY (r_num),
FOREIGN KEY (m_Ssn) REFERENCES EMPLOYEE(Ssn),
FOREIGN KEY (k_Ssn) REFERENCES EMPLOYEE(Ssn));

CREATE TABLE IF NOT EXISTS RESERVATION
(res_num NUMERIC(8) NOT NULL,
r_Ssn NUMERIC(9) NOT NULL,
checkin date,
checkout date,
r_num VARCHAR(4) NOT NULL,
guest_uname VARCHAR(15) NOT NULL,
PRIMARY KEY (res_num),
FOREIGN KEY (r_Ssn) REFERENCES EMPLOYEE(Ssn),
FOREIGN KEY (guest_uname) REFERENCES GUEST(uname));

CREATE TABLE IF NOT EXISTS BILL
(res_num NUMERIC(8) NOT NULL,
paid_on date,
payment_amount FLOAT,
cc_info NUMERIC(16),
guest_uname VARCHAR(15) NOT NULL,
FOREIGN KEY (res_num) REFERENCES RESERVATION(res_num),
FOREIGN KEY (guest_uname) REFERENCES GUEST(uname));

CREATE TABLE IF NOT EXISTS GUEST
(uname VARCHAR(15) NOT NULL,
g_name VARCHAR(15),
cc_info NUMERIC(16),
email VARCHAR(30) NOT NULL,
phone NUMERIC(10),
PRIMARY KEY (uname));

SET FOREIGN_KEY_CHECKS = 1;