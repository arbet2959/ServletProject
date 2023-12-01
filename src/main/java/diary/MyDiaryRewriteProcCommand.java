package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyDiaryRewriteProcCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/diary");
		int maxSize = 1024*1024* 5;
		String encoding = "UTF-8";
		//객체생성과 동시에 업로드
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		//String orininalFileName = multipartRequest.getOriginalFileName("fName")==null ? "" : multipartRequest.getOriginalFileName("fName");
		String filesystemName = multipartRequest.getFilesystemName("fName")==null ? "" : multipartRequest.getFilesystemName("fName");
//		if(filesystemName.equals("")) {
//			filesystemName="noimage.jpg";
//		}
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");
		String nickName = (String) session.getAttribute("sNickName");
		
		
		String title = multipartRequest.getParameter("title")==null ? "" : multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content")==null ? "" : multipartRequest.getParameter("content");
		String hostIp = multipartRequest.getParameter("hostIp")==null ? "" : multipartRequest.getParameter("hostIp");
		int openLevel = multipartRequest.getParameter("openLevel")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("openLevel"));
		
		int ref = multipartRequest.getParameter("ref")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("ref"));
		int re_step = multipartRequest.getParameter("re_step")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("re_step"));
		int re_level = multipartRequest.getParameter("re_level")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("re_level"));
		
	
		DiaryVO vo = new DiaryVO();
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setPhoto(filesystemName);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		vo.setOpenLevel(openLevel);
		// 부모글의 값
		vo.setRef(ref);
		vo.setRe_step(re_step);
		vo.setRe_level(re_level);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		int res = dao.RewriteDiary(vo);
		
	}

}
