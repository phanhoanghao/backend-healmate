CREATE TABLE `hos_rating` (
                              `HOS_RATING_ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `HOS_ID` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `AVG` double NOT NULL,
                              PRIMARY KEY (`HOS_RATING_ID`),
                              UNIQUE KEY `HOS_ID_UNIQUE` (`HOS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;