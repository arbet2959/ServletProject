package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminComplaintListCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		AdminDAO dao = AdminDAO.getInstance();
		ArrayList<DiaryComplaintVO> vos = dao.getComplaintList();
		
		request.setAttribute("vos", vos);
	}

}
