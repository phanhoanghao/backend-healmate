INSERT INTO `USER` (USER_ID, PHONE)  VALUES
                                       ('asdasdsa-6727-4229-a4ab-zxczxcxczxcc', '+84367209442'),
                                       ('xcvcvbvv-ba5d-4b92-85be-dfgdfgdfgdfg', '213456789'),
                                       ('rertertr-6727-4229-a4ab-erererererer', '313456789'),
                                       ('cvcvbcvb-ba5d-4b92-85be-fggfgtrytyty', '413456789'),
                                       ('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb', '513456789');

INSERT INTO `ROLE` (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('9601409f-4691-4281-886e-8f8987763b56', 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO `ROLE` (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('f4b194d0-238b-41b5-8f18-630e5fcf3d8e', 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');
INSERT INTO `ROLE` (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('f4b194d1-238b-41b5-8f18-630e5fcf3d8e', 'DIRECTOR_USER', 'Director User - Has permission to perform admin tasks');

INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('asdasdsa-6727-4229-a4ab-zxczxcxczxcc','f4b194d0-238b-41b5-8f18-630e5fcf3d8e');
INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('xcvcvbvv-ba5d-4b92-85be-dfgdfgdfgdfg','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('rertertr-6727-4229-a4ab-erererererer','f4b194d0-238b-41b5-8f18-630e5fcf3d8e');
INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('cvcvbcvb-ba5d-4b92-85be-fggfgtrytyty','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO `USER_ROLES` (USER_ID, ROLE_ID) VALUES('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb','f4b194d1-238b-41b5-8f18-630e5fcf3d8e');

INSERT INTO `CHAIN` (CHAIN_ID, CHAIN_NAME, CHAIN_DESCRIPTION, CREATED_AT, UPDATED_AT) VALUES ('d87f79b2-a496-4799-90dc-938271218def', 'NO_CHAIN', 'No Chain - hospital not chain right', now(), now());
INSERT INTO `CHAIN` (CHAIN_ID, CHAIN_NAME, CHAIN_DESCRIPTION, CREATED_AT, UPDATED_AT) VALUES ('6a76eedc-cfba-491a-a49e-b1f7f08c7018', 'GANGNAM', 'Gangnam chain - All hospital in Gangnam', now(), now());