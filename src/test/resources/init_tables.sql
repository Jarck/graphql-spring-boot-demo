DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `permission`;
DROP TABLE IF EXISTS `role_permission`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `company`;

CREATE TABLE `user` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL COMMENT '用户名',
  `phone` VARCHAR(50) NOT NULL COMMENT '手机号码',
  `status` INT(11) DEFAULT '1' COMMENT '状态 1: 可用; 0: 不可用',
  `city_id` INT(11) DEFAULT NULL COMMENT '城市id',
  `company_id` INT(11) DEFAULT NULL COMMENT '公司id',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `name`, `phone`, `city_id`, `company_id`, `status`)
VALUES
  (1, 'admin', '18812345671', 1, 1, 1),
  (2, 'user', '18812345672', 2, 2, 1);

CREATE TABLE `role` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '角色名称(英文)' UNIQUE,
  `status` INT(11) DEFAULT 1 COMMENT '状态 1: 可用; 0: 不可用',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '角色说明' UNIQUE,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `role` (`id`, `name`, `status`, `remark`)
VALUES
  (1, 'admin', 1, '管理员'),
  (2, 'manager', 1, '客户经理'),
  (3, 'driver', 1, '司机');

CREATE TABLE `user_role` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `user_role` (`id`, `user_id`, `role_id`)
VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 2, 1);

CREATE TABLE `permission` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '权限名称',
  `permission` VARCHAR(200) NOT NULL COMMENT '权限名称 例如: user:read' UNIQUE,
  `resource_type` VARCHAR(20) NOT NULL COMMENT '权限类别',
  `available` INT(11) DEFAULT 1 COMMENT '状态 1: 可用; 0: 不可用',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `permission` (`id`, `name`, `permission`, `resource_type`, `available`)
VALUES
  (1, '读取用户', 'user:read', 'read', 1),
  (2, '注册用户', 'user:create', 'create', 1),
  (3, '编辑用户', 'user:edit', 'edit', 1);

CREATE TABLE `role_permission` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) NOT NULL,
  `permission_id` INT(11) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 1, 3),
  (4, 2, 1);

CREATE TABLE `city` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '城市名称',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `city` (`id`, `name`)
VALUES
  (1, '杭州'),
  (2, '苏州');

CREATE TABLE `company` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL COMMENT '公司名称',
  `short_name` VARCHAR(50) NOT NULL COMMENT '公司简称',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '公司地址',
  `link_name` VARCHAR(50) DEFAULT NULL COMMENT '公司联系人',
  `phone` VARCHAR(50) DEFAULT NULL COMMENT '联系人手机号码',
  `city_id` INT(11) DEFAULT NULL COMMENT '城市id',
  `status` INT(11) DEFAULT 1 COMMENT '状态 1: 可用; 0: 不可用',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `company` (`id`, `name`, `short_name`, `city_id`)
VALUES
  (1, '杭州小车东有限公司', '杭州小车东', 1),
  (2, '苏州小车东有限公司', '苏州小车东', 2);
