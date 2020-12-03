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

/*Table structure for table `administrator_info` */

DROP TABLE IF EXISTS `administrator_info`;

CREATE TABLE `administrator_info` (
  `manageId` int(11) NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  `sex` enum('男','女') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `avatar` longtext,
  PRIMARY KEY (`manageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator_info` */

insert  into `administrator_info`(`manageId`,`name`,`sex`,`birthday`,`phone`,`avatar`) values 
(1001,'Taylor','女','1989-12-13',17656561156,'https://wx4.sinaimg.cn/mw1024/006zlocZly1gjp1w4fxzyj30u00u6400.jpg');

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `class_name` varchar(15) DEFAULT NULL,
  `major` varchar(15) DEFAULT NULL,
  `total_person` tinyint(4) DEFAULT '0' COMMENT '总人数',
  `join_year` date DEFAULT '2018-09-10' COMMENT '入学年份',
  `counselor` varchar(10) DEFAULT NULL COMMENT '辅导员',
  `class_id` bigint(11) NOT NULL COMMENT '班级号',
  `headmaster` varchar(10) DEFAULT NULL COMMENT '班主任',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_info` */

insert  into `class_info`(`class_name`,`major`,`total_person`,`join_year`,`counselor`,`class_id`,`headmaster`) values 
('计科一班','计算机科学与技术',32,'2020-11-19','廖金果',5183002141,'杜君'),
('计科二班','计算机科学与技术',32,'2020-11-19','廖金果',5183002142,'杜君'),
('计科三班','计算机科学与技术',32,'2020-11-17','廖金果',5183002143,'姚东'),
('计科四班','计算机科学与技术',32,'2020-11-10','廖金果',5183002144,'姚东');

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
(12,7,5183002142,'丁洁','星期五,19:00-21:40','同大205(机房1)'),
(13,1,5183002141,'李骥啊','星期日,8:00-9:40','系办'),
(14,1,5183002141,'哈哈','星期日,8:00-9:40','系办'),
(15,1,5183002141,'李骥','星期日,8:00-9:40','系办'),
(16,1,5183002142,'李骥','星期日,8:00-9:40','系办'),
(17,1,5183002141,'李骥啊','星期日,8:00-9:40','系办'),
(18,2,5183002141,'郑艳君','星期一,16:00-17:40','生科楼114'),
(19,3,5183002141,'徐方','星期二,14:00-15:40','生科楼114'),
(20,4,5183002141,'焦家林','星期三,10:00-11:40','同大机房205'),
(21,1,5183002141,'哈哈','星期三,16:00-17:40','生科楼114');

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
(7,'数据库课程设计','12周',2),
(8,'体育课','12周',2),
(9,'体育课啊','12周',2),
(10,'体育课啊啊','12周',2),
(11,'数据库课程设计啊','12周',3),
(12,'体育课啊啊1','12周',2),
(13,'体育课啊啊2','12周',2),
(14,'体育课啊啊3','12周',2),
(15,'体育课啊啊34','12周',2),
(16,'体育课啊啊111','12周',11),
(112,'体育课','12周',2),
(113,'数1','12周',2),
(1111,'体育课111','12周',2),
(1122,'体育课啊啊22','12周',2),
(11221,'体育课222','12周',2);

/*Table structure for table `grade_info` */

DROP TABLE IF EXISTS `grade_info`;

CREATE TABLE `grade_info` (
  `sno` bigint(20) NOT NULL,
  `cno` int(11) NOT NULL,
  `grade` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`sno`,`cno`),
  KEY `grade_info_ibfk_2` (`cno`),
  CONSTRAINT `grade_info_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student_info` (`sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grade_info_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `course_info` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grade_info` */

insert  into `grade_info`(`sno`,`cno`,`grade`) values 
(518300214124,1,80),
(518300214124,2,80),
(518300214124,3,90),
(518300214201,1,90),
(518300214201,2,85),
(518300214201,3,80),
(518300214201,4,85),
(518300214202,1,93),
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
(518300214124,'老王','男','2020-12-17','计算机科学与技术','计科一班','恩施',17655555555,'应届生',5183002141),
(518300214201,'白旭东','男','2000-01-01','计算机科学与技术','计科二班','内蒙古',17655555555,'应届生',5183002142),
(518300214202,'曾妮','女','2000-04-01','计算机科学与技术','计科二班','不知道',17655555555,'应届生',5183002142),
(518300214203,'啊啊','女','2020-11-13','计算机科学与技术','计科二班','内蒙古',17655555555,'应届生',5183002142),
(518300214204,'范绪超','男','2000-04-01','计算机科学与技术','计科二班','湖北黄冈',17683838383,'应届生',5183002142);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(10) DEFAULT '123456',
  `type` char(6) DEFAULT '学生',
  `user_info_id` bigint(20) DEFAULT NULL COMMENT '用户详细信息识别id',
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_info_id` (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_login` */

insert  into `user_login`(`username`,`password`,`type`,`user_info_id`) values 
('518300214124','123456','学生',518300214124),
('518300214203','123456','学生',518300214203),
('admin','123456','管理员',1001),
('baixudong','123456','学生',518300214201),
('fanxuchao','123456','学生',518300214204),
('zengni','123456','学生',518300214202);

/* Trigger structure for table `student_info` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `add_student_user_login` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `add_student_user_login` AFTER INSERT ON `student_info` FOR EACH ROW BEGIN
	insert into user_login(username,user_info_id) value(new.sno,new.sno);
	
    END */$$


DELIMITER ;

/* Trigger structure for table `student_info` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `delete_student_user_login` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `delete_student_user_login` AFTER DELETE ON `student_info` FOR EACH ROW BEGIN
    /*禁用另一个触发器*/
    SET @enable_trigger2 = 0;
    if @enable_trigger = 1 then
	delete from user_login where user_info_id=old.sno and type='学生';
    end if;
    /*完成后恢复另一个触发器*/
    SET @enable_trigger2 = 1;
    END */$$


DELIMITER ;

/* Trigger structure for table `user_login` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `delete_student_info` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `delete_student_info` AFTER DELETE ON `user_login` FOR EACH ROW BEGIN
	/*禁用另一个触发器*/
	SET @enable_trigger = 0;
	
	IF @enable_trigger2 = 1 THEN 
	delete from student_info where sno=old.user_info_id and old.type='学生';
	END IF;
	
	/*完成后恢复另一个触发器*/
	SET @enable_trigger = 1; 
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
