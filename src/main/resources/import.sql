INSERT INTO role (ID, role_name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO role (ID, role_name) VALUES (2, 'ROLE_MAINTAINER');
INSERT INTO role (ID, role_name) VALUES (3, 'ROLE_CUSTOMER');

insert into machine_state(id,dtype,name) values (100,'IdleState','Idle');
insert into machine (identifier,dtype,state_id) values ('1','WashingMachine',100);

insert into program (id,dtype,cost,duration,name) values (100,'StandardProgram',5,0.5,'eco');
insert into program (id,dtype,cost,duration,name) values (200,'StandardProgram',6,1,'40Deg');
insert into program (id,dtype,cost,duration,name) values (300,'StandardProgram',7,1,'Wool');
insert  into machine_programs(machine_identifier, programs_id) values ('1',100);
insert  into machine_programs(machine_identifier, programs_id) values ('1',200);
insert  into machine_programs(machine_identifier, programs_id) values ('1',300);

INSERT INTO facility (id,address,name) VALUES (100, '8, facility street, Limerick', 'CleanMachine Original');

INSERT INTO facility_machines (machines_identifier,facility_id)VALUES ('1', 100);

INSERT INTO public.users VALUES ('Customer', 100, 'email@domain.com', 'Name', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'username', 3, NULL);
INSERT INTO public.users VALUES ('Administrator', 200, 'admin@domain.com', 'Admin', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'admin', 1, 100);
INSERT INTO public.users VALUES ('Maintainer', 300, 'maintainer@domain.com', 'Maintainer', '$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a', 'maintainer', 2, 100);
insert into public.users(id,dtype,email,password,username,name,role_id) values (400,'Customer','vince@email.be','$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a','vince','vincent',3);

INSERT INTO "public"."account" VALUES (100, 0, 100);

insert into auth_item(id,dtype,card_number) values (100,'NFCCard',1);
INSERT INTO auth_item VALUES ('NFCCard', 200, 1234567890);

insert into users_auth_item_list(user_id,auth_item_list_id) values (100,100);

insert into account(id,balance,user_id) values (200,20,100);


insert into subscription_plan (id,name,price,subscription_periodicity) VALUES (100, '10 per month', 10.0, 'MONTHLY');
insert into facility_subscription_plans (facility_id, subscription_plans_id)  VALUES (100, 100);

