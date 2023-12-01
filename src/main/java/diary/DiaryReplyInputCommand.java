package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiaryReplyInputCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diaryIdx = request.getParameter("diaryIdx")==null ? 0 : Integer.parseInt(request.getParameter("diaryIdx"));
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String nickName = request.getParameter("nickName")==null ? "" : request.getParameter("nickName");
		String replyContent = request.getParameter("replyContent")==null ? "" : request.getParameter("replyContent");
		
		DiaryReplyVO replyVO = new DiaryReplyVO();
		replyVO.setDiary_Idx(diaryIdx);
		replyVO.setMid(mid);
		replyVO.setNickName(nickName);
		replyVO.setContent(replyContent);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		int res = dao.insertDiaryReply(replyVO);
		
		response.getWriter().write(res+"");
		
	}

}
