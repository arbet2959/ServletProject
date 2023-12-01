package friend;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diary.DiaryReplyVO;
import diary.DiaryVO;
import util.RootDAO;


public class FriendDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int res=0;
	private String sql;
	FriendVO vo;
		private FriendDAO() {
			conn = RootDAO.getInstance().getConnection();
		}
		
		private static class DAOHolder{
			private static final FriendDAO INSTANCE = new FriendDAO();
		}
		public static FriendDAO getInstance() {
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

		public int insertFriend(FriendVO vo) {
			try {
				sql = "insert into DiaryFriend values(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMyId());
				pstmt.setString(2, vo.getFriendId());
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				res=0;
			}finally {
				pstmtClose();
			}
			return res;
		}

		public ArrayList<FriendVO> getFriendList(String mid) {
			ArrayList<FriendVO> vos = new ArrayList<FriendVO>(); 
			try {
				sql = "select * from DiaryFriend where myId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					FriendVO vo = new FriendVO();
					vo.setMyId(rs.getString(1));
					vo.setFriendId(rs.getString(2));
					vos.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return vos;
		}

		public int deleteFriend(String sMid, String friendId) {
			try {
				sql = "delete from diaryFriend where myId = ? and friendId = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sMid);
				pstmt.setString(2, friendId);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				pstmtClose();
			}
			return res;
		}
		
		
		
		

		
		

		

		

		
		
		
}
