package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyDiaryDeleteProcCommand implements DiaryInterface {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? -2 : Integer.parseInt(request.getParameter("idx"));
		
		DiaryDAO dao = DiaryDAO.getInstance();
		int res = dao.deleteDiary(idx);
		response.getWriter().write(res+"");
	}

}
