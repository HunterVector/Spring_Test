
TRUNCATE file_storage;

insert into file_storage(file_state, file_path) values('A', 'D:\\logs\\file\\Upload\\testDownLoad1.txt');
insert into file_storage(file_state, file_path) values('A', 'D:\\logs\\file\\Upload\\testDownLoad2.txt');


TRUNCATE file_info;
insert into file_info(file_id, file_state, file_name) values(1, 'A', 'testDownLoad1.txt');
insert into file_info(file_id, file_state, file_name) values(2, 'A', 'testDownLoad2.txt');
insert into file_info(file_id, file_state, file_name) values(3, 'A', 'errorFile.txt');

TRUNCATE user_info;
insert into user_info(user_state, user_name, user_code,user_pass) values('A', 'user1', 'admin1', 'Admin11');

insert into user_info(user_state, user_name, user_code,user_pass) values('A', 'user2', 'admin2', 'Admin22');

insert into user_info(user_state, user_name, user_code,user_pass) values('X', 'user3', 'admin3', 'Admin33');

TRUNCATE user_file;
insert into user_file(user_id, file_id) values(1, 1);
insert into user_file(user_id, file_id) values(2, 2);

commit;