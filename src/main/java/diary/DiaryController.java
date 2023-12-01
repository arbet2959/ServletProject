package diary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("*.diary")
public class DiaryController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiaryInterface command;
		String viewPage = "/WEB-INF/diary";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		String sLevel = String.valueOf(session.getAttribute("sLevel")) ==null ? "-2" : String.valueOf(session.getAttribute("sLevel"));
		int sLevelInt = Integer.parseInt(sLevel);
		
		if(com.equals("/openDiaryList")) {
			command = new OpenDiaryListCommand();
			command.execute(request, response);
			viewPage += "/openDiaryList.jsp";
		}
		else if(com.equals("/openDiaryContent")) {
			command = new OpenDiaryContentCommand();
			command.execute(request, response);
			viewPage += "/openDiaryContent.jsp";
		}
		
		else if(sLevelInt<0) {
			//command = new diaryPermissionBlockProcCommand();
			//command.execute(request, response);
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		
		if(com.equals("/openDiaryRewrite")) {
			command = new OpenRewriteCommand();
			command.execute(request, response);
			viewPage += "/openDiaryRewrite.jsp";
		}
		if(com.equals("/openDiaryReWriteProc")) {
			command = new OpenRewriteProcCommand();
			command.execute(request, response);
			viewPage += "/openDiaryList.diary";
		}
		
		
		
		
		if(com.equals("/myDiaryList")) {
			command = new MyDiaryListCommand();
			command.execute(request, response);
			viewPage += "/myDiaryList.jsp";
		}
		if(com.equals("/myDiaryInput")) {
			//command = new MyDiaryInputCommand();
			//command.execute(request, response);
			viewPage += "/myDiaryInput.jsp";
		}
		if(com.equals("/myDiaryInputProc")) {
			command = new MyDiaryInputProcCommand();
			command.execute(request, response);
			viewPage += "/myDiaryList.diary";
		}
		if(com.equals("/myDiaryContent")) {
			command = new MyDiaryContentCommand();
			command.execute(request, response);
			viewPage += "/myDiaryContent.jsp";
		}
		if(com.equals("/myDiaryUpdate")) {
			command = new MyDiaryUpdateCommand();
			command.execute(request, response);
			viewPage += "/myDiaryUpdate.jsp";
		}
		if(com.equals("/myDiaryUpdateProc")) {
			command = new MyDiaryUpdateProcCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		if(com.equals("/myDiaryRewrite")) {
			command = new MyDiaryRewriteCommand();
			command.execute(request, response);
			viewPage += "/myDiaryRewrite.jsp";
		}
		if(com.equals("/myDiaryReWriteProc")) {
			command = new MyDiaryRewriteProcCommand();
			command.execute(request, response);
			viewPage += "/myDiaryList.diary";
		}
		if(com.equals("/myDiaryDeleteProc")) {
			command = new MyDiaryDeleteProcCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/diaryGoodPlus")) {
			command = new DiaryGoodPlusCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/diaryGoodMinus")) {
			command = new DiaryGoodMinusCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/diaryReplyDelete")) {
			command = new DiaryReplyDeleteCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/diaryReplyInput")) {
			command = new DiaryReplyInputCommand();
			command.execute(request, response);
			return;
		}
		if(com.equals("/diaryReplyRewrite")) {
			command = new DiaryReplyRewriteCommand();
			command.execute(request, response);
			return;
		}
		
		if(com.equals("/friendDiaryList")) {
			command = new FriendDiaryListCommand();
			command.execute(request, response);
			viewPage += "/friendDiaryList.jsp";
		}
		if(com.equals("/friendDiaryContent")) {
			command = new FriendDiaryContentCommand();
			command.execute(request, response);
			viewPage += "/friendDiaryContent.jsp";
		}
		if(com.equals("/friendDiaryRewrite")) {
			command = new FriendRewriteCommand();
			command.execute(request, response);
			viewPage += "/friendDiaryRewrite.jsp";
		}
		if(com.equals("/friendDiaryReWriteProc")) {
			command = new FriendRewriteProcCommand();
			command.execute(request, response);
			viewPage += "/friendDiaryList.diary";
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
	
}
