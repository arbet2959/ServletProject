package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("*.diary")
public class DiaryController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiaryInterface command;
		String viewPage = "/WEB-INF/diary";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		 
		if(com.equals("/myDiaryList")) {
			command = new MyDiaryListCommand();
			command.execute(request, response);
			viewPage += "/myDiaryList.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
}
