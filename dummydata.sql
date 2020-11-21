SET FOREIGN_KEY_CHECKS = 0;

/*ADD EMPLOYEE DATA*/
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('John','Smith',111222333,'407 Baker Ln, Towson, MD',3012235757,'JSmith', 'password123','Manager', 1, 111222333);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Sarah','Brooks',878656434,'123 Main St, Baltimore, MD',2405661233,'SBrooks', 'password456','Manager', 2, 111222333);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Michael','Roberts',555444333,'222 Flower Ave, Towson, MD',4439786634,'MRoberts', 'password789','Manager', 3, 111222333);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Steve','Hendricks',666777888,'43 Green Ct, Baltimore , MD',3011116212,'SHendricks', 'apple1','Receptionist', 1, 878656454);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Amy','Rose',321321321,'300 Lake St, Towson, MD',2401235555,'ARose', 'street4','Receptionist', 2, 878656454);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Madison','Fields',100200300,'209 West Ct, Baltimore, MD',4436003200,'MFields', 'bird0','Receptionist', 3, 878656454);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Evan','Powers',501602703,'21 Bush Ln, Towson, MD',3011013333,'EPowers', 'football5','Cleaner', 1, 555444333);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Whitney','Ruggs',233200166,'3 Blue View, Baltimore, MD',3014557070,'WRuggs', 'blue7','Cleaner', 2, 555444333);
INSERT INTO EMPLOYEE(fName,lName,ssn,address,telephone,e_username,e_password,e_type, shift,super_ssn)
VALUES('Haley','Roberts',999888777,'44 Mailbox Rd, Baltimore, MD',2405110008,'HRoberts', 'one1','Cleaner', 3, 555444333);


/*ADD ROOM DATA*/
INSERT INTO ROOM(r_num, c_Ssn, r_description, r_price, r_beds, r_capacity, r_name, r_floor,r_available)
VALUES(201, 501602703, 'Two twin beds, mini-fridge, flatscreen TV', 150, 2, 4, 'Value Suite', 2,true);
INSERT INTO ROOM(r_num, c_Ssn, r_description, r_price, r_beds, r_capacity, r_name, r_floor,r_available)
VALUES(304, 233200166, 'One queen bed, full kitchen, complimentary bar', 300, 1, 3, 'Deluxe Suite', 3, true);
INSERT INTO ROOM(r_num, c_Ssn, r_description, r_price, r_beds, r_capacity, r_name, r_floor,r_available)
VALUES(508, 999888777, 'One king bed, flatscreen TV, complimentary room service', 500, 1, 2, 'Honeymoon Suite', 5,true);


/*ADD RESERVATION DATA*/
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME,checked_in, checked_out)
VALUES(0,'ARose','2020-11-04','2020-11-08','201','TErvin', false, false);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(1,'SBrooks','2020-11-01','2020-11-10','304','RDunn', false, false);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(2,'SHendricks','2020-12-20','2020-12-27','508','BHolt', null, null);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(3,'ARose','2020-11-17','2020-11-18','201','LSimpson', false, false);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(4,'SHendricks','2020-11-23','2020-11-28','304','MFork', null, null);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(5,'SBrooks','2020-12-04','2020-12-04','508','TErvin', null, null);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(6,'ARose','2020-12-06','2020-12-12','201','BHolt', null, null);
INSERT INTO RESERVATION (RES_NUM,R_USERNAME,CHECKIN_DATE,CHECKOUT_DATE,R_NUM,GUEST_USERNAME, CHECKED_IN, CHECKED_OUT)
VALUES(19,'SHendricks','2020-12-06','2020-12-12','508','BHolt', null, null);

/*ADD BILL DATA*/
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(0,'2020-11-04',150,1111111111111111,'TErvin');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(1,'2020-11-01',300,2222222222222222,'RDunn');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(2,'2020-12-20',500,3333333333333333,'BHolt');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(3,'2020-11-017',150,4444444444444444,'LSimpson');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(4,'2020-11-23',300,5555555555555555,'MFork');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(5,'2020-12-04',500,1111111111111111,'TErvin');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_USERNAME)
VALUES(6,'2020-12-06',150,3333333333333333,'BHolt');

/*ADD GUEST DATA*/
INSERT INTO GUEST(fName,lName,email,phone,cc_info,g_username,g_password)
VALUES('Tyler', 'Ervin', 'tervin1@gmail.com', 3016561777, 1111111111111111, 'TErvin', 'easy0');
INSERT INTO GUEST(fName,lName,email,phone,cc_info,g_username,g_password)
VALUES('Rachel', 'Dunn', 'rachel777@gmail.com', 8972331001, 2222222222222222, 'RDunn', 'blank1');
INSERT INTO GUEST(fName,lName,email,phone,cc_info,g_username,g_password)
VALUES('Brian', 'Holt', 'holt1@yahoo.com', 8085441234, 3333333333333333, 'BHolt', 'cheese2');
INSERT INTO GUEST(fName,lName,email,phone,cc_info,g_username,g_password)
VALUES('Lisa', 'Simpson', 'saxophone@gmail.com', 1455664044, 4444444444444444, 'LSimpson', 'dog4');
INSERT INTO GUEST(fName,lName,email,phone,cc_info,g_username,g_password)
VALUES('Matthew', 'Fork', 'fork12@gmail.com', 3032221665, 5555555555555555, 'MFork', 'wheel8');

SET FOREIGN_KEY_CHECKS = 1;