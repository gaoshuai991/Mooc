CREATE DATABASE mooc CHARACTER SET utf8;
USE mooc;
CREATE TABLE admin(
  aid        INT PRIMARY KEY AUTO_INCREMENT,
  username      VARCHAR(50) NOT NULL ,
  password       VARCHAR(50) NOT NULL ,
  authority  INT
);

CREATE TABLE user(
  userid      INT PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(30) NOT NULL ,
  password    VARCHAR(50) NOT NULL ,
  realname    VARCHAR(50) NOT NULL ,
  sex         CHAR(2) ,
  hobbys      VARCHAR(50),
  birthday    DATETIME,
  city        VARCHAR(30),
  email       VARCHAR(30),
  qq          VARCHAR(20),
  createtime  DATETIME,
  state       INT
);

CREATE TABLE theme(
  theid       INT PRIMARY KEY AUTO_INCREMENT,
  thename     VARCHAR(30) NOT NULL ,
  count       INT DEFAULT 0
);

CREATE TABLE message(
  msgid       INT PRIMARY KEY AUTO_INCREMENT,
  userid      INT NOT NULL,
  msgtopic    VARCHAR(200) NOT NULL ,
  msgcontents VARCHAR(5000) NOT NULL ,
  msgtime     DATETIME NOT NULL ,
  msgip       VARCHAR(30),
  theid       INT,
  state       INT DEFAULT 1,
  CONSTRAINT fk_muid FOREIGN KEY (userid) REFERENCES user(userid),
  CONSTRAINT fk_mtid FOREIGN KEY (theid) REFERENCES theme(theid)
);

CREATE TABLE `count`(
  msgid       INT PRIMARY KEY ,
  accesscount INT,
  replycount  INT,
  CONSTRAINT fk_cmid FOREIGN KEY (msgid) REFERENCES message(msgid)
);

CREATE TRIGGER ic AFTER INSERT ON message FOR EACH ROW
  BEGIN
    INSERT INTO `count` (msgid, accesscount, replycount) VALUES (NEW.msgid,0,0);
  END;

CREATE TABLE reply (
  replyid     INT PRIMARY KEY AUTO_INCREMENT,
  msgid       INT,
  userid      INT,
  replycontents   VARCHAR(5000),
  replytime   DATETIME,
  replyip     VARCHAR(30),
  CONSTRAINT fk_rmid FOREIGN KEY (msgid) REFERENCES message (msgid),
  CONSTRAINT fk_ruid FOREIGN KEY (userid) REFERENCES user(userid)
);

CREATE TRIGGER `insert` AFTER INSERT ON reply FOR EACH ROW
  BEGIN
    UPDATE count SET replycount=replycount+1 WHERE msgid=new.msgid;
  END;

CREATE TRIGGER mtcount AFTER INSERT ON message FOR EACH ROW
  BEGIN
    UPDATE theme SET count=count+1 WHERE theid=new.theid;
  END;



-- admin表 密码:hello
INSERT INTO mooc.admin (username, password, authority) VALUES ('admin', '5D41402ABC4B2A76B9719D911017C592', 1);

-- user表 密码:gss
INSERT INTO mooc.user (username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state) VALUES ('gss', '024743CF5C8D4C372DC312C74AABA64C', '高少帅', '男', '游戏、睡觉、足球、电影、吟诗、', '1997-09-09 00:00:00', '菏泽', 'xx@xx.com', '383196019', '2017-09-07 17:03:18', 1);
INSERT INTO mooc.user (username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state) VALUES ('gss1', '024743CF5C8D4C372DC312C74AABA64C', '刘德华', '男', '游戏、篮球、吟诗、', '1999-02-25 00:00:00', '香港', 'xx@xx.com', '383196', '2017-09-07 20:54:51', 1);
INSERT INTO mooc.user (username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state) VALUES ('gss2', '024743CF5C8D4C372DC312C74AABA64C', '成龙', '男', '游戏、睡觉、足球、电影、', '2006-05-17 00:00:00', '香港', 'qq@qq.com', '38319601', '2017-09-07 20:55:23', 1);
INSERT INTO mooc.user (username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state) VALUES ('gss3', '024743CF5C8D4C372DC312C74AABA64C', '范冰冰', '女', '睡觉、篮球、电影、吟诗、', '2008-06-18 00:00:00', '北京', 'xx@xx.com', '38319601', '2017-09-07 20:55:57', 1);
INSERT INTO mooc.user (username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state) VALUES ('gss4', '024743CF5C8D4C372DC312C74AABA64C', '李一桐', '女', '游戏、睡觉、篮球、电影、吟诗、', '2003-06-10 00:00:00', '北京', 'xx@xx.com', '38319601', '2017-09-07 20:56:32', 1);

-- theme表
INSERT INTO mooc.theme (thename, count) VALUES ('Java', 2);
INSERT INTO mooc.theme (thename, count) VALUES ('C++', 2);
INSERT INTO mooc.theme (thename, count) VALUES ('Python', 1);
INSERT INTO mooc.theme (thename, count) VALUES ('Spring', 2);
INSERT INTO mooc.theme (thename, count) VALUES ('Mybatis', 1);
INSERT INTO mooc.theme (thename, count) VALUES ('BUG反馈', 1);
INSERT INTO mooc.theme (thename, count) VALUES ('Mysql', 1);
INSERT INTO mooc.theme (thename, count) VALUES ('JAVAFX', 0);

-- message表
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (1, 'Java与Mysql的爱恨情仇', 'Java与Mysql的爱恨情仇Java与Mysql的爱恨情仇', '2017-09-07 20:42:46', '0:0:0:0:0:0:0:1', 1, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (2, '如何完全卸载MySQL数据库', '如何完全卸载MySQL数据库如何完全卸载MySQL数据库', '2017-09-07 20:57:20', '0:0:0:0:0:0:0:1', 2, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (2, 'JDK如何配置', 'JDK如何配置JDK如何配置', '2017-09-07 20:57:36', '0:0:0:0:0:0:0:1', 3, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (4, 'Java 命令行打印圣诞树', 'Java 命令行打印圣诞树Java 命令行打印圣诞树', '2017-09-07 20:58:20', '0:0:0:0:0:0:0:1', 4, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (4, '网站Bug请在此留下', '网站Bug请在此留下网站Bug请在此留下', '2017-09-07 20:58:29', '0:0:0:0:0:0:0:1', 5, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (5, '网易云爬虫的实现', '网易云爬虫的实现网易云爬虫的实现', '2017-09-07 20:59:14', '0:0:0:0:0:0:0:1', 6, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (5, '什么是原生动画函数', '什么是原生动画函数什么是原生动画函数', '2017-09-07 20:59:36', '0:0:0:0:0:0:0:1', 7, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (2, 'spring的事务管理方式', '有哪几种事务管理方式呢，事务的传播性又是如何的？', '2017-09-10 10:13:28', '0:0:0:0:0:0:0:1', 4, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (3, 'HashMap是怎么实现扩容的？', '什么情况下会发生扩容，采用何种方式扩容？', '2017-09-10 10:17:49', '0:0:0:0:0:0:0:1', 1, 1);
INSERT INTO mooc.message (userid, msgtopic, msgcontents, msgtime, msgip, theid, state) VALUES (1, 'C++继承语法', 'C++用什么语法来实现继承的？', '2017-09-10 11:04:47', '0:0:0:0:0:0:0:1', 2, 1);

-- count表
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (1, 6, 7);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (3, 12, 6);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (4, 16, 4);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (5, 8, 4);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (6, 34, 1);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (7, 10, 2);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (8, 11, 4);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (11, 2, 0);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (12, 0, 0);
INSERT INTO mooc.count (msgid, accesscount, replycount) VALUES (13, 3, 1);

-- reply表
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'Java与Mysql同属于一家公司：Oracle。', '2017-09-08 08:39:12', '172.17.31.34');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (5, 1, '使用循环打印以下圣诞树：
               *        // 空格（高度-1），* 1（2*行数-1），
              ***       // 空格（高度-2），*3
             *****    // 空格（高度-3），*5
            *******
           *********
          ***********  
n要求输入树的高度，打印圣诞树。', '2017-09-08 08:40:42', '172.17.31.35');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (5, 4, 'import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for (int i=0; i>count; i++){
            for (int j=0; j>count*2; j++){
                if ( i+j>count || j-count<i ){
                    System.out.print(" ");
                }
                else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}', '2017-09-08 08:41:24', '172.17.21.26');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 5, '1、重装系统；
2、把电脑砸了之后买台新的', '2017-09-08 08:43:04', '111.217.65.65');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (6, 2, '你这网站这么渣，还有必要找BUG？', '2017-09-08 08:44:04', '125.34.128.2');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (4, 3, '1、找个会配置的人；
2、把电脑砸了之后买台配置好的', '2017-09-08 08:45:12', '152.168.42.3');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (7, 2, '人生苦短，我用python。', '2017-09-08 08:46:12', '192.168.111.130');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (4, 2, '楼上可去你妹儿的吧！', '2017-09-08 08:47:15', '211.37.157.37');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (4, 4, '支持一楼</胜利>', '2017-09-08 08:48:06', '172.16.10.3');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 2, '青梅竹马、两小无猜，结果却成了兄妹。', '2017-09-08 09:21:18', '154.32.35.8');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (4, 1, '不如跳舞~', '2017-09-08 10:55:24', '172.16.13.3');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 4, '我是范爷我最帅！', '2017-09-08 16:36:12', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (7, 4, '你用python，我用Java', '2017-09-08 16:41:47', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (8, 4, 'Jquery', '2017-09-08 16:43:29', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 3, '楼上中二病患者一个', '2017-09-08 17:19:48', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 3, '0.00.0', '2017-09-08 17:20:04', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 3, '顶d=====(￣▽￣*)b', '2017-09-08 17:22:51', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (3, 3, 'dd', '2017-09-08 17:23:50', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (8, 3, '淡入淡出动画', '2017-09-08 17:24:16', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (8, 3, 'fadeIn和fadeOut', '2017-09-08 17:24:38', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (8, 3, '配合使用效果更好', '2017-09-08 17:24:57', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'I''m Jackie', '2017-09-08 17:26:02', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'Who are you?', '2017-09-08 17:26:14', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (5, 3, '杨辉三角', '2017-09-08 17:28:52', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (5, 3, '金字塔', '2017-09-08 17:29:03', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'Are you Tom?', '2017-09-08 17:30:23', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'Ok', '2017-09-08 17:30:44', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (1, 3, 'All right.', '2017-09-08 17:30:55', '0:0:0:0:0:0:0:1');
INSERT INTO mooc.reply (msgid, userid, replycontents, replytime, replyip) VALUES (13, 1, 'class A:B{} //A继承与B', '2017-09-10 16:43:02', '0:0:0:0:0:0:0:1');