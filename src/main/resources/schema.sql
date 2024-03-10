-- delete database
-- DROP DATABASE IF EXISTS healmate;

-- CREATE USER TABLE
CREATE TABLE IF NOT EXISTS USER (
                                    USER_ID VARCHAR(255) NOT NULL,
                                    PHONE VARCHAR(15) NOT NULL UNIQUE ,
                                    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (USER_ID)
);

-- CREATE ROLE TABLE
CREATE TABLE IF NOT EXISTS ROLE (
                                    ROLE_ID VARCHAR(255) NOT NULL,
                                    ROLE_DESCRIPTION VARCHAR(255),
                                    ROLE_NAME VARCHAR(255) NOT NULL UNIQUE,
                                    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (ROLE_ID)
);


-- CREATE USER-ROLES MAPPING TABLE
CREATE TABLE IF NOT EXISTS `healmate`.`USER_ROLES` (
                                          USER_ID VARCHAR(255) NOT NULL,
                                          ROLE_ID VARCHAR(255) NOT NULL,
                                          CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                          CONSTRAINT ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);



-- CREATE USER TABLE
CREATE TABLE IF NOT EXISTS `HOS` (
                                   HOS_ID VARCHAR(255) NOT NULL,
                                   HOS_NAME VARCHAR(255) NOT NULL,
                                   DIRECTOR VARCHAR(255) NOT NULL,
                                   PHONE VARCHAR(15) NOT NULL UNIQUE,
                                   ADDR VARCHAR(255),
                                   GEO VARCHAR(255),
                                   CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (HOS_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- CREATE ROLE TABLE
CREATE TABLE IF NOT EXISTS `CHAIN` (
                                     CHAIN_ID VARCHAR(255) NOT NULL,
                                     CHAIN_DESCRIPTION VARCHAR(255),
                                     CHAIN_NAME VARCHAR(255) NOT NULL UNIQUE,
                                     CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     PRIMARY KEY (CHAIN_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- CREATE USER-ROLES MAPPING TABLE
CREATE TABLE IF NOT EXISTS `HOS_CHAINS` (
                                          HOS_ID VARCHAR(255) NOT NULL,
                                          CHAIN_ID VARCHAR(255) NOT NULL,
                                          CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT HOS_FK FOREIGN KEY (HOS_ID) REFERENCES HOS (HOS_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                          CONSTRAINT CHAIN_FK FOREIGN KEY (CHAIN_ID) REFERENCES CHAIN (CHAIN_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- CREATE REVIEW TABLE
CREATE TABLE IF NOT EXISTS `REVIEW` (
                          `REV_ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `PHONE` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `HOS_ID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `CONTENT` mediumtext COLLATE utf8mb4_unicode_ci,
                          `RATING` smallint NOT NULL,
                          `IMAGES` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`REV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE HOS_RATING TABLE
CREATE TABLE IF NOT EXISTS `HOS_RATING` (
                              `HOS_RATING_ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `HOS_ID` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `AVG` double NOT NULL,
                              PRIMARY KEY (`HOS_RATING_ID`),
                              UNIQUE KEY `HOS_ID_UNIQUE` (`HOS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
