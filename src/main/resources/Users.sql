CREATE TABLE `user` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `LAST_LOGIN` TIMESTAMP,
  PRIMARY KEY (`USER_ID`)
);

INSERT INTO USER (NAME,PASSWORD, EMAIL, LAST_LOGIN)
VALUES ('David','123@','devid.neo@genesys.com',null);
INSERT INTO USER (NAME,PASSWORD, EMAIL, LAST_LOGIN)
VALUES ('John Smith','125@','john.smith@genesys.com',null);
INSERT INTO USER (NAME,PASSWORD, EMAIL, LAST_LOGIN)
VALUES ('Raja','555@','raja@genesys.com',null);