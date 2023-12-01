package admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

public class AdminMemberDeleteProcCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.DeleteMember(idx);
		response.getWriter().write(res+"");
		
	}

}
