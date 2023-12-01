package admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class diaryComplaintInputCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int diary_Idx = request.getParameter("diary_Idx") == null ? 0: Integer.parseInt(request.getParameter("diary_Idx"));
		String cpMid = request.getParameter("cpMid")==null ? "" : request.getParameter("cpMid");
		String cpContent = request.getParameter("cpContent")==null ? "" : request.getParameter("cpContent");
		
		AdminDAO dao = AdminDAO.getInstance();
		DiaryComplaintVO vo = new DiaryComplaintVO();
		
		vo.setDiary_Idx(diary_Idx);;
		vo.setCpMid(cpMid);
		vo.setCpContent(cpContent);
		
		int res = dao.setDiaryComplaintInput(vo);
				
		response.getWriter().write(res+"");

		
	}

}
