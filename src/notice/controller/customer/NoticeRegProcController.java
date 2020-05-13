package notice.controller.customer;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.controller.Controller;
import notice.dao.NoticeDao;
import notice.vo.Notice;

public class NoticeRegProcController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("NoticeRegProcController 신호");
//		String path="customer/upload";
//		ServletContext ctx= request.getServletContext();
//		path=ctx.getRealPath(path);
//		System.out.println("realpath : "+path);
		
		String path=this.getClass().getResource("").getPath();
		path=path.substring(1,path.indexOf(".metadata"))+
				"jspWebm19_mvc2/WebContent/customer/upload";
		
		System.out.println("getpath : " + path);
		
		MultipartRequest req=new MultipartRequest(request, path, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
//		String title=request.getParameter("title");
//		String content=request.getParameter("content");
//		String file=request.getParameter("file");

		String title=req.getParameter("title");
		String uid=req.getParameter("uid");
		String content=req.getParameter("content");
		String file=req.getFilesystemName("file");
		String orgFile=req.getOriginalFileName("file");

		System.out.println("title : "+title);
		System.out.println("uid : "+uid);
		System.out.println("file : "+file);
		System.out.println("orgfilesrc : "+orgFile);
		
		
		Notice n=new Notice();
		n.setTitle(title);
		n.setWriter(uid);
		n.setContent(content);
		n.setFilesrc(file);
		n.setOrgfilesrc(orgFile);
		
		NoticeDao dao=new NoticeDao();
		int af= dao.insert(n);
		
		PrintWriter out=response.getWriter();
		//목록으로 이동
		if(af>0)
		response.sendRedirect("notice.do");
		else out.println("글쓰기 오류");

	}

}
