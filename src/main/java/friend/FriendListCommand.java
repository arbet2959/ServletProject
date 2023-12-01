package friend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FriendListCommand implements FriendInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		
		FriendDAO dao = FriendDAO.getInstance();
		ArrayList<FriendVO> vos = dao.getFriendList(sMid);
		
		request.setAttribute("vos", vos);
	}

}
