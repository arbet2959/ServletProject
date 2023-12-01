package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDiaryUpdateCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0:Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1:Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 3:Integer.parseInt(request.getParameter("pageSize"));
		
		DiaryDAO dao = DiaryDAO.getInstance();
		DiaryVO vo = dao.getDiaryIdx(idx,false);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("idx", idx);
		
		
	}

}
