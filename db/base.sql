CREATE TABLE `tb_user` (
  `id` varchar(36) PRIMARY KEY,
  `account` varchar(255) unique,
  `password` varchar(255),
  `salt` varchar(64),
  `status` int,
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_user_info` (
  `id` varchar(36) PRIMARY KEY,
  `user_id` varchar(36),
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
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_user_role` (
  `user_id` varchar(36),
  `role_id` varchar(36)
);

CREATE TABLE `tb_role` (
  `id` varchar(36) PRIMARY KEY,
  `name` varchar(128),
  `description` varchar(255),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_organization` (
  `id` varchar(36) PRIMARY KEY,
  `prent_id` int,
  `prent_ids` varchar(255),
  `name` varchar(128),
  `type` varchar(64),
  `description` varchar(255),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
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
  `id` varchar(36) PRIMARY KEY,
  `menu_id` int,
  `permission` varchar(255),
  `description` varchar(255),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_menu` (
  `id` varchar(36) PRIMARY KEY,
  `parent_id` int,
  `name` varchar(255),
  `description` varchar(255),
  `url` varchar(255),
  `order_by` int,
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_country` (
  `id` varchar(36) PRIMARY KEY,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_state` (
  `id` varchar(36) PRIMARY KEY,
  `country_id` int,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_city` (
  `id` varchar(36) PRIMARY KEY,
  `state_id` int,
  `name` varchar(255),
  `code` varchar(32),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint
);

CREATE TABLE `tb_sys_code` (
  `id` varchar(36) PRIMARY KEY,
  `parent_id` int,
  `code` varchar(64),
  `type` varchar(64),
  `description` varchar(255),
  `version` int,
  `create_user` varchar(36),
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` varchar(36),
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
