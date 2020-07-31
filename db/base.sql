CREATE TABLE `tb_user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `account` varchar(255),
  `password` varchar(255),
  `salt` varchar(64),
  `status` int,
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_user_info` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `f_name` varchar(255),
  `l_name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `birthday` timestamp,
  `id_num` varchar(255),
  `country_code` varchar(32),
  `city_code` varchar(32),
  `address` varchar(128),
  `postcode` int(32),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_user_role` (
  `user_id` int,
  `role_id` int
);

CREATE TABLE `tb_role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(128),
  `description` varchar(255),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_organization` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `prent_id` int,
  `prent_ids` varchar(255),
  `name` varchar(128),
  `type` varchar(64),
  `description` varchar(255),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_org_permission` (
  `org_id` int,
  `permission_id` int
);

CREATE TABLE `tb_role_permission` (
  `role_id` int,
  `permission_id` int
);

CREATE TABLE `tb_permission` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `menu_id` int,
  `permission` varchar(255),
  `description` varchar(255),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_menu` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `parent_id` int,
  `name` varchar(255),
  `description` varchar(255),
  `url` varchar(255),
  `order_by` int,
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_country` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_state` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `country_id` int,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_city` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `state_id` int,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_sys_code` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `parent_id` int,
  `code` varchar(64),
  `type` varchar(64),
  `description` varchar(255),
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

ALTER TABLE `tb_user_info` ADD FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`);

ALTER TABLE `tb_user_role` ADD FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`);

ALTER TABLE `tb_user_role` ADD FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

ALTER TABLE `tb_organization` ADD FOREIGN KEY (`prent_id`) REFERENCES `tb_organization` (`id`);

ALTER TABLE `tb_org_permission` ADD FOREIGN KEY (`org_id`) REFERENCES `tb_organization` (`id`);

ALTER TABLE `tb_org_permission` ADD FOREIGN KEY (`permission_id`) REFERENCES `tb_permission` (`id`);

ALTER TABLE `tb_role_permission` ADD FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

ALTER TABLE `tb_role_permission` ADD FOREIGN KEY (`permission_id`) REFERENCES `tb_permission` (`id`);

ALTER TABLE `tb_permission` ADD FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`id`);

ALTER TABLE `tb_menu` ADD FOREIGN KEY (`parent_id`) REFERENCES `tb_menu` (`id`);

ALTER TABLE `tb_state` ADD FOREIGN KEY (`country_id`) REFERENCES `tb_country` (`id`);

ALTER TABLE `tb_city` ADD FOREIGN KEY (`state_id`) REFERENCES `tb_state` (`id`);

ALTER TABLE `tb_sys_code` ADD FOREIGN KEY (`parent_id`) REFERENCES `tb_sys_code` (`id`);
