package Service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.Service;
import Service.ActionForward;

public class LogOutService extends Service{
	public ActionForward LogOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("user_id");
			String password = request.getParameter("user_password");
			HttpSession session = request.getSession();
			session.removeAttribute("Name");
			session.removeAttribute("Id");
			session.invalidate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("/MainView.jsp");
			nextAction.setRedirect(false);
		}
		return nextAction;
		
	}
}
