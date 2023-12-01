package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diary.DiaryVO;
import util.RootDAO;


public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int res=0;
	private String sql;
		
		private MemberDAO() {
			conn = RootDAO.getInstance().getConnection();
		}
		
		private static class DAOHolder{
			private static final MemberDAO INSTANCE = new MemberDAO();
		}
		public static MemberDAO getInstance() {
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

		public MemberVO getMemberMidCheck(String mid) {
			MemberVO vo = new MemberVO();
			sql = "select * from member where mid= ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setIdx(rs.getInt(1));
					vo.setMid(rs.getString(2));
					vo.setPwd(rs.getString(3));
					vo.setNickName(rs.getString(4));
					vo.setName(rs.getString(5));
					vo.setGender(rs.getString(6));
					vo.setBirthday(rs.getString(7));
					vo.setTel(rs.getString(8));
					vo.setAddress(rs.getString(9));
					vo.setEmail(rs.getString(10));
					vo.setContent(rs.getString("content"));
					vo.setLevel(rs.getInt(12));
					vo.setLastDate(rs.getString(13));
					vo.setSalt(rs.getString(14));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			return vo;
		}

		public MemberVO getMemberNickCheck(String nickName) {
			MemberVO vo = new MemberVO();
			sql = "select * from member where nickName= ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nickName);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setIdx(rs.getInt(1));
					vo.setMid(rs.getString(2));
					vo.setPwd(rs.getString(3));
					vo.setNickName(rs.getString(4));
					vo.setName(rs.getString(5));
					vo.setGender(rs.getString(6));
					vo.setBirthday(rs.getString(7));
					vo.setTel(rs.getString(8));
					vo.setAddress(rs.getString(9));
					vo.setEmail(rs.getString(10));
					vo.setContent(rs.getString("content"));
					vo.setLevel(rs.getInt(12));
					vo.setLastDate(rs.getString(13));
					vo.setSalt(rs.getString(14));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			return vo;
		}

		public int signUpMember(MemberVO vo) {
			sql = "insert into member values (default,?,?,?,?,?,?,?,?,?,?,default,default,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMid());
				pstmt.setString(2, vo.getPwd());
				pstmt.setString(3, vo.getNickName());
				pstmt.setString(4, vo.getName());
				pstmt.setString(5, vo.getGender());
				pstmt.setString(6, vo.getBirthday());
				pstmt.setString(7, vo.getTel());
				pstmt.setString(8, vo.getAddress());
				pstmt.setString(9, vo.getEmail());
				pstmt.setString(10, vo.getContent());
				pstmt.setString(11, vo.getSalt());
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmtClose();
			}
			return res;
		}

		public int getTotRecCnt() {
			int totRecCnt = 0;
			try {
				sql = "select count(*) as cnt from member";
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

		public ArrayList<MemberVO> getMemberList(int startIndexNo, int pageSize) {
			ArrayList<MemberVO> vos = new ArrayList<MemberVO>();
			try {
				sql = "select * from member limit ?,?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberVO vo = new MemberVO();
					vo.setIdx(rs.getInt(1));
					vo.setMid(rs.getString(2));
					vo.setPwd(rs.getString(3));
					vo.setNickName(rs.getString(4));
					vo.setName(rs.getString(5));
					vo.setGender(rs.getString(6));
					vo.setBirthday(rs.getString(7));
					vo.setTel(rs.getString(8));
					vo.setAddress(rs.getString(9));
					vo.setEmail(rs.getString(10));
					vo.setContent(rs.getString("content"));
					vo.setLevel(rs.getInt(12));
					vo.setLastDate(rs.getString(13));
					vo.setSalt(rs.getString(14));
					
					vos.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rsClose();
			}
			return vos;
		}

		public MemberVO getMemberSearchidx(int idx) {
			MemberVO vo = new MemberVO();
			sql = "select * from member where idx= ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setIdx(rs.getInt(1));
					vo.setMid(rs.getString(2));
					vo.setPwd(rs.getString(3));
					vo.setNickName(rs.getString(4));
					vo.setName(rs.getString(5));
					vo.setGender(rs.getString(6));
					vo.setBirthday(rs.getString(7));
					vo.setTel(rs.getString(8));
					vo.setAddress(rs.getString(9));
					vo.setEmail(rs.getString(10));
					vo.setContent(rs.getString("content"));
					vo.setLevel(rs.getInt(12));
					vo.setLastDate(rs.getString(13));
					vo.setSalt(rs.getString(14));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				rsClose();
			}
			return vo;
		}

		public int DeleteMember(int idx) {
			try {
				String sql  = "delete from member where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				pstmtClose();
			}
			return res;
		}
		
		
		
		
}
