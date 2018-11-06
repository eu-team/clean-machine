/**
INSERT INTO facility(ID,address,name) VALUES (1,'Limerick','Limerick Facility')


INSERT INTO  machine(dtype,id) values ('Dryer', 1);


INSERT INTO  facility_machines(facility_id,machines_id) values (1,1);

INSERT  Into program(id,cost,duration,name) values (1,23.3,50,'Program1');

INSERT  Into program(id,cost,duration,name) values (2,15,50,'Program2');

INSERT  Into program(id,cost,duration,name) values (3,5.3,50,'Program3');

Insert Into machine_programs(machine_id, programs_id) VALUES (1,1);
Insert Into machine_programs(machine_id, programs_id) VALUES (1,2);
Insert Into machine_programs(machine_id, programs_id) VALUES (1,3);
*/