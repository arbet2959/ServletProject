create table Diary (
  idx  int not null auto_increment,	
  mid  varchar(30) not null,				
  nickName varchar(30) not null,		
  title   varchar(100)  not null,		
  email   varchar(60),							/* 이메일 주소 */
  content text not null,						/* 게시글 내용 */
  readNum	int not null default 0,		/* 글 조회수 */
  hostIp	varchar(40) not null,			/* 글 올린이 IP */
  openLevel	int default 0,			/* -1 : 삭제 0 : 전체공개 1:친구공개 2:비공개 */
  wDate		datetime default now(),		/* 글 올린 날짜(시간) */
  good		int default 0,						/* '좋아요' 클릭 횟수 누적 음 따로 뺄까...? */  
  ref     int not null,
  re_step int not null,
  re_level int not null,							/* 계층형 */
  
  primary key(idx)
/*  foreign key(nickName) references member(nickName)
  on update cascade */
);
drop table diary;

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

create table DiaryReply (
  idx      	int not null auto_increment,	/* 댓글의 고유번호 */
  Diary_Idx	int not null,						/* 원본글(부모글)의 고유번호(외래키로 설정) */
  mid				varchar(30) not null,		/* 댓글 올린이의 아이디 */
  nickName  varchar(30) not null,		/* 댓글 올린이의 닉네임 */
  wDate			datetime default now(),	/* 댓글 올린 날짜 */
  hostIp		varchar(50) not null,		/* 댓글 올린 PC의 고유 IP */
  content   text  not null,					/* 댓글 내용 */  
  ref     int not null,
  re_step int not null,
  re_level int not null,
  primary key(idx),
  foreign key(Diary_Idx) references Diary(idx)
  on update cascade			/* 부모필드를 수정하면 함께 영향을 받는다. */
);

create table Diaryfriend (
	myId varchar(30) not null,
	friend varchar(30) not null
);

create table DiaryPart(

);
