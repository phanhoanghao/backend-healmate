INSERT INTO user (USER_ID, PHONE)  VALUES
                                       ('asdasdsa-6727-4229-a4ab-zxczxcxczxcc', '+84367209442'),
                                       ('xcvcvbvv-ba5d-4b92-85be-dfgdfgdfgdfg', '213456789'),
                                       ('rertertr-6727-4229-a4ab-erererererer', '313456789'),
                                       ('cvcvbcvb-ba5d-4b92-85be-fggfgtrytyty', '413456789'),
                                       ('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb', '513456789');

INSERT INTO role (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('9601409f-4691-4281-886e-8f8987763b56', 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO role (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('f4b194d0-238b-41b5-8f18-630e5fcf3d8e', 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');
INSERT INTO role (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES ('f4b194d1-238b-41b5-8f18-630e5fcf3d8e', 'DIRECTOR_USER', 'Director User - Has permission to perform admin tasks');

INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('asdasdsa-6727-4229-a4ab-zxczxcxczxcc','f4b194d0-238b-41b5-8f18-630e5fcf3d8e');
INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('xcvcvbvv-ba5d-4b92-85be-dfgdfgdfgdfg','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('rertertr-6727-4229-a4ab-erererererer','f4b194d0-238b-41b5-8f18-630e5fcf3d8e');
INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('cvcvbcvb-ba5d-4b92-85be-fggfgtrytyty','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb','9601409f-4691-4281-886e-8f8987763b56');
INSERT INTO user_roles(USER_ID, ROLE_ID) VALUES('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb','f4b194d1-238b-41b5-8f18-630e5fcf3d8e');