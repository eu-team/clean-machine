INSERT INTO role (ID, role_name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO role (ID, role_name) VALUES (2, 'ROLE_MAINTAINER');
INSERT INTO role (ID, role_name) VALUES (3, 'ROLE_CUSTOMER');

INSERT INTO public.users VALUES ('Customer', 1, 'email@domain.com', 'Name', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'username', 3, NULL);
INSERT INTO "public"."account" VALUES (2, 0, 1);
INSERT INTO auth_item VALUES ('NFCCard', 1, 1234567890);
INSERT INTO users_auth_item_list VALUES (1, 1);

INSERT INTO public.facility VALUES (1, '8, facility avenue, Limerick', 'Lavomatic');

INSERT INTO machine VALUES ('WashingMachine', 1);
INSERT INTO program VALUES (1, 2, 50, 'Cotton 60');
INSERT INTO machine_programs VALUES (1, 1);

INSERT INTO facility_machines VALUES (1, 1);
