package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDiaryRewriteCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx") == null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int ref= request.getParameter("ref") == null ? 0 : Integer.parseInt(request.getParameter("ref"));
		int re_step = request.getParameter("re_step") == null ? 0 : Integer.parseInt(request.getParameter("re_step"));
		int re_level = request.getParameter("re_level") == null ? 0 : Integer.parseInt(request.getParameter("re_level"));
	
		request.setAttribute("idx", idx);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
	}

}
