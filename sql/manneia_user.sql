create database if not exists project_template;

use project_template;

create table if not exists manneia_user
(
    id            bigint       not null comment '主键用户唯一标识',
    user_account  varchar(255) not null comment '用户登录账号',
    user_password varchar(255) not null comment '用户登录密码',
    username      varchar(255) not null comment '用户昵称',
    user_avatar   varchar(255) comment '用户头像',
    user_profile  text comment '用户简介',
    age           int comment '年龄',
    gender        tinyint  default 0 comment '性别: 0-男 1-女',
    phone         varchar(120) comment '手机号',
    email         varchar(120) comment '邮箱',
    user_status   tinyint  default 0 comment '用户状态: 0-正常 1-禁用',
    user_role     tinyint  default 0 comment '用户角色: 0-普通用户 2-vip用户 3-管理员',
    create_time   datetime default current_timestamp comment '创建时间',
    update_time   datetime default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) comment '用户表';
create index idx_user_id on manneia_user (id);

create index idx_user_select on manneia_user (gender, user_status, user_role);