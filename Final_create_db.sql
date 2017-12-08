DROP DATABASE IF EXISTS miata;

CREATE DATABASE miata;

USE miata;

-- ----------------------------
--  Table structure for `User`
-- ----------------------------

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(50) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Address_1` varchar(50) DEFAULT NULL,
  `Address_2` varchar(50) NOT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Postal_Code` int(11) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Security` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
);


-- ----------------------------
--  Table structure for `Product`
-- ----------------------------

CREATE TABLE `Product` (
  `ProductCode` int(11) NOT NULL DEFAULT '0',
  `Name` varchar(50) DEFAULT NULL,
  `CatelogCategory` varchar(50) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Price` float(11,0) DEFAULT NULL,
  `ImageURL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProductCode`)
);

-- ----------------------------
--  Table structure for `OrderItem`
-- ----------------------------

CREATE TABLE `OrderItem` (
 `OrderNumber` int(50) NOT NULL,
 `ProductCode` varchar(100) NOT NULL,
 `Quantity` varchar(100) NOT NULL,
 PRIMARY KEY (`OrderNumber`,`ProductCode`)
);


-- ----------------------------
--  Table structure for `Order`
-- ----------------------------

CREATE TABLE `Order` (
`OrderNumber` int(50) NOT NULL AUTO_INCREMENT,
 `Date` varchar(100) NOT NULL,
 `UserID` varchar(100) NOT NULL,
 `TaxRate` varchar(100) NOT NULL,
 `TotalCost` varchar(100) NOT NULL,
 `Paid` varchar(100) NOT NULL,
 PRIMARY KEY (`OrderNumber`)
);


-- ----------------------------
--  Table structure for `UserRole`
-- ----------------------------

CREATE TABLE `UserRole` (
  `Username` varchar(50),
  `Rolename` varchar(50) 
);

-- ----------------------------
--  Table structure for `UserPass`
-- ----------------------------

CREATE TABLE `UserPass` (
  `Username` varchar(50),
  `Password` varchar(50) 
);



-- ----------------------------
--  Insert Values
-- ----------------------------
INSERT INTO User (UserID, LastName, FirstName, Email, Address_1, Address_2, City, State, Postal_Code, Country, Password, Security) 
VALUES 
(-1, "Account", "Guest", "guest@email.com", "Miata.com", "", "Miata.com", "XX", 404, "Miata", "123", "Batman"),
(1, "Doe", "John", "johndoe@email.com", "9201 University City Blvd", "Bio Inf 112", "Charlotte", "NC", 28223, "USA", "123", "Batman"),
(2, "Doe", "Jane", "janedoe@email.com", "9201 University City Blvd", "Bio Inf 113", "<script>alert('test');</script>", "NC", 28223, "USA", "123", "Batman");


INSERT INTO Product (ProductCode, Name, CatelogCategory, Description, Price, ImageURL)
VALUES 
("1", "Strut Tower Brace", "externals", "Finally, a well-designed shock tower brace for the Miata. Now fits all NA and NB models!", 189.99, "./resources/strut.jpg"),
("2", "Windshield Cowl Panel", "externals", "Windshield Cowl Panel fits '90-'05. These often come with a small crack in the middle section, as shown in pictures, but this defect is purely cosmetic and does not impede function. It actually looks like a factory seem. ", 49.99, "./resources/cowl.jpg"),
("3", "Brake Lights", "externals", "3rd brake light located on trunk lid.", 15.99, "./resources/brake.jpg"),
("4", "Front Bumper Assembly", "externals", "Slightly worn original 1992 Mazda Miata body panel. Still looks new but with a few small dents and scratches", 13.0, "./resources/bumper.jpg"),
("5", "Soft Top", "internals", "NA Black Convertible Boot Cover fits '90-'97 Miata. In good condition. Sample Picture, actual pictures will be sent on request", 90.99, "./resources/top.jpg"),
("6", "Dash Trim Panel", "internals", "Set of A Pillar Trim pieces. Includes both left and right side.", 15.0, "./resources/dash.jpg"),
("7", "AC Blower", "internals", "Set of A Pillar Trim pieces. Includes both left and right side.", 23.0, "./resources/ac.jpg");


INSERT INTO UserRole (Username, Rolename)
VALUES ("joel", "programmer"), ("andrea", "server");

INSERT INTO UserPass (Username, Password)
VALUES ("joel", "sesame"), ("andrea", "sesame");

INSERT INTO miata.Order (OrderNumber, Date, UserID, TaxRate, TotalCost, Paid)
VALUES(42, "2017-11-19", "2", ".07", "123.45", "true");