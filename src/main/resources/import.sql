INSERT INTO role (ID, role_name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO role (ID, role_name) VALUES (2, 'ROLE_MAINTAINER');
INSERT INTO role (ID, role_name) VALUES (3, 'ROLE_CUSTOMER');

INSERT INTO public.facility VALUES (100, '8, facility avenue, Limerick', 'Lavomatic');

INSERT INTO public.users VALUES ('Customer', 100, 'email@domain.com', 'Name', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'username', 3, NULL);
INSERT INTO public.users VALUES ('Administrator', 200, 'admin@domain.com', 'Admin', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'admin', 1, 100);
INSERT INTO "public"."account" VALUES (100, 0, 100);
INSERT INTO auth_item VALUES ('NFCCard', 100, 1234567890);
INSERT INTO users_auth_item_list VALUES (100, 100);

INSERT INTO machine VALUES ('WashingMachine', 100, 'ABC987654321');
INSERT INTO program VALUES (100, 2, 50, 'Cotton 60');
INSERT INTO machine_programs VALUES (100, 100);

INSERT INTO facility_machines VALUES (100, 100);
