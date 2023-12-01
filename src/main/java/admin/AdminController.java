package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("*.admin")
public class AdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInterface command;
		String viewPage = "/WEB-INF/admin";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		String sLevel = String.valueOf(session.getAttribute("sLevel")) == null ? "-2" : String.valueOf(session.getAttribute("sLevel"));
		
		
		if(com.equals("/main")) {
			command = new MainCommand();
			command.execute(request, response);
			viewPage = "/WEB-INF/main/main.jsp";
		}
		else if(!sLevel.equals("0")) {
			request.getRequestDispatcher("/").forward(request, response);
			System.out.println("권한없는 사용자의 접근");
			return;
		}
		
		
		if(com.equals("/adminMemberList")) {
			command = new AdminMemberListCommand();
			command.execute(request, response);
			viewPage += "/adminMemberList.jsp";
		}
		if(com.equals("/adminMemberInfor")) {
			command = new AdminMemberInforCommand();
			command.execute(request, response);
			viewPage += "/adminMemberInfor.jsp";
		}
		if(com.equals("/adminMemberDeleteProc")) {
			command = new AdminMemberDeleteProcCommand();
			command.execute(request, response);
			return;
		}
		
		if(com.equals("/adminDiaryList")) {
			command = new AdminDiaryListCommand();
			command.execute(request, response);
			viewPage += "/adminDiaryList.jsp";
		}
		if(com.equals("/adminDiaryContent")) {
			command = new AdminDiaryContentCommand();
			command.execute(request, response);
			viewPage += "/adminDiaryContent.jsp";
		}
		
		if(com.equals("/diaryComplaintInput")) {
			command = new diaryComplaintInputCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/adminComplaintList")) {
			command = new AdminComplaintListCommand();
			command.execute(request, response);
			viewPage +="/adminComplaintList.jsp";
		}
		if(com.equals("/admindeleteComplaintProc")) {
			command = new AdminDeleteComplaintProcCommand();
			command.execute(request, response);
			viewPage +="/adminComplaintList.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
}
