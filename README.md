# Users
Simple user management and authentication system.

**Building and Running the App:**

mvnw spring-boot:run

**Executing Test Cases:**

mvnw test

**MYSQL Query for creating the users table:**

CREATE TABLE `user` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `LAST_LOGIN` TIMESTAMP,
  PRIMARY KEY (`USER_ID`)
);