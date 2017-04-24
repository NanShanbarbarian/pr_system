
SET FOREIGN_KEY_CHECKS=0;

-------------------------
----------登录表
-------------------------
CREATE TABLE `login` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`number` varchar(30) NOT NULL,
	`password` varchar(20) NOT NULL,
	`user_id` int(11) NOT NULL,
	`role` int(2) NOT NULL,
	`status` int(2) NOT NULL,
	`createtime` datetime NOT NULL,
	`login_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------
-----------学生信息表
----------------------------
CREATE TABLE `student` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	`sex` int(2) NOT NULL,
	`experience` int(11) NOT NULL DEFAULT 0,
	`level` int(2) NOT NULL,
	`academy` varchar(20) NOT NULL,
	`major` varchar(10) NOT NULL,
	`grade` varchar(10) NOT NULL,
	`class` varchar(10) NOT NULL,
	`nation` varchar(20) NOT NULL,
	`polity` varchar(10) NOT NULL,
	`telephone` varchar(20) NOT NULL,
	`cornet` varchar(10),
	`qq` varchar(20),
	`email` varchar(50),
	`dormitory` varchar(20) NOT NULL,
	`description` varchar(300)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------
-------------教师及管理员信息表
-----------------------------
CREATE TABLE `teacher` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	`sex` int(2) NOT NULL,
	`academy` varchar(20) NOT NULL,
	`level` int(2) NOT NULL,
	`telephone` varchar(20) NOT NULL,
	`cornet` varchar(10),
	`qq` varchar(20),
	`email` varchar(50),
	`description` varchar(300)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------
--------------项目表
----------------------------
CREATE TABLE `Project` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	`num` int(2) NOT NULL,
	`period` varchar(20) NOT NULL,
	`keyword` varchar(30),
	`description` varchar(500),
	`requirement` varchar(500),
	`login_id` int(11) NOT NULL,
	`status` int(2) NOT NULL DEFAULT 0,
	`select_count` int(2) NOT NULL DEFAULT 0,
	`createtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------
------------------开发人员关联项目表
----------------------------------
CREATE TABLE `UserProjectRelation` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`porject_id` int(11) NOT NULL,
	`stu_login_id` int(11) NOT NULL,
	`tea_login_id` int(11) NOT NULL,
	`status` int(2) NOT NULL DEFAULT 1,
	`score` int(2) NOT NULL DEFAULT 0,
	`stu_remark` varchar(200),
	`tea_remark` varchar(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

------------------------------------
----------------------消息表
------------------------------------
CREATE TABLE `Message` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`project_id` int(11) NOT NULL,
	`sender_id` int(11) NOT NULL,
	`receiver_id` int(11) NOT NULL,
	`status` int(2) NOT NULL DEFAULT 0,
	`content` varchar(10000) NOT NULL,
	`createtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------
------------------------标签表
------------------------------------------
CREATE TABLE `Tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(10) NOT NULL,
	`num` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------
------------------开发人员标签表
----------------------------------
CREATE TABLE `StudentTagRelation` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`tag_id` int(11) NOT NULL,
	`login_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-------------------------------------
----------------------项目标签表
------------------------------------
CREATE TABLE `ProjectTagRelation` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`tag_id` int(11) NOT NULL,
	`project_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;























