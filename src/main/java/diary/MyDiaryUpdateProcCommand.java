package diary;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyDiaryUpdateProcCommand implements DiaryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/diary");
		int maxSize = 1024*1024* 5;
		String encoding = "UTF-8";
		//객체생성과 동시에 업로드
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		//String orininalFileName = multipartRequest.getOriginalFileName("fName")==null ? "" : multipartRequest.getOriginalFileName("fName");
		String filesystemName = multipartRequest.getFilesystemName("fName")==null ? "" : multipartRequest.getFilesystemName("fName");

		HttpSession session = request.getSession();
		
		
		String title = multipartRequest.getParameter("title")==null ? "" : multipartRequest.getParameter("title");
		String photo = multipartRequest.getParameter("photo")==null ? "" : multipartRequest.getParameter("photo");
		String content = multipartRequest.getParameter("content")==null ? "" : multipartRequest.getParameter("content");
		int openLevel = multipartRequest.getParameter("openLevel")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("openLevel"));
		int idx = multipartRequest.getParameter("idx")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("idx"));
	
		DiaryVO vo = new DiaryVO();
		vo.setPhoto(filesystemName);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setOpenLevel(openLevel);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		//System.out.println(vo.toString());
		//System.out.println(idx);
		int res = dao.updateDiary(vo,idx);
		
		if(res==1) {
				
			File file = new File(realPath+"/"+photo);
			if(file.exists()) {
				file.delete();
			}
			
			request.setAttribute("msg", "수정성공");
			request.setAttribute("url", "myDiaryList.diary");
			
		}
		if(res==0) {
			request.setAttribute("msg", "수정실패");
			request.setAttribute("url", "myDiaryList.diary");
		}
		
	}

}
