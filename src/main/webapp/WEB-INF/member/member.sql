create table member (
  idx       int not null auto_increment,/* 회원 고유번호 */
  mid       varchar(30) not null,				/* 회원 아이디(중복불허) */
  pwd       varchar(100) not null,			/* 회원 비밀번호(SHA256 암호화 처리) */
  nickName  varchar(20) not null,				/* 회원 별명(중복불허/수정가능) */
  name		  varchar(20) not null,				/* 회원 성명 */
  gender    char(2)	not null default '남자',	/* 회원 성별 */
  birthday  datetime,			/* 회원 생일 */
  tel			  varchar(15),								/* 전화번호 : 010-1234-5678 */
  address   varchar(100),								/* 주소(다음 API 활용) */
  email		  varchar(60) not null,		  	/* 이메일(아이디/비밀번호 분실시에 사용)-형식체크필수 */
  content   text,												/* 회원 소개 */
  level     int default 1,							/* 회원등급(-1: 탈퇴회원 0:관리자, 1:준회원, 2:정회원, 3:우수회원, (4:운영자)) */
  lastDate  datetime default now(),			/* 마지막 접속일 */
  salt      char(32) not null, 			/* 비밀번호 보안을 위한 salt */
  primary key (idx),
  unique(mid)
);

drop table member;

desc member;

select * from member;

