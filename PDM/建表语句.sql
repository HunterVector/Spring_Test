drop table if exists file_storage;

/*==============================================================*/
/* Table: file_storage                                         */
/*==============================================================*/
create table file_storage
(
   file_id          int not null auto_increment comment '主键ID',
   file_state       CHAR comment'文件状态',
   file_path        varchar(500) comment'文件路径',
   primary key (file_id)
);

alter table file_storage comment '文件存储表';


drop table if exists file_info;

/*==============================================================*/
/* Table: file_info                                         */
/*==============================================================*/
create table file_info
(
   file_id          int not null ,
   file_state       CHAR comment'文件状态',
   file_name        varchar(500) comment'文件路径',
   primary key (file_id)
);

alter table file_info comment '文件信息表';


drop table if exists user_info;

/*==============================================================*/
/* Table: user_info                                         */
/*==============================================================*/
create table user_info
(
   user_id          int not null auto_increment comment '主键ID',
   user_state       CHAR comment'用户状态',
   user_name        varchar(200) comment'用户姓名',
   user_code        varchar(200) comment'用户登录code',
   user_pass        varchar(200) comment'用户密码',
   primary key (user_id)
);

alter table user_info comment '用户信息表';

drop table if exists user_file;

/*==============================================================*/
/* Table: user_file                                         */
/*==============================================================*/
create table user_file
(
   user_id          int not null ,
   file_id          int not null 
);

alter table user_file comment '用户文件列表';

commit;