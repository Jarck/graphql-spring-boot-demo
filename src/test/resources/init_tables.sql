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
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
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
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `role` (`id`, `name`, `status`, `remark`)
VALUES
  (1, 'admin', 1, '系统管理员'),
  (2, 'test', 1, '测试');

CREATE TABLE `user_role` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
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
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `permission` (`id`, `name`, `permission`, `resource_type`, `available`)
VALUES
  (1, '读取用户', 'user:read', 'read', 1),
  (2, '创建用户', 'user:create', 'create', 1),
  (3, '编辑用户', 'user:edit', 'edit', 1),
  (4, '删除用户', 'user:delete', 'delete', 1),

  (5, '读取角色', 'role:read', 'read', 1),
  (6, '创建角色', 'role:create', 'create', 1),
  (7, '编辑角色', 'role:edit', 'edit', 1),
  (8, '删除角色', 'role:delete', 'delete', 1),

  (9, '读取权限', 'permission:read', 'read', 1),
  (10, '创建权限', 'permission:create', 'create', 1),
  (11, '编辑权限', 'permission:edit', 'edit', 1),
  (12, '删除权限', 'permission:delete', 'delete', 1),

  (13, '读取城市', 'city:read', 'read', 1),
  (14, '创建城市', 'city:create', 'create', 1),
  (15, '编辑城市', 'city:edit', 'edit', 1),
  (16, '删除城市', 'city:delete', 'delete', 1),

  (17, '读取公司', 'company:read', 'read', 1),
  (18, '创建公司', 'company:create', 'create', 1),
  (19, '编辑公司', 'company:edit', 'edit', 1),
  (20, '删除公司', 'company:delete', 'delete', 1);

CREATE TABLE `role_permission` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) NOT NULL,
  `permission_id` INT(11) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 1, 3),
  (4, 1, 4),
  (5, 2, 1);

CREATE TABLE `city` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '城市名称',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
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
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `company` (`id`, `name`, `short_name`, `city_id`)
VALUES
  (1, '杭州xxx有限公司', '杭州xxx', 1),
  (2, '苏州xxx有限公司', '苏州xxx', 2);
