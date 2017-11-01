-- ----------------------------
--  Table structure for `User`
-- ----------------------------

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(50) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Address 1` varchar(50) DEFAULT NULL,
  `Address 2` varchar(50) NOT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Postal Code` int(11) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) 


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
)	


