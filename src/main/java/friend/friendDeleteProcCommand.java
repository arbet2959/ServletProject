package friend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class friendDeleteProcCommand implements FriendInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		String friendId = request.getParameter("friendId") == null ? "":request.getParameter("friendId");
		
		FriendDAO dao = FriendDAO.getInstance();
		int res = dao.deleteFriend(sMid,friendId);
		
		
		response.getWriter().write(res+"");
		
	}

}
