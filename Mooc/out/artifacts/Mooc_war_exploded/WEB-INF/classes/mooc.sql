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
  count       INT
);

CREATE TABLE message(
  msgid       INT PRIMARY KEY AUTO_INCREMENT,
  userid      INT NOT NULL,
  msgtopic    VARCHAR(200) NOT NULL ,
  msgcontents VARCHAR(5000) NOT NULL ,
  msgtime     DATETIME NOT NULL ,
  msgip       VARCHAR(30),
  theid       INT,
  state       INT,
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
