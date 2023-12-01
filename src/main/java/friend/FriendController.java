package friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.MainCommand;
@WebServlet("*.friend")
public class FriendController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendInterface command;
		String viewPage = "/WEB-INF/friend";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		HttpSession session = request.getSession();
		String sLevel = String.valueOf(session.getAttribute("sLevel")) == null ? "-2" : String.valueOf(session.getAttribute("sLevel"));
		

		if(!sLevel.equals("0")) {
			request.getRequestDispatcher("/").forward(request, response);
			System.out.println("권한없는 사용자의 접근");
			return;
		}

		if(com.equals("/addfriendProc")) {
			command = new addFriendProcCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/friendList")) {
			command = new FriendListCommand();
			command.execute(request, response);
			viewPage += "/friendList.jsp";
		}
		if(com.equals("/friendDeleteProc")) {
			command = new friendDeleteProcCommand();
			command.execute(request, response);
			return;
		}
		
//		if(com.equals("/adminMemberList")) {
//			command = new AdminMemberListCommand();
//			command.execute(request, response);
//			viewPage += "/friendList.jsp";
//		}
//		if(com.equals("/adminMemberInfor")) {
//			command = new AdminMemberInforCommand();
//			command.execute(request, response);
//			viewPage += "/adminMemberInfor.jsp";
//		}
//		if(com.equals("/adminMemberDeleteProc")) {
//			command = new AdminMemberDeleteProcCommand();
//			command.execute(request, response);
//			return;
//		}
//		
//		if(com.equals("/adminDiaryList")) {
//			command = new AdminDiaryListCommand();
//			command.execute(request, response);
//			viewPage += "/adminDiaryList.jsp";
//		}
//		if(com.equals("/adminDiaryContent")) {
//			command = new AdminDiaryContentCommand();
//			command.execute(request, response);
//			viewPage += "/adminDiaryContent.jsp";
//		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
}
