package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiaryReplyRewriteCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int diaryIdx = request.getParameter("diaryIdx")==null ? 0 : Integer.parseInt(request.getParameter("diaryIdx"));
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String nickName = request.getParameter("nickName")==null ? "" : request.getParameter("nickName");
		String reWritecontent = request.getParameter("reWritecontent")==null ? "" : request.getParameter("reWritecontent");
		//int replyIdx = request.getParameter("replyIdx")==null ? 0 : Integer.parseInt(request.getParameter("replyIdx"));
		int ref = request.getParameter("ref")==null ? 0 : Integer.parseInt(request.getParameter("ref"));
		int re_step = request.getParameter("re_step")==null ? 0 : Integer.parseInt(request.getParameter("re_step"));
		int re_level = request.getParameter("re_level")==null ? 0 : Integer.parseInt(request.getParameter("re_level"));
		
		
		
		
		DiaryReplyVO replyVO = new DiaryReplyVO();
		replyVO.setDiary_Idx(diaryIdx);
		replyVO.setMid(mid);
		replyVO.setNickName(nickName);
		replyVO.setContent(reWritecontent);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		int res = dao.rewriteDiaryReply(replyVO,ref,re_step,re_level);
		
		response.getWriter().write(res+"");
		
	}

}
