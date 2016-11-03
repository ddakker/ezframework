/*
drop table ACL_AUTH_HIERARCHY;
drop table ACL_AUTH_RESOURCE;
drop table ACL_USER_AUTH;
drop table ACL_AUTH;
drop table ACL_RESOURCE;
drop table ACL_USER_IP;
*/
-- drop table ez_user;
-- drop table EZ_SAMPLE_DESC;
-- drop table EZ_SAMPLE;
-- drop table EZ_LOG;


-- drop table EZ_USER;
CREATE TABLE EZ_USER
(
	USER_ID    VARCHAR(20) NOT NULL /*comment '유저ID'*/,
	USER_TYPE  INT DEFAULT 1001 NOT NULL   /*comment '사용자타입'*/,
	USER_NM    VARCHAR(20) NOT NULL /*comment '사용자명'*/,
	USER_PWD   VARCHAR(100) NOT NULL /*comment '비밀번호'*/,
	USE_YN     VARCHAR(1) DEFAULT 'Y' NOT NULL /*comment '사용여부'*/
) /*comment = '사용자'*/
;
/*
ALTER TABLE EZ_USER ADD(
	CONSTRAINT EZ_USER_PK PRIMARY KEY (USER_ID));
*/

INSERT INTO EZ_USER (USER_ID, USER_TYPE, USER_NM, USER_PWD, USE_YN) VALUES
	('admin', 2001, '시스템 관리자', 'admin', 'Y'),
	('middleadmin', 2001, '시스템 중간 관리자', 'middleadmin', 'Y'),
	('cpadmin', 3001, '제휴사 관리자', 'cpadmin', 'Y'),
	('test1', 1002, '테스터1', 'teste1', 'Y'),
	('test2', 1001, '테스터2', 'teste2', 'Y'),
	('user', 1001, '사용자', 'user', 'Y');


-- drop table ACL_AUTH;
CREATE TABLE ACL_AUTH
(
	AUTH_CD   VARCHAR (20) NOT NULL /*comment '권한관리'*/,
	AUTH_NM   VARCHAR (50) /*comment '명칭'*/,
	AUTH_DC   VARCHAR (200) /*comment '설명'*/,
	REG_DT    VARCHAR (14) /*comment '등록일'*/,
	MODI_DT   VARCHAR (14) /*comment '수정일'*/
) /*comment = '권한관리'*/
;
/*
ALTER TABLE ACL_AUTH ADD(
	CONSTRAINT EZ_AUTH PRIMARY KEY (AUTH_CD));
*/


INSERT INTO ACL_AUTH (AUTH_CD, AUTH_NM, AUTH_DC, REG_DT, MODI_DT) VALUES
('ROLE_ADMIN', '관리자 권한', '관리자 권한', '20140915111541', '20140915111541'),
('ROLE_MIDDLE_ADMIN', '중간 관리자', '중간 관리자', '20150320152747', '20150323165538'),
('ROLE_USER', '사용자 권한', '사용자 권한', '20140915111541', '20140915111541');


-- drop table ACL_RESOURCE;
CREATE TABLE ACL_RESOURCE
(
	RESOURCE_CD    VARCHAR (50) NOT NULL /*comment '리소스코드'*/,
	RESOURCE_NM    VARCHAR (100) /*comment '명칭'*/,
	RESOURCE_DC    VARCHAR (200) /*comment '설명'*/,
	RESOURCE_URL   VARCHAR (300) /*comment '패턴'*/,
	REG_DT         VARCHAR (20) /*comment '등록일'*/,
	MODI_DT        VARCHAR (20) /*comment '수정일'*/
) /*comment = '리소스관리'*/
;
/*
ALTER TABLE ACL_RESOURCE ADD(
	CONSTRAINT PK_EZ_ROLE PRIMARY KEY (RESOURCE_CD));
*/

INSERT INTO ACL_RESOURCE (RESOURCE_CD, RESOURCE_NM, RESOURCE_DC, RESOURCE_URL, REG_DT, MODI_DT) VALUES
	('acl', '인증 페이지', '인증 페이지', '/acl/**', '20140915113112', '20140915121523'),
	('testAdminAuth', '관리자 전용', '관리자 전용', '/sample/onlyAdmin', '20140915113323', '20140915113323'),
	('testMiddleAuth', '중간 관리자 접근 리소스', '중간 관리자 용이지만 계층관계 설정으로 시스템관리자도 접근 되는지 테스트', '/sample/onlyMiddle', '20150320153256', '20150320153256'),
	('testUserAuth', '사용자 전용', '사용자 전용', '/sample/onlyUser', '20140915113428', '20140915113428'),
	('wertw', 'wert', 'wertw2345234a111', 'wetr', '20150318091121', '20150323165339');



-- drop table ACL_USER_AUTH;
CREATE TABLE ACL_USER_AUTH
(
	USER_AUTH_KEY  	VARCHAR (50) NOT NULL /*comment '사이트별 사용자키 PK(예) USER_ID+USER_TYPE)'*/,
	AUTH_CD   		VARCHAR (20) /*comment '권한코드'*/,
	REG_DT    		VARCHAR (14) /*comment '등록일'*/,
	MODI_DT   		VARCHAR (14) /*comment '수정일'*/
	/*CONSTRAINT FK_ACL_AUTH_TO_ACL_USER_AUTH FOREIGN KEY (AUTH_CD) REFERENCES ACL_AUTH(AUTH_CD)*/
) /*comment = '사용자권한관리'*/
;
/*
ALTER TABLE ACL_USER_AUTH ADD(
	CONSTRAINT EZ_USER_AUTH PRIMARY KEY (USER_AUTH_KEY));
*/

INSERT INTO ACL_USER_AUTH (USER_AUTH_KEY, AUTH_CD, REG_DT, MODI_DT) VALUES
	('admin2001', 'ROLE_ADMIN', '20140915112633', '20150324204151'),
	('middleadmin2001', 'ROLE_MIDDLE_ADMIN', '20150320163304', '20150320163304'),
	('cpdmin3001', 'ROLE_MIDDLE_ADMIN', '20150320163304', '20150320163304'),
	('test11001', 'ROLE_USER', '20150320162906', '20150324204132'),
	('test21001', 'ROLE_ADMIN', '20150320162912', '20150324204151'),
	('user1001', 'ROLE_USER', '20140915112714', '20140925132139');

-- drop table ACL_AUTH_RESOURCE;
CREATE TABLE ACL_AUTH_RESOURCE
(
	AUTH_CD       VARCHAR (20) NOT NULL /*comment '권한코드'*/,
	RESOURCE_CD   VARCHAR (50) NOT NULL /*comment '리소스코드'*/,
	REG_DT        VARCHAR (14) /*comment '등록일'*/,
	MODI_DT       VARCHAR (14) /*comment '수정일'*/
	/*
	CONSTRAINT FK_AUTH_TO_AUTH_RESOURCE FOREIGN KEY (AUTH_CD) REFERENCES ACL_AUTH (AUTH_CD),
	CONSTRAINT FK_RESOURCE_TO_AUTH_RESOURCE FOREIGN KEY (RESOURCE_CD) REFERENCES ACL_RESOURCE (RESOURCE_CD)
	*/
) /*comment = '권한별 리소스관리'*/
;
/*
ALTER TABLE ACL_AUTH_RESOURCE ADD(
	CONSTRAINT PK_ACL_AUTH_RESOURCE PRIMARY KEY (AUTH_CD, RESOURCE_CD));
*/

INSERT INTO ACL_AUTH_RESOURCE (AUTH_CD, RESOURCE_CD, REG_DT, MODI_DT) VALUES
('ROLE_ADMIN', 'acl', '20140915113129', '20140915113129'),
('ROLE_ADMIN', 'testAdminAuth', '20140915113441', '20140915113441'),
('ROLE_MIDDLE_ADMIN', 'testMiddleAuth', '20150320153314', '20150320153314'),
('ROLE_USER', 'testUserAuth', '20140915113451', '20140915113451');


-- drop table ACL_AUTH_HIERARCHY;
CREATE TABLE ACL_AUTH_HIERARCHY
(
	SEQ              INT IDENTITY PRIMARY KEY,
	PARENT_AUTH_CD   VARCHAR (20) NOT NULL /*comment '계층 부모'*/,
	CHILD_AUTH_CD    VARCHAR (20) NOT NULL /*comment '계층 자식'*/
	/*PRIMARY KEY ACL_AUTH_HIERARCHY_PK (SEQ)*/
) /*comment = '권한 계층 관리'*/
;

INSERT INTO ACL_AUTH_HIERARCHY (SEQ, PARENT_AUTH_CD, CHILD_AUTH_CD) VALUES
	(1, 'ROLE_ADMIN', 'ROLE_MIDDLE_ADMIN'),
	(2, 'ROLE_MIDDLE_ADMIN', 'ROLE_USER');

/*
ALTER TABLE ACL_AUTH_HIERARCHY ADD(
	CONSTRAINT ACL_AUTH_HIERARCHY_UK UNIQUE (PARENT_AUTH_CD, CHILD_AUTH_CD));
*/



	CREATE TABLE EZ_LOG
(
	SEQ      int IDENTITY PRIMARY KEY,
	MSG      VARCHAR (4000),
	REG_DT   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE ACL_USER_IP (
	USER_AUTH_KEY	VARCHAR(20) NOT NULL /*COMMENT '사이트별 사용자키 PK(예) USER_ID+USER_TYPE)'*/,
	IP	VARCHAR(15) NOT NULL /*COMMENT 'IP'*/,
	IP_DC	VARCHAR(200) NOT NULL /*COMMENT '설명'*/,
	PRIMARY KEY (USER_AUTH_KEY, IP)
) /*comment = '사용자 IP 체크'*/
;


CREATE TABLE EZ_SAMPLE
(
	SEQ     int IDENTITY PRIMARY KEY,
	NAME    VARCHAR (500),
	EMAIL   VARCHAR (500),
	AB_CD	INT DEFAULT 10000,
	PHONE	VARCHAR(11) DEFAULT '01012345678'
);

CREATE TABLE EZ_SAMPLE_DESC
(
	MSG   VARCHAR (100),
	SEQ   int,
	FOREIGN KEY (SEQ) REFERENCES EZ_SAMPLE (SEQ)
);


INSERT INTO ez_sample (name, email) VALUES ('ddakker0', 'ddakker0@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker1', 'ddakker1@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker2', 'ddakker2@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker3', 'ddakker3@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker4', 'ddakker4@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker5', 'ddakker5@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker6', 'ddakker6@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker7', 'ddakker7@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker8', 'ddakker8@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker9', 'ddakker9@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker10', 'ddakker10@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker11', 'ddakker11@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker12', 'ddakker12@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker13', 'ddakker13@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker14', 'ddakker14@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker15', 'ddakker15@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker16', 'ddakker16@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker17', 'ddakker17@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker18', 'ddakker18@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker19', 'ddakker19@gmail.com');
INSERT INTO ez_sample (name, email) VALUES ('ddakker20', 'ddakker20@gmail.com');

insert into ez_sample_desc (seq, msg) values (1, 'msg1');
insert into ez_sample_desc (seq, msg) values (2, 'msg1');
insert into ez_sample_desc (seq, msg) values (4, 'msg1');
insert into ez_sample_desc (seq, msg) values (6, 'msg1');
insert into ez_sample_desc (seq, msg) values (8, 'msg1');
insert into ez_sample_desc (seq, msg) values (10, 'msg1');

insert into ez_sample_desc (seq, msg) values (1, 'msg1-1');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-2');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-3');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-4');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-5');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-6');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-7');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-8');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-9');
insert into ez_sample_desc (seq, msg) values (1, 'msg1-10');
