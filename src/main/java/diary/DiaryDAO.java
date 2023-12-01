package diary;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.RootDAO;


public class DiaryDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int res=0;
	private String sql;
	DiaryVO vo;
		private DiaryDAO() {
			conn = RootDAO.getInstance().getConnection();
		}
		
		private static class DAOHolder{
			private static final DiaryDAO INSTANCE = new DiaryDAO();
		}
		public static DiaryDAO getInstance() {
			return DAOHolder.INSTANCE;
		}
		
		public void connClose() {
			try {
				conn.close();
			} catch (Exception e) {	}
		}
		
		public void pstmtClose() {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e) {	}
		}
		
		public void rsClose() {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e) {	}
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e) {	}
		}

		public int getTotRecCnt() {
			int totRecCnt = 0;
			try {
				sql = "select count(*) as cnt from diary";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				totRecCnt = rs.getInt("cnt");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rsClose();
			}
			return totRecCnt;
		}

		public ArrayList<DiaryVO> getDiaryList(int startIndexNo, int pageSize) {
			ArrayList<DiaryVO> vos = new ArrayList<DiaryVO>();
			try {
				sql = "select * from diary order by ref desc, re_level desc limit ?,?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo = new DiaryVO();
					vo.setIdx(rs.getInt("idx"));
					vo.setMid(rs.getString("mid"));
					vo.setNickName(rs.getString("nickName"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setContent(rs.getString("content"));
					vo.setReadNum(rs.getInt("readNum"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setOpenLevel(rs.getInt("openLevel"));
					vo.setwDate(rs.getString("wDate"));
					vo.setGood(rs.getInt("good"));
					vo.setRef(rs.getInt("ref"));
					vo.setRe_step(rs.getInt("re_step"));
					vo.setRe_level(rs.getInt("re_level"));
					
					vos.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rsClose();
			}
			return vos;
		}

		public int insertDiary(DiaryVO vo) {
			int res=0;
			int ref=0;
			int re_step=1;
			int re_level=1;
			try {
				String refsql ="select max(ref) from diary";
				pstmt = conn.prepareStatement(refsql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					ref = rs.getInt(1)+1; //최댓값 구한거에 +1
				}else {
					ref = 1;
				}
				
				String sql = "insert into diary values(default,?,?,?,?,?,default,?,?,default,default,?,default,default)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
				pstmt.setString(2, vo.getNickName());
				pstmt.setString(3, vo.getPhoto());
				pstmt.setString(4, vo.getTitle());
				pstmt.setString(5, vo.getContent());
				pstmt.setString(6, vo.getHostIp());
				pstmt.setInt(7, vo.getOpenLevel());
				pstmt.setInt(8, ref);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			
			
			return res;
		}
//한개가져오기 
		public DiaryVO getDiaryIdx(int idx, boolean a) {
			try {
				
				if(a) setCountPlus(idx);
				
				String sql = "select * from diary where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					vo = new DiaryVO();
					vo.setIdx(rs.getInt("idx"));
					vo.setMid(rs.getString("mid"));
					vo.setNickName(rs.getString("nickName"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setContent(rs.getString("content"));
					vo.setReadNum(rs.getInt("readNum"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setOpenLevel(rs.getInt("openLevel"));
					vo.setwDate(rs.getString("wDate"));
					vo.setGood(rs.getInt("good"));
					vo.setRef(rs.getInt("ref"));
					vo.setRe_step(rs.getInt("re_step"));
					vo.setRe_level(rs.getInt("re_level"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			return vo;
		}

		private void setCountPlus(int idx) {
			String readsql = "update diary set readNum = readNum+1 where idx=?";
			try {
				pstmt = conn.prepareStatement(readsql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				/* pstmtClose(); */
			}
			
		}

		public int updateDiary(DiaryVO vo, int idx) {
			try {
				sql = "update diary set photo=?, title=?, content=?, openLevel=? where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getPhoto());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContent());
				pstmt.setInt(4, vo.getOpenLevel());
				pstmt.setInt(5, idx);
				
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int getMyRecCnt(String sMid) {
			int totRecCnt = 0;
			try {
				sql = "select count(*) as cnt "
						+ "from (select idx,ref from diary where mid = ? and re_step=1) as A left outer join diary as B on A.ref = B.ref ";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sMid);
				rs = pstmt.executeQuery();
				rs.next();
				totRecCnt = rs.getInt("cnt");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rsClose();
			}
			return totRecCnt;
		}
		
//댓글도 최신순서로 정렬
		
		
		public ArrayList<DiaryVO> getMyDiaryList(int startIndexNo, int pageSize, String sMid) {
			ArrayList<DiaryVO> vos = new ArrayList<DiaryVO>();
			try {
				sql = "select B.* "
						+ "from (select idx,ref from diary where mid = ? and re_step=1) as A left outer join diary as B on A.ref = B.ref "
						+ "order by B.ref desc, B.re_level desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sMid);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo = new DiaryVO();
					vo.setIdx(rs.getInt("idx"));
					vo.setMid(rs.getString("mid"));
					vo.setNickName(rs.getString("nickName"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setContent(rs.getString("content"));
					vo.setReadNum(rs.getInt("readNum"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setOpenLevel(rs.getInt("openLevel"));
					vo.setwDate(rs.getString("wDate"));
					vo.setGood(rs.getInt("good"));
					vo.setRef(rs.getInt("ref"));
					vo.setRe_step(rs.getInt("re_step"));
					vo.setRe_level(rs.getInt("re_level"));
					
					vos.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("SQL구문 오류 : " + e.getMessage());
			} finally {
				rsClose();
			}
			return vos;
		}
		
		//반업데이트형 계층형 게시판, 보기좋은순서
		//level step은 1에서 시작
	/*SELECT IFNULL(min(re_level),1) FROM BOARD
	 *  WHERE  ref = (원글의 ref)
				   AND re_level > (원글의 re_level)
				   AND re_step <= (원글의 re_step)
				=>> 1번
				*/
		/* 1번 결과가 1이면
		 * 
		 *  select IFNULL(MAX(SORTS),1)+1 from board where ref= (원글의 ref)
		 *  insert into board values(ref,re_step+1,위에값)
		 * 
		 * 1번 결과가 1이아니면
		 * 
		 * 	update board set where ref=(원본ref) and re_level>=(1번값)
		 * insert into board values(ref,re_step+1,1번값)
		 */
		
		
		//반업데이트형 계층형 게시판, 최신순서
		public int RewriteDiary(DiaryVO vo) {
			int res=0;
			int ref=vo.getRef();
			int re_step=vo.getRe_step();
			int re_level=vo.getRe_level();
			try {
				String levelsql ="update diary set re_level=re_level+1 where ref=? and re_level>=?";
				pstmt = conn.prepareStatement(levelsql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_level);
				pstmt.executeUpdate();
				
				
				String sql = "insert into diary values(default,?,?,?,?,?,default,?,?,default,default,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
				pstmt.setString(2, vo.getNickName());
				pstmt.setString(3, vo.getPhoto());
				pstmt.setString(4, vo.getTitle());
				pstmt.setString(5, vo.getContent());
				pstmt.setString(6, vo.getHostIp());
				pstmt.setInt(7, vo.getOpenLevel());
				pstmt.setInt(8, ref);
				pstmt.setInt(9, re_step+1);
				pstmt.setInt(10, re_level);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				pstmtClose();
			}
			return res;
		}

		public int deleteDiary(int idx) {
			try {
				sql = "delete from diary where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				res =pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int getSearchGood(int diary_idx, String sMid) {
			res = 0;
				try {
					sql = "select count(*) as cnt from DiaryGood where diary_idx = ? and mid = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, diary_idx);
					pstmt.setString(2, sMid);
					rs = pstmt.executeQuery();
					if(rs.next())
						res = rs.getInt("cnt");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					rsClose();
				}
			return res;
		}

		public int insertGood(int diary_idx, String sMid) {
				
			try {
				sql="insert into DiaryGood values(default,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, diary_idx);
				pstmt.setString(2, sMid);
				res = pstmt.executeUpdate();
				if(res==0) return res;
				
				sql="update diary set good=good+1 where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, diary_idx);
				res = pstmt.executeUpdate();

				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				pstmtClose();
			}
			return diary_idx;
		}

		public int deleteGood(int diary_idx, String sMid) {
			
		try {
			sql="delete from DiaryGood where diary_idx = ? and mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diary_idx);
			pstmt.setString(2, sMid);
			res = pstmt.executeUpdate();
			if(res==0) return res;
			
			sql="update diary set good=good-1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diary_idx);
			res = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pstmtClose();
		}
		return res;
		}

		public int deleteDiaryReply(int idx) {
			try {
				sql = "delete from diaryReply where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int insertDiaryReply(DiaryReplyVO vo) {
			int res=0;
			int ref=0;
			int re_step=1;
			int re_level=1;
			try {
				String refsql ="select max(ref) from diaryReply";
				pstmt = conn.prepareStatement(refsql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					ref = rs.getInt(1)+1; //최댓값 구한거에 +1
				}else {
					ref = 1;
				}
				
				String sql = "insert into diaryReply values(default,?,?,?,?,?,default,default)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getDiary_Idx());
				pstmt.setString(2, vo.getMid());
				pstmt.setString(3, vo.getNickName());
				pstmt.setString(4, vo.getContent());
				pstmt.setInt(5, ref);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			return res;
		}

		public ArrayList<DiaryReplyVO> getReplyList(int diary_Idx, int startIndexNo, int replyPageSize) {
			ArrayList<DiaryReplyVO> ReplyVOs = new ArrayList<DiaryReplyVO>();
			try {
				sql = "select * from diaryReply where diary_Idx=? order by ref asc, re_level asc limit ?,?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, diary_Idx);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, replyPageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					DiaryReplyVO replyVO = new DiaryReplyVO();
					replyVO.setIdx(rs.getInt("idx"));
					replyVO.setDiary_Idx(rs.getInt("diary_Idx"));
					replyVO.setMid(rs.getString("mid"));
					replyVO.setNickName(rs.getString("nickName"));
					replyVO.setContent(rs.getString("content"));
					replyVO.setRef(rs.getInt("ref"));
					replyVO.setRe_step(rs.getInt("re_step"));
					replyVO.setRe_level(rs.getInt("re_level"));
					
					ReplyVOs.add(replyVO);
				}
			} catch (SQLException e) {
				System.out.println("SQL구문 오류 : " + e.getMessage());
			} finally {
				rsClose();
			}
			return ReplyVOs;
		}

		public int getTotReplyCnt(int Diary_Idx) {
			try {
				sql = "select count(*) as cnt from diaryReply where Diary_Idx = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, Diary_Idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					res = rs.getInt("cnt");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		
		/*SELECT IFNULL(MIN(re_level),1) FROM BOARD
		 *  WHERE  ref = (원글의 ref)
					   AND re_level > (원글의 re_level)
					   AND re_step <= (원글의 re_step)
					=>> 1번
					*/
			/* 1번 결과가 1이면
			 * 
			 *  select IFNULL(max(re_level),1)+1 from board where ref= (원글의 ref)
			 *  insert into board values(ref,re_step+1,위에값)
			 * 
			 * 1번 결과가 1이아니면
			 * 
			 * 	update board set re_level=re_level+1 where ref=(원본ref) and re_level>=(1번값)
			 * insert into board values(ref,re_step+1,1번값)
			 */
		
		public int rewriteDiaryReply(DiaryReplyVO replyVO, int ref, int re_step, int re_level) {
			int res1 =1; int res2 = 0;
			System.out.println(replyVO.toString());
			System.out.println(ref);
			System.out.println(re_step);
			System.out.println(re_level);
			try {
				String sql1 = "select IFNULL(min(re_level),1) from diaryReply "
						+ "where ref = ? and re_level> ? and re_step <= ?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_level);
				pstmt.setInt(3, re_step);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					res1 = rs.getInt(1);
				}
				
				if(res1==1) {
					String sql2 = "select IFNULL(max(re_level),1) from diaryReply "
							+ "where ref=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, ref);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						res2 = rs.getInt(1);
					}
					
					sql = "insert into diaryReply values(default,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, replyVO.getDiary_Idx());
					pstmt.setString(2, replyVO.getMid());
					pstmt.setString(3, replyVO.getNickName());
					pstmt.setString(4, replyVO.getContent());
					pstmt.setInt(5, ref);
					pstmt.setInt(6, re_step+1);
					pstmt.setInt(7, res2+1);
					res = pstmt.executeUpdate();
					
					
				}else {
					
					String sql3 = "update diaryReply set re_level = re_level+1 "
							+ "where ref = ? and re_level >= ?";
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, res1);
					pstmt.executeUpdate();
					
					sql = "insert into diaryReply values(default,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, replyVO.getDiary_Idx());
					pstmt.setString(2, replyVO.getMid());
					pstmt.setString(3, replyVO.getNickName());
					pstmt.setString(4, replyVO.getContent());
					pstmt.setInt(5, ref);
					pstmt.setInt(6, re_step+1);
					pstmt.setInt(7, res1);
					res = pstmt.executeUpdate();
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			
			return res;
		}

		public ArrayList<DiaryVO> getFriendDiaryList(int startIndexNo, int pageSize, String sMid) {
			
			ArrayList<DiaryVO> vos = new ArrayList<DiaryVO>();
			try {
				sql = "select D.* "
						+ "				from (select idx,ref from (select friendId from diaryFriend where myId = ?) as A"
						+ "				inner join diary as B on A.friendId=B.mid where re_step=1 ) as C"
						+ "		 		left outer join diary as D on C.ref = D.ref"
						+ "					order by D.ref desc, D.re_level desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sMid);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo = new DiaryVO();
					vo.setIdx(rs.getInt("idx"));
					vo.setMid(rs.getString("mid"));
					vo.setNickName(rs.getString("nickName"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setContent(rs.getString("content"));
					vo.setReadNum(rs.getInt("readNum"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setOpenLevel(rs.getInt("openLevel"));
					vo.setwDate(rs.getString("wDate"));
					vo.setGood(rs.getInt("good"));
					vo.setRef(rs.getInt("ref"));
					vo.setRe_step(rs.getInt("re_step"));
					vo.setRe_level(rs.getInt("re_level"));
					
					vos.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("SQL구문 오류 : " + e.getMessage());
			} finally {
				rsClose();
			}
			return vos;
		}

		public int getFriendRecCnt(String sMid) {
			int totRecCnt = 0;
			try {
				sql = "select count(D.idx) as cnt"
						+ "				from (select idx,ref from (select friendId from diaryFriend where myId = ?) as A"
						+ "				inner join diary as B on A.friendId=B.mid where re_step=1 ) as C"
						+ "		 		left outer join diary as D on C.ref = D.ref";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sMid);
				rs = pstmt.executeQuery();
				rs.next();
				totRecCnt = rs.getInt("cnt");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rsClose();
			}
			return totRecCnt;
		}

		


		
		
}
