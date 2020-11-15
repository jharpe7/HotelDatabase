SET FOREIGN_KEY_CHECKS = 0;
/*ADD EMPLOYEE DATA*/
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(222888333,'James Smith', '150 Bosley,Towson, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(856781345, 'Fred Johnson','350 Oak Wood, Baltimore, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(385712567,'Jayson Nelson','260 Towsontown,Towson, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(333444555,'Nicole Scott','4461 Glen St, Baltimore, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(222532767,'Alicia Surratt','2213 Grove St, Baltimore, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(234567890,'Jimmy Jackson','7800 York Road, Towson, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(555666777,'Davaughn Reid','3100 Fire House, Baltimore, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(123123123,'Mary McCaffrey','1234 Camberley Cir, Towson, MD');
INSERT INTO EMPLOYEE(SSN,ENAME,ADDRESS)
VALUES(777888999,'Tyler Niknam','305 Simple St, Baltimore, MD');
/*ADD MANAGER DATA*/

INSERT INTO MANAGER(M_UNAME,IS_ADMIN,SHIFT,M_PWORD,ESSN)
VALUES('MMcCaffrey',TRUE,1,'Pizza1234','123123123');
INSERT INTO MANAGER(M_UNAME,IS_ADMIN,SHIFT,M_PWORD,ESSN)
VALUES('JJackson',FALSE,2,'7800Pie','234567890');
INSERT INTO MANAGER(M_UNAME,IS_ADMIN,SHIFT,M_PWORD,ESSN)
VALUES('JSmith',TRUE,3,'567Burger','222888333');

/*ADD HOUSEKEEPER DATA*/
INSERT INTO HOUSEKEEPER(SHIFT,ESSN,SUPER_SSN)
VALUES(1, '385712567','123123123');
INSERT INTO HOUSEKEEPER(SHIFT,ESSN,SUPER_SSN)
VALUES(2, '222532767','234567890');
INSERT INTO HOUSEKEEPER(SHIFT,ESSN,SUPER_SSN)
VALUES(3, '555666777','222888333');


/*ADD RECEPTIONIST DATA*/
INSERT INTO RECEPTIONIST(R_UNAME,SHIFT,R_PWORD,ESSN,SUPER_SSN)
VALUES('NScott',1,'103Pasta','333444555','123123123');
INSERT INTO RECEPTIONIST(R_UNAME,SHIFT,R_PWORD,ESSN,SUPER_SSN)
VALUES('FJohnson',2,'210Sandwich','856781345','234567890');
INSERT INTO RECEPTIONIST(R_UNAME,SHIFT,R_PWORD,ESSN,SUPER_SSN)
VALUES('TNiknam',3,'455Biscuit','777888999','222888333');

/*ADD ROOM DATA*/
INSERT INTO ROOM(R_NUM,M_SSN,K_SSN,R_DESCRIPTION,R_PRICE,R_BEDS,R_CAPACITY,R_NAME,R_FLOOR,IS_AVAILABLE)
VALUES('222','123123123','385712567','Guest room, 2 Queen, City View','129','2','4','Guest Room','2',FALSE);
INSERT INTO ROOM(R_NUM,M_SSN,K_SSN,R_DESCRIPTION,R_PRICE,R_BEDS,R_CAPACITY,R_NAME,R_FLOOR,IS_AVAILABLE)
VALUES('401','234567890','222532767','Hospitality Suite, 1 Queen, 1 Pullout Couch, Half Kitchen, City View','450','1','6','Hospitality Suite','4',FALSE);
INSERT INTO ROOM(R_NUM,M_SSN,K_SSN,R_DESCRIPTION,R_PRICE,R_BEDS,R_CAPACITY,R_NAME,R_FLOOR,IS_AVAILABLE)
VALUES('508','222888333','555666777','Presidential Suite, 1 King, Full Kitchen, Bar, 2 Pullout couches, Meeting table, City View','1000','1','8','Presidential Suite','5',TRUE);

/*ADD RESERVATION DATA*/
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000000','333444555','2020-11-04','2020-11-08','401','TErvin');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000001','856781345','2020-11-01','2020-11-07','222','AJones');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000002','333444555','2020-12-20','2020-12-27','508','JMularz');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000003','777888999','2020-12-12','2020-12-16','222','PManning');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000004','333444555','2020-11-23','2020-11-28','222','KSmith');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000005','777888999','2020-11-30','2020-12-06','401','JPeppers');
INSERT INTO RESERVATION (RES_NUM,R_SSN,CHECKIN,CHECKOUT,R_NUM,GUEST_UNAME)
VALUES('10000006','856781345','2020-12-06','2020-12-12','401','KSowers');

/*ADD BILL DATA*/
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000000','2020-11-04','450','2121787893932222','TErvin');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000001','2020-11-01','129','1234567890123456','AJones');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000002','2020-12-20','1000','1111222233334444','JMularz');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000003','2020-12-12','129','5555666677778888','PManning');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000004','2020-11-23','129','9999000011112222','KSmith');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000005','2020-11-30','450','3333444455556666','JPeppers');
INSERT INTO BILL(RES_NUM,PAID_ON,PAYMENT_AMOUNT,CC_INFO,GUEST_UNAME)
VALUES('10000006','2020-12-06','450','1010212132324545','KSowers');

/*ADD GUEST DATA*/
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('TErvin','Tyler Ervin', '2121787893932222', 'TErvin@gmail.com','7727727727');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('AJones','Aaron Jones','1234567890123456', 'AJones@yahoo.com','8088088080');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('JMularz','Jamie Mularz','1111222233334444', 'JMularz@gmail.com','7037037030');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('PManning','Peyton Manning','5555666677778888', 'PManning@Verizon.net','2402402402');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('KSmith','Kathryn Smith','9999000011112222', 'KSmith@yahoo.com','4434434433');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('JPeppers','Julius Peppers', '3333444455556666', 'JPeppers@gmail.com','3013013010');
INSERT INTO GUEST(UNAME,G_NAME,CC_INFO,EMAIL,PHONE)
VALUES('KSowers','Katie Sowers','1010212132324545', 'KSowers@yahoo.com','2022022020');

SET FOREIGN_KEY_CHECKS = 1;