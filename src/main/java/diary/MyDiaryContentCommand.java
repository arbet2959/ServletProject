package diary;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyDiaryContentCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0:Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1:Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 3:Integer.parseInt(request.getParameter("pageSize"));
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		DiaryDAO dao = DiaryDAO.getInstance();
		DiaryVO vo = dao.getDiaryIdx(idx,true);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		
		int res = dao.getSearchGood(idx,sMid);
		boolean isGood = false;
		if(res==1) {
			isGood = true;
		}
		request.setAttribute("isGood", isGood);
		
		
		int replyPag = request.getParameter("replyPag")==null ? 1 : Integer.parseInt(request.getParameter("replyPag"));
		int replyPageSize = request.getParameter("replyPageSize")==null ? 5 : Integer.parseInt(request.getParameter("replyPageSize"));
		int totRecCnt = dao.getTotReplyCnt(idx);
		int totPage = (totRecCnt % replyPageSize)==0 ? (totRecCnt / replyPageSize) : (totRecCnt / replyPageSize) + 1 ;
		int startIndexNo = (replyPag - 1) * replyPageSize;
		int curScrStartNo = totRecCnt - startIndexNo;
		
		int blockSize = 3;
		int curBlock = (replyPag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;

		
		ArrayList<DiaryReplyVO> replyVOs = dao.getReplyList(idx,startIndexNo, replyPageSize);
 
		
		request.setAttribute("idx", idx);
		
		request.setAttribute("replyVOs", replyVOs);
		request.setAttribute("replyPag", replyPag);
		request.setAttribute("replyPageSize", replyPageSize);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
	}
	
	

}
