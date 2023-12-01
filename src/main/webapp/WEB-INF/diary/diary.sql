create table Diary (
  idx  int not null auto_increment,	
  mid  varchar(30) not null,				
  nickName varchar(30) not null,
  photo varchar(30) not null,
  title   varchar(100)  not null,		
  content text not null,						/* 게시글 내용 */
  readNum	int not null default 0,		/* 글 조회수 */
  hostIp	varchar(40) not null,			/* 글 올린이 IP */
  openLevel	int default 1,			/* 0 : 전체공개 1:친구공개 2:비공개 */
  wDate		datetime default now(),		/* 글 올린 날짜(시간) */
  good		int default 0,						/* '좋아요' 클릭 횟수 누적 음 따로 뺄까...? */  
  ref     int not null,
  re_step int default 1,
  re_level int default 1,							/* 계층형 */
  
  primary key(idx)
/*  foreign key(mid) references member(mid)
  on update cascade */
);
drop table diary;
select * from diary;

select B.* from (select idx,ref from diary where mid = 'admin' and re_step=1) as A left outer join diary as B on A.ref = B.ref order by B.ref desc, B.re_level desc limit 0,10;

/* 새글은 가장높은ref에서 +1 새글의 re_step re_level은 1*/
/* 답글은 해당ref와 같음 re_step은 부모글 +1 //레벨은 부모레벨보다 큰숫자를 전부다 1씩증가시키고 나는 부모레벨+1*/
select * from Diary order by ref desc, re_step asc, re_level desc;

/* 답글
update	board set re_level=re_level+1 where ref=? and re_level>?

sql = "insert into board values(default,?,?,?,?,now(),?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step+1);
			pstmt.setInt(7, re_level+1);
			pstmt.setString(8, bean.getContent());
			
*/
create table DiaryGood (
	idx int not null auto_increment,
	diary_idx int not null,
	mid varchar(30) not null,
	primary key(idx),
	unique key(diary_idx,mid),
	foreign key(diary_idx) references Diary(idx),
	foreign key(mid) references member(mid)
);
drop table DiaryGood;

create table DiaryReply (
  idx      	int not null auto_increment,	/* 댓글의 고유번호 */
  Diary_Idx	int not null,						/* 원본글(부모글)의 고유번호(외래키로 설정) */
  mid				varchar(30) not null,		/* 댓글 올린이의 아이디 */
  nickName  varchar(30) not null,		/* 댓글 올린이의 닉네임 */
  content   text  not null,					/* 댓글 내용 */  
  ref     int not null,
  re_step int default 1,
  re_level int default 1,
  primary key(idx),
  foreign key(Diary_Idx) references Diary(idx),
  foreign key(mid) references member(mid)
  on update cascade on delete cascade			/* 부모필드를 수정하면 함께 영향을 받는다. */
);
drop table DiaryReply;

create table DiaryFriend (
	myId varchar(30) not null,
	friendId varchar(30) not null,
	primary key(myId,friendId)
);

drop table diaryfriend;

create table DiaryPart(
	
);

				select D.* 
				from (select idx,ref from (select friendId from diaryFriend where myId = 'wlsl9411') as A
				inner join diary as B on A.friendId=B.mid where re_step=1 ) as C
		 		left outer join diary as D on C.ref = D.ref
					order by D.ref desc, D.re_level desc limit ?,?;


create table complaint (
  idx  int not null auto_increment,		/* 신고테이블 고유번호 */
 	diary_Idx int not null,								/* 해당 항목 게시글의 고유번호 */
  cpMid varchar(30) not null,					/* 신고자 아이디 */
  cpContent text not null,						/* 신고 사유 */
  primary key(idx)
);
desc complaint;

select A.cpMid as cpMid, A.cpContent as cpContent, B.nickName as name, B.title as title 
						from complaint as A inner join diary as B on A.diary_Idx = B.idx