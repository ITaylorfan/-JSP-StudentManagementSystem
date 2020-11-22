/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.5.62 : Database - jspstudentsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jspstudentsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jspstudentsystem`;

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `class_name` varchar(15) DEFAULT NULL,
  `major` varchar(15) DEFAULT NULL,
  `total_person` tinyint(4) DEFAULT NULL COMMENT '总人数',
  `join_year` date DEFAULT '2018-09-10' COMMENT '入学年份',
  `counselor` varchar(10) DEFAULT NULL COMMENT '辅导员',
  `class_id` bigint(11) NOT NULL COMMENT '班级号',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_info` */

insert  into `class_info`(`class_name`,`major`,`total_person`,`join_year`,`counselor`,`class_id`) values 
('计科二班','计算机科学与技术',32,'2018-09-10','廖金果',5183002142);

/*Table structure for table `course_chart` */

DROP TABLE IF EXISTS `course_chart`;

CREATE TABLE `course_chart` (
  `plan_id` int(11) NOT NULL COMMENT '排课id',
  `cno` int(11) DEFAULT NULL,
  `class_id` bigint(11) DEFAULT NULL COMMENT '班级号',
  `teacher` varchar(10) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL COMMENT '授课日期',
  `address` varchar(10) DEFAULT NULL COMMENT '地点',
  PRIMARY KEY (`plan_id`),
  KEY `class_id` (`class_id`),
  KEY `cno` (`cno`),
  CONSTRAINT `course_chart_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class_info` (`class_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `course_chart_ibfk_3` FOREIGN KEY (`cno`) REFERENCES `course_info` (`cno`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course_chart` */

insert  into `course_chart`(`plan_id`,`cno`,`class_id`,`teacher`,`date`,`address`) values 
(1,1,5183002142,'李骥','星期一,14:00-15:40','生科楼114'),
(2,2,5183002142,'郑艳君','星期一,16:00-17:40','生科楼114'),
(3,3,5183002142,'徐方','星期二,14:00-15:40','生科楼114'),
(4,1,5183002142,'李骥','星期二,16:00-17:40','生科楼114'),
(5,4,5183002142,'焦家林','星期三,10:00-11:40','同大205(机房1)'),
(6,3,5183002142,'徐方','星期三,14:00-15:40','同大205(机房1)'),
(7,5,5183002142,'彭杏芬','星期三,19:00-21:40','同大205(机房1)'),
(8,6,5183002142,'涂俊英','星期四,10:00-11:40','生科楼114'),
(9,4,5183002142,'焦家林','星期五,8:00-9:40','科技楼302'),
(10,6,5183002142,'涂俊英','星期五,10:00-11:40','同大205(机房1)'),
(11,2,5183002142,'郑艳君','星期五,14:00-15:40','同大205(机房1)'),
(12,7,5183002142,'丁洁','星期五,19:00-21:40','同大205(机房1)');

/*Table structure for table `course_info` */

DROP TABLE IF EXISTS `course_info`;

CREATE TABLE `course_info` (
  `cno` int(11) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `study_time` varchar(20) DEFAULT '16周',
  `credit` tinyint(4) DEFAULT '2',
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course_info` */

insert  into `course_info`(`cno`,`cname`,`study_time`,`credit`) values 
(1,'计算机组成原理','16周',2),
(2,'JSP/Servlet','16周',2),
(3,'计算机网络工程','8周',2),
(4,'Android开发基础','16周',2),
(5,'数据库应用技术','16周',2),
(6,'VC++程序设计','16周',2),
(7,'数据库课程设计','12周',2);

/*Table structure for table `grade_info` */

DROP TABLE IF EXISTS `grade_info`;

CREATE TABLE `grade_info` (
  `sno` bigint(20) NOT NULL,
  `cno` int(11) NOT NULL,
  `grade` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`sno`,`cno`),
  KEY `grade_info_ibfk_2` (`cno`),
  CONSTRAINT `grade_info_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `course_info` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_info_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student_info` (`sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grade_info` */

insert  into `grade_info`(`sno`,`cno`,`grade`) values 
(518300214204,1,95),
(518300214204,2,99),
(518300214204,3,88),
(518300214204,4,91),
(518300214204,5,89),
(518300214204,6,92),
(518300214204,7,93);

/*Table structure for table `student_info` */

DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `sno` bigint(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` enum('男','女') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `major` varchar(10) DEFAULT NULL COMMENT '专业',
  `class` varchar(10) DEFAULT NULL COMMENT '班级',
  `address` varchar(15) DEFAULT NULL COMMENT '地址',
  `phone` bigint(20) DEFAULT NULL COMMENT '手机号',
  `remark` varchar(20) DEFAULT '应届生' COMMENT '备注',
  `classId` bigint(20) DEFAULT NULL COMMENT '班号',
  PRIMARY KEY (`sno`),
  KEY `classId` (`classId`),
  CONSTRAINT `student_info_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `class_info` (`class_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_info` */

insert  into `student_info`(`sno`,`name`,`sex`,`birthday`,`major`,`class`,`address`,`phone`,`remark`,`classId`) values 
(518300214201,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'应届生',5183002142),
(518300214204,'范绪超','男','2000-04-01','计算机科学与技术','计科二班','湖北黄冈',17683838383,'应届生',5183002142);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  `type` char(6) DEFAULT '学生',
  `user_info_id` bigint(20) DEFAULT NULL COMMENT '用户详细信息识别id',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_login` */

insert  into `user_login`(`username`,`password`,`type`,`user_info_id`) values 
('admin','123456','管理员',1001),
('fanxuchao','111111','学生',518300214204);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
