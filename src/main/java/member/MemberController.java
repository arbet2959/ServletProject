package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("*.member")
public class MemberController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInterface command;
		String viewPage = "/WEB-INF/member/";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		
		if(com.equals("/signUp")) {
			viewPage += "signUp.jsp";
		}
		if(com.equals("/signUpProc")) {
			command = new SignUpProcCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		if(com.equals("/memberIdCheck")) {
			command = new MemberIdCheckCommand();
			command.execute(request, response);
			viewPage += "memberIdCheck.jsp";
			
		}
		if(com.equals("/memberNickCheck")) {
			command = new MemberNickCheckCommand();
			command.execute(request, response);
			viewPage += "memberNickCheck.jsp";
		}
		if(com.equals("/signIn")) {
			command = new signInCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/logout")) {
			command = new logoutCommand();
			command.execute(request, response);
			viewPage += "main.admin";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
}
