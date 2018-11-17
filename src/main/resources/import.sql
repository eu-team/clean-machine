INSERT INTO role (ID, role_name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO role (ID, role_name) VALUES (2, 'ROLE_MAINTAINER');
INSERT INTO role (ID, role_name) VALUES (3, 'ROLE_CUSTOMER');

insert into machine_state(id,dtype) values (100,'IdleState');
insert into machine (id,dtype,identifier,state_id) values (1,'WashingMachine','123ABC',100);

insert into program (id,dtype,cost,duration,name) values (1,'StandardProgram',5,0.5,'eco');
insert into program (id,dtype,cost,duration,name) values (2,'StandardProgram',6,1,'40Deg');
insert into program (id,dtype,cost,duration,name) values (3,'StandardProgram',7,1,'Wool');

insert  into machine_programs(machine_id, programs_id) values (1,1);
insert  into machine_programs(machine_id, programs_id) values (1,2);
insert  into machine_programs(machine_id, programs_id) values (1,3);

insert into auth_item(id,dtype,card_number) values (1,'NFCCard',1);

insert into users(id,dtype,email,password,username,name,role_id) values (1,'Customer','vince@email.be','$2a$10$4g7r7UA3UjlU08A4NabsG.Jt3rYTRWlHZDk3KcjPB.HybTML3aK4a','vince','vincent',3);

insert into users_auth_item_list(user_id,auth_item_list_id) values (1,1);

insert into account(id,balance,user_id) values (1,20,1);

INSERT INTO facility (id,address,name) VALUES (1, '8, facility street, Limerick', 'CleanMachine Original');

INSERT INTO facility_machines VALUES (1, 1);


