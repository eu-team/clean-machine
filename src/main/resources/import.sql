INSERT INTO role (ID, role_name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO role (ID, role_name) VALUES (2, 'ROLE_MAINTAINER');
INSERT INTO role (ID, role_name) VALUES (3, 'ROLE_CUSTOMER');


insert into machine (id,dtype) values (1,'WashingMachine');


insert into program (id,dtype,cost,duration,name) values (1,'StandardProgram',50,50,'eco');
insert into program (id,dtype,cost,duration,name) values (2,'StandardProgram',60,50,'40Deg');
insert into program (id,dtype,cost,duration,name) values (3,'StandardProgram',70,50,'Wool');



insert  into machine_programs(machine_id, programs_id) values (1,1);
insert  into machine_programs(machine_id, programs_id) values (1,2);
insert  into machine_programs(machine_id, programs_id) values (1,3);


insert into auth_item(id,dtype,card_number) values (1,'NFCCard',1);

insert into users(id,dtype,email,password,username,name,role_id) values (1,'Customer','vince@email.be','zefzfzef','vince','vincent',3);

insert into users_auth_item_list(user_id,auth_item_list_id) values (1,1);

insert into account(id,balance,user_id) values (1,20,1);

