-- CREATE REVIEW TABLE
CREATE TABLE `review` (
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
