package notice.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.controller.Controller;

public class TestController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TestController 통과");
//		response.sendRedirect("/WEB-INF/testview/test.jsp");
		request.getRequestDispatcher("/WEB-INF/testview/test.jsp").forward(request, response);
	}

}
