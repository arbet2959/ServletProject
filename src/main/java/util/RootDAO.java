package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class RootDAO {
	private RootDAO() {
		getSingleConnection();
	}
	
	Connection conn;
	

		private static class RootDAOHolder{
			private static final RootDAO INSTANCE = new RootDAO();
		}
		public static RootDAO getInstance() {
			return RootDAOHolder.INSTANCE;
		}
		
		private Connection getSingleConnection() {
			String url = "jdbc:mysql://localhost:3306/javaProject3";
			String user = "root";
			String password = "1234";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버검색실패");
			} catch (SQLException e) {
				System.out.println("DB연동실패");		
			}
			return conn;
		}
		
		public Connection getConnection() {
			return conn;
		}
	
	
}
