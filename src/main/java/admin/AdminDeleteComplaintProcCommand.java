package admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.DiaryDAO;

public class AdminDeleteComplaintProcCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idx = request.getParameter("idx")==null ? -2 : Integer.parseInt(request.getParameter("idx"));
		
		AdminDAO dao = AdminDAO.getInstance();
		int res = dao.deleteComplaint(idx);
		response.getWriter().write(res+"");
	}

}
