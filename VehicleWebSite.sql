CREATE TABLE VehicleBrand (
	`vehicle_brand_id` INTEGER AUTO_INCREMENT CHECK (`vehicle_brand_id` >= 0),
	`vehicle_brand_name` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`vehicle_brand_id`) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO VehicleBrand VALUES
	(1, 'A'),
	(2, 'B'),
	(3, 'C')
;

CREATE TABLE VehicleType (
	`vehicle_type_id` VARCHAR(45),
	`vehicle_brand_id` INTEGER NOT NULL,
	`vehicle_speed` SMALLINT NOT NULL CHECK (`vehicle_speed` >= 0),
	`sale_status` TINYINT NOT NULL CHECK (`sales_status` IN (0, 1, 2)),
	PRIMARY KEY (`vehicle_type_id`),
	FOREIGN KEY (`vehicle_brand_id`) REFERENCES VehicleBrand(`vehicle_brand_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO VehicleType VALUES
	('A123-1', 1, 220, 1),
	('B324-2', 2, 230, 1),
	('A123-9', 1, 220, 1),
	('C8-2', 3, 300, 1),
	('C0-0', 3, 400, 0)
;

CREATE INDEX idx_brand_speed_salestatus ON 
	VehicleType(`vehicle_brand_id`, `vehicle_speed`, `sale_status`);


CREATE TABLE VehicleColour (
	`vehicle_type_id` VARCHAR(45),
	`vehicle_colour` VARCHAR(45),
	PRIMARY KEY (`vehicle_type_id`, `vehicle_colour`),
	FOREIGN KEY (`vehicle_type_id`) REFERENCES VehicleType(`vehicle_type_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO VehicleColour VALUES
	('A123-1', 'white'),
	('A123-1', 'black'),
	('B324-2', 'silver'),
	('A123-9', 'dark blue'),
	('C8-2', 'red'),
	('C0-0', 'sky blue')
;

CREATE TABLE Shop (
	`shop_id` VARCHAR(45),
	PRIMARY KEY (`shop_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO Shop VALUES
	('S-A-1'),
	('S-B-1'),
	('S-B-2'),
	('S-C-1')
;

CREATE TABLE Manufacturer (
	`manufacturer_id` VARCHAR(45),
	PRIMARY KEY (`manufacturer_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO Manufacturer VALUES
	('M-A-1'),
	('M-B-1'),
	('M-C-1')
;

CREATE TABLE WebUser (
	`user_id` VARCHAR(45),
	`phone` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO WebUser VALUES
	('aaa', '123456789'),
	('bbb', '987654321')
;

CREATE TABLE VehicleEvaluation (
	`evaluation_id` INTEGER AUTO_INCREMENT,
	`vehicle_type_id` VARCHAR(45) NOT NULL,
	`user_id` VARCHAR(45) NOT NULL,
	`shop_id` VARCHAR(45) NOT NULL,
	`evaluation_date` TIME NOT NULL,
	`evaluation_content` TEXT NOT NULL,
	`score` REAL NOT NULL CHECK (`score` >= 0),
	PRIMARY KEY (`evaluation_id`),
	FOREIGN KEY (`vehicle_type_id`) REFERENCES VehicleType(`vehicle_type_id`),
	FOREIGN KEY (`user_id`) REFERENCES WebUser(`user_id`),
	FOREIGN KEY (`shop_id`) REFERENCES Shop(`shop_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX idx_vehicle_type ON VehicleEvaluation(`vehicle_type_id`);

CREATE INDEX idx_user ON VehicleEvaluation(`user_id`);


CREATE TABLE Works (
	`works_id` VARCHAR(45),
	`user_id` VARCHAR(45) NOT NULL,
	`works_type` TINYINT NOT NULL CHECK (`works_type` IN (0, 1, 2)),
	`works_content` TEXT NOT NULL,
	PRIMARY KEY (`works_id`),
	FOREIGN KEY (`user_id`) REFERENCES WebUser(`user_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX idx_user ON Works(`user_id`);

CREATE INDEX idx_works_type ON Works(`works_type`);

CREATE TABLE WorksReply (
	`reply_id` VARCHAR(45),
	`user_id` VARCHAR(45) NOT NULL,
	`works_id` VARCHAR(45) NOT NULL,
	`reply_content` TEXT NOT NULL,
	PRIMARY KEY (`reply_id`),
	FOREIGN KEY (`user_id`) REFERENCES WebUser(`user_id`),
	FOREIGN KEY (`works_id`) REFERENCES Works(`works_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX idx_user ON WorksReply(`user_id`);


CREATE TABLE Indent (
	`indent_id` VARCHAR(45),
	`user_id` VARCHAR(45) NOT NULL,
	`shop_id` VARCHAR(45) NOT NULL,
	`vehicle_type_id` VARCHAR(45) NOT NULL,
	`indent_date` TIME NOT NULL,
	PRIMARY KEY (`indent_id`),
	FOREIGN KEY (`user_id`) REFERENCES WebUser(`user_id`),
	FOREIGN KEY (`shop_id`) REFERENCES Shop(`shop_id`),
	FOREIGN KEY (`vehicle_type_id`) REFERENCES VehicleType(`vehicle_type_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE INDEX idx_user ON Indent(`user_id`);

CREATE INDEX idx_shop ON Indent(`shop_id`);


CREATE TABLE VehicleBrandManufacture (
	`manufacturer_id` VARCHAR(45),
	`vehicle_brand_id` INTEGER,
	PRIMARY KEY (`manufacturer_id`, `vehicle_brand_id`),
	FOREIGN KEY (`manufacturer_id`) REFERENCES Manufacturer(`manufacturer_id`),
	FOREIGN KEY (`vehicle_brand_id`) REFERENCES VehicleBrand(`vehicle_brand_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE VehicleTypeManufacture (
	`manufacturer_id` VARCHAR(45),
	`vehicle_type_id` VARCHAR(45),
	`quantity` INTEGER NOT NULL CHECK (`quantity` >= 0),
	PRIMARY KEY (`manufacturer_id`, `vehicle_type_id`),
	FOREIGN KEY (`manufacturer_id`) REFERENCES Manufacturer(`manufacturer_id`),
	FOREIGN KEY (`vehicle_type_id`) REFERENCES VehicleType(`vehicle_type_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE VehicleTypeShop (
	`shop_id` VARCHAR(45),
	`vehicle_type_id` VARCHAR(45),
	`vehicle_price` REAL NOT NULL CHECK (`vehicle_price` >= 0),
	PRIMARY KEY (`shop_id`, `vehicle_type_id`),
	FOREIGN KEY (`shop_id`) REFERENCES Shop(`shop_id`),
	FOREIGN KEY (`vehicle_type_id`) REFERENCES VehicleType(`vehicle_type_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX idx_price ON VehicleTypeShop(`vehicle_price`);

CREATE TABLE UserFriend (
	`user_idx` VARCHAR(45),
	`user_idy` VARCHAR(45),
	PRIMARY KEY (`user_idx`, `user_idy`),
	FOREIGN KEY (`user_idx`) REFERENCES WebUser(`user_id`),
	FOREIGN KEY (`user_idy`) REFERENCES WebUser(`user_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE WorksAboutVehicleBrand (
	`works_id` VARCHAR(45),
	`vehicle_brand_id` INTEGER,
	PRIMARY KEY (`works_id`, `vehicle_brand_id`),
	FOREIGN KEY (`works_id`) REFERENCES Works(`works_id`),
	FOREIGN KEY (`vehicle_brand_id`) REFERENCES VehicleBrand(`vehicle_brand_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE VIEW AllIndentsofAllUsers AS SELECT * FROM WebUser NATURAL JOIN Indent;

CREATE VIEW AllEvaluationsofAllVehicleTypes AS SELECT * FROM VehicleType NATURAL JOIN VehicleEvaluation;

CREATE VIEW RepliesofAllWorks AS SELECT * FROM Works NATURAL JOIN WorksReply;

CREATE VIEW VehicleTypeAVGScore AS SELECT DISTINCT `vehicle_type_id`, AVG(`score`) `avg_score` FROM VehicleType NATURAL join VehicleEvaluation GROUP BY vehicle_type_id ;

