package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.EncryptSha256Util;

public class SignUpProcCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "":request.getParameter("mid"); 
		String pwd = request.getParameter("pwd")==null ? "":request.getParameter("pwd"); 
		String nickName = request.getParameter("nickName")==null ? "":request.getParameter("nickName"); 
		String name = request.getParameter("name")==null ? "":request.getParameter("name"); 
		String gender = request.getParameter("gender")==null ? "":request.getParameter("gender"); 
		String birthday = request.getParameter("birthday")==null ? "":request.getParameter("birthday"); 
		String tel = request.getParameter("tel")==null ? "":request.getParameter("tel"); 
		String address = request.getParameter("address")==null ? "":request.getParameter("address"); 
		String email = request.getParameter("email")==null ? "":request.getParameter("email"); 
		String content = request.getParameter("content")==null ? "":request.getParameter("content"); 
		
		String salt=EncryptSha256Util.getSalt();
		pwd = EncryptSha256Util.getSha256WithSalt(pwd, salt);
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO vo = dao.getMemberMidCheck(mid);
		if(vo.getMid() != null) {
			request.setAttribute("msg", "이미 사용중인 아이디 입니다.");
			request.setAttribute("url", "signUp.member");
			return;
		}
		
		vo = dao.getMemberNickCheck(nickName);
		if(vo.getNickName() != null) {
			request.setAttribute("msg", "이미 사용중인 닉네임 입니다.");
			request.setAttribute("url", "signUp.member");
			return;
		}
		
		vo = new MemberVO();
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setContent(content);
		vo.setSalt(salt);
		System.out.println(vo.toString());
		int res = dao.signUpMember(vo);
		
		if(res ==1) {
			request.setAttribute("msg", "가입성공 로그인하세요");
			request.setAttribute("url", "main.admin");
		}
		if(res ==0) {
			request.setAttribute("msg", "가입실패");
			request.setAttribute("url", "main.admin");
		}
	}

}
