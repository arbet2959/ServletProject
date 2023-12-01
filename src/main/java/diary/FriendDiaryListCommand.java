package diary;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FriendDiaryListCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String sMid =(String) session.getAttribute("sMid");
	
	DiaryDAO dao = DiaryDAO.getInstance();
	
	// 페이징처리
	int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
	int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
	int totRecCnt = dao.getFriendRecCnt(sMid);
	int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1 ;
	int startIndexNo = (pag - 1) * pageSize;
	int curScrStartNo = totRecCnt - startIndexNo;
	
	int blockSize = 3;
	int curBlock = (pag - 1) / blockSize;
	int lastBlock = (totPage - 1) / blockSize;
	
	String now = LocalDate.now().toString();
	request.setAttribute("now", now);
	
	ArrayList<DiaryVO> vos = dao.getFriendDiaryList(startIndexNo, pageSize, sMid);

	
	
	request.setAttribute("vos", vos);
	request.setAttribute("pag", pag);
	request.setAttribute("pageSize", pageSize);
	request.setAttribute("totPage", totPage);
	request.setAttribute("curScrStartNo", curScrStartNo);
	request.setAttribute("blockSize", blockSize);
	request.setAttribute("curBlock", curBlock);
	request.setAttribute("lastBlock", lastBlock);
	
	request.setAttribute("sMid", sMid);

	}

}
