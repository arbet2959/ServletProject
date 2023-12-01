package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.EncryptSha256Util;

public class signInCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "":request.getParameter("mid"); 
		String pwd = request.getParameter("pwd")==null ? "":request.getParameter("pwd"); 
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		if(vo.getMid()==null) {
			response.getWriter().write("idfault");
			return;
		}
		String enteredPwd=EncryptSha256Util.getSha256WithSalt(pwd, vo.getSalt());
		if(!vo.getPwd().equals(enteredPwd)) {
			response.getWriter().write("pwdfault");
			return;
		}
		
		System.out.println(vo.getMid());

		HttpSession session = request.getSession();
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sMid", vo.getMid());
		session.setAttribute("sLevel", vo.getLevel());
		
		response.getWriter().write("1");
	}

}
