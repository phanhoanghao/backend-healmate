-- CREATE USER TABLE
CREATE TABLE IF NOT EXISTS HOS (
                                    HOS_ID VARCHAR(255) NOT NULL,
                                    HOS_NAME VARCHAR(255) NOT NULL,
                                    DIRECTOR VARCHAR(255) NOT NULL,
                                    PHONE VARCHAR(15) NOT NULL UNIQUE,
                                    ADDR VARCHAR(255),
                                    GEO VARCHAR(255),
                                    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (HOS_ID)
);

CREATE UNIQUE INDEX HOS_ID_INDEX ON HOS (HOS_ID);
-- CREATE ROLE TABLE
CREATE TABLE IF NOT EXISTS CHAIN (
                                    CHAIN_ID VARCHAR(255) NOT NULL,
                                    CHAIN_DESCRIPTION VARCHAR(255),
                                    CHAIN_NAME VARCHAR(255) NOT NULL UNIQUE,
                                    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (CHAIN_ID)
);
CREATE UNIQUE INDEX CHAIN_ID_INDEX ON CHAIN (CHAIN_ID);

-- CREATE USER-ROLES MAPPING TABLE
CREATE TABLE IF NOT EXISTS HOS_CHAINS (
                                          HOS_ID VARCHAR(255) NOT NULL,
                                          CHAIN_ID VARCHAR(255) NOT NULL,
                                          CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT HOS_FK FOREIGN KEY (HOS_ID) REFERENCES HOS (HOS_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                          CONSTRAINT CHAIN_FK FOREIGN KEY (CHAIN_ID) REFERENCES `CHAIN` (CHAIN_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE INDEX HOS_FK_INDEX ON HOS_CHAINS (HOS_ID);
CREATE INDEX CHAIN_FK_INDEX ON HOS_CHAINS (CHAIN_ID);