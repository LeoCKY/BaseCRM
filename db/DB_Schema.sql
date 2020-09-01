-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user`
(
    `id`           char(36) PRIMARY KEY,
    `account`     varchar(64) unique comment '登入帳號',
    `password`     varchar(255),
    `salt`         varchar(64) comment '加密(鹽)',
    `email`        varchar(255) NOT NULL,
    `status`       tinyint    default 0 comment '狀態',
    `website_type` tinyint    default 0 comment '前/後台',
    `version`      int        default 0 comment '版號',
    `create_user`  varchar(36) comment '建立人',
    `create_date`  timestamp comment '建立時間',
    `create_ip`    varchar(64) comment '建立IP',
    `update_user`  varchar(36) comment '更新人',
    `update_date`  timestamp comment '更新時間',
    `update_ip`    varchar(64) comment '更新IP',
    `is_del`       tinyint(2) default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 管理員 - Admin 123456
insert into `tb_sys_user` value ('acfc0e9232f54732a5d9ffe9071bf572', 'admin', 'e0b141de1c8091be350d3fc80de66528', '',
                                 'tbdsyu9527@gmail.com', 1, 1, 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1',
                                 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

DROP TABLE IF EXISTS `tb_sys_user_info`;

CREATE TABLE `tb_sys_user_info`
(
    `id`          char(36) PRIMARY KEY,
    `user_id`     varchar(36)  NOT NULL,
    `f_name`      varchar(255) NOT NULL,
    `l_name`      varchar(255) NOT NULL,
    `phone`       varchar(255),
    `birthday`    timestamp,
    `id_num`      varchar(255),
    `country_id`  varchar(32),
    `states_id`   varchar(32),
    `city_id`     varchar(32),
    `address`     varchar(128),
    `postcode`    int(32),
    `version`     int        default 0 comment '版號',
    `create_user` varchar(36) comment '建立人',
    `create_date` timestamp comment '建立時間',
    `create_ip`   varchar(64) comment '建立IP',
    `update_user` varchar(36) comment '更新人',
    `update_date` timestamp comment '更新時間',
    `update_ip`   varchar(64) comment '更新IP',
    `is_del`      tinyint(2) default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 管理員資料明細
insert into `tb_sys_user_info` value ('acfc0e9232f54732a5d9ffe9071bf572', 'e0b141de1c8091be350d3fc80de66528', 'Leo',
                                      'Chen', '0987654321', NOW(), 'H123456789', null, null, null, '火星路26號', '00001'
, 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role`
(
    `id`          char(36) PRIMARY KEY,
    `name`        varchar(128) NOT NULL,
    `description` varchar(255),
    `version`     int        default 0 comment '版號',
    `create_user` varchar(36) comment '建立人',
    `create_date` timestamp comment '建立時間',
    `create_ip`   varchar(64) comment '建立IP',
    `update_user` varchar(36) comment '更新人',
    `update_date` timestamp comment '更新時間',
    `update_ip`   varchar(64) comment '更新IP',
    `is_del`      tinyint(2) default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 角色:管理員
insert into `tb_sys_role` value ('2619a672e53811e7b983201a068c6482', 'admin', '系統管理員'
    , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

DROP TABLE IF EXISTS `tb_sys_role_user`;
CREATE TABLE `tb_sys_role_user`
(
    `user_id` varchar(36) NOT NULL,
    `role_id` varchar(36) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `tb_sys_user` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into tb_sys_role_user value ('acfc0e9232f54732a5d9ffe9071bf572','2619a672e53811e7b983201a068c6482');

DROP TABLE IF EXISTS `tb_sys_organization`;

CREATE TABLE `tb_sys_organization`
(
    `id`          char(36) PRIMARY KEY,
    `prent_id`    int,
    `prent_ids`   varchar(255),
    `name`        varchar(128) NOT NULL,
    `type`        int        default 0,
    `description` varchar(255),
    `version`     int        default 0 comment '版號',
    `create_user` varchar(36) comment '建立人',
    `create_date` timestamp comment '建立時間',
    `create_ip`   varchar(64) comment '建立IP',
    `update_user` varchar(36) comment '更新人',
    `update_date` timestamp comment '更新時間',
    `update_ip`   varchar(64) comment '更新IP',
    `is_del`      tinyint(2) default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for tb_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;

CREATE TABLE `tb_sys_menu`
(
    `id`          char(36)     NOT NULL PRIMARY KEY,
    `name`        varchar(255) NOT NULL,
    `parent_id`   varchar(36)           DEFAULT NULL,
    `url`         varchar(255)          DEFAULT NULL,
    `order_num`   int(4)                DEFAULT NULL COMMENT '排序字段',
    `icon`        varchar(255)          DEFAULT NULL COMMENT '圖標',
    `permission`  varchar(255)          DEFAULT NULL COMMENT '權限',
    `menu_type`   tinyint(4)   NOT NULL DEFAULT '0' COMMENT '1欄目 2菜单',
    `version`     int                   default 0 comment '版號',
    `create_user` varchar(36) comment '建立人',
    `create_date` timestamp comment '建立時間',
    `create_ip`   varchar(64) comment '建立IP',
    `update_user` varchar(36) comment '更新人',
    `update_date` timestamp comment '更新時間',
    `update_ip`   varchar(64) comment '更新IP',
    `is_del`      tinyint(2)            default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `tb_sys_menu` VALUES ('cfda8029dfb311e7b555201a068c6482', '系统管理', null, null, '1', '&#xe614;',null ,0
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);
INSERT INTO `tb_sys_menu`
VALUES ('3873ccc2dfda11e7b555201a068c6482', '菜单管理', 'cfda8029dfb311e7b555201a068c6482', 'menu/showMenu', '1', '&#xe62a',  'menu:show', 0
       , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('18bf8d5df09511e78a57201a068c6482', '新增', '3873ccc2dfda11e7b555201a068c6482', null, '1', null,'nemu:add', 1
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('8a6c8bfa7f804eac810c5790cad9a62a', '删除', '3873ccc2dfda11e7b555201a068c6482', null, '2', null, 'menu:del', 1
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('cfe54921dfb311e7b555201a068c6482', '用户管理', 'cfda8029dfb311e7b555201a068c6482', '/user/showUser', '2', '&#xe6af;', 'user:show', 0
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('cfe54921dfb311e7b555201a068c6483', '增加', 'cfe54921dfb311e7b555201a068c6482', null, '1', null, 'user:select', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('433089a6eb0111e782d5201a068c6482', '编辑', 'cfe54921dfb311e7b555201a068c6482', '', null, '1', 'user:update', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('e3b11497eb9e11e7928d201a068c6482', '删除', 'cfe54921dfb311e7b555201a068c6482', '', null, '', 'user:del', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('f23f6a6bf09511e78a57201a068c6482', '修改密码', 'cfe54921dfb311e7b555201a068c6482', null, '4', '', 'user:repass', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('cff61424dfb311e7b555201a068c6482', '角色管理', 'cfda8029dfb311e7b555201a068c6482', '/role/showRole', '3', '&#xe613;', 'role:show', '0'
, 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('2b56410cf09411e78a57201a068c6482', '新增', 'cff61424dfb311e7b555201a068c6482', null, '1', '', 'role:add', '1'
                              , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('ff015ea5f09411e78a57201a068c6482', '编辑', 'cff61424dfb311e7b555201a068c6482', null, '2', '','role:update', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('0e6c8d4cf09511e78a57201a068c6482', '删除', 'cff61424dfb311e7b555201a068c6482', null, '3', '','role:del', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

INSERT INTO `tb_sys_menu` VALUES ('88b8e5d1f38911e7aca0201a068c6482', '查看', 'cff61424dfb311e7b555201a068c6482', null, '4', '','role:select', '1'
                                 , 0, 'acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1','acfc0e9232f54732a5d9ffe9071bf572', NOW(), '127.0.0.1', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role_menu`;
CREATE TABLE `tb_sys_role_menu` (
                                 `role_id` varchar(32) NOT NULL,
                                 `menu_id` varchar(32) NOT NULL,
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '0e6c8d4cf09511e78a57201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '18bf8d5df09511e78a57201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '28661300f9d411e7a009201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '2b56410cf09411e78a57201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '3873ccc2dfda11e7b555201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '433089a6eb0111e782d5201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '4d603831fe9b11e7b472201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '5ae3d4e9f38e11e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '6315968bf37111e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '63da4415fc6211e7a781201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '6931fd22f09611e78a57201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '69f3f59cf38e11e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '6dc13c6eec5f11e7a472201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '788d8e34f38e11e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '7967e098ee0611e7a60d201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '873f30b0f38e11e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', '88b8e5d1f38911e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'a1ca6642ec5e11e7a472201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'b441914cee0811e7a60d201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'b7839f59fe8811e7b472201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfda8029dfb311e7b555201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfe54921dfb311e7b555201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cfe54921dfb311e7b555201a068c6483');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'cff61424dfb311e7b555201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'e06da471f90311e780aa201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'e3b11497eb9e11e7928d201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'e9a13e55f35911e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'ecda560cf36f11e7aca0201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'f23f6a6bf09511e78a57201a068c6482');
INSERT INTO `tb_sys_role_menu` VALUES ('2619a672e53811e7b983201a068c6482', 'ff015ea5f09411e78a57201a068c6482');


DROP TABLE IF EXISTS `tb_sys_code`;

CREATE TABLE `tb_sys_code`
(
    `id`          char(36) PRIMARY KEY,
    `parent_id`   varchar(36),
    `code`        varchar(64),
    `type`        varchar(64),
    `description` varchar(255),
    `version`     int        default 0 comment '版號',
    `create_user` varchar(36) comment '建立人',
    `create_date` timestamp comment '建立時間',
    `create_ip`   varchar(64) comment '建立IP',
    `update_user` varchar(36) comment '更新人',
    `update_date` timestamp comment '更新時間',
    `update_ip`   varchar(64) comment '更新IP',
    `is_del`      tinyint(2) default 0 comment '0:未刪除;1:刪除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `tb_countries`;

create table tb_countries
(
    id        mediumint unsigned,
    name      varchar(100) not null,
    iso3      char(3)      null,
    iso2      char(2)      null,
    phone_code varchar(255) null,
    capital   varchar(255) null,
    currency  varchar(255) null,
    native    varchar(255) null,
    emoji     varchar(191) null,
    emojiU    varchar(191) null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `tb_states`;

create table tb_states
(
    id           mediumint unsigned not null,
    name         varchar(255)       not null,
    country_id   mediumint unsigned not null,
    country_code char(2)            not null,
    fips_code    varchar(255)       null,
    iso2         varchar(255)       null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `tb_cities`;

create table tb_cities
(
    id           mediumint unsigned not null,
    name         varchar(255)       not null,
    state_id     mediumint unsigned not null,
    state_code   varchar(255)       not null,
    country_id   mediumint unsigned not null,
    country_code char(2)            not null,
    latitude     decimal(10, 8)     not null,
    longitude    decimal(11, 8)     not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;