package friend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addFriendProcCommand implements FriendInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mid = request.getParameter("mid") == null ? "" : request.getParameter("mid");
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		
		FriendDAO dao = FriendDAO.getInstance();
		FriendVO vo = new FriendVO();
		vo.setMyId(sMid);
		vo.setFriendId(mid);
		if(sMid.equals(mid)) {
			response.getWriter().write("0");
			return;
		}
		int res = dao.insertFriend(vo);
		
		response.getWriter().write(res+"");
		
	}

}
