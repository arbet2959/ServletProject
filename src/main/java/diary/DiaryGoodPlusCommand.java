package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DiaryGoodPlusCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		HttpSession session = request.getSession();
		String sMid =(String) session.getAttribute("sMid");
		
		DiaryDAO dao = DiaryDAO.getInstance();
		int res = dao.insertGood(idx,sMid); // 반정규화해서 정합성 깨질수있음
		
		int res2 = dao.getSearchGood(idx,sMid);
		boolean isGood = false;
		if(res2==1) {
			isGood = true;
		}
		request.setAttribute("isGood", isGood);
		
		response.getWriter().write(res+"");
		
	}

}
