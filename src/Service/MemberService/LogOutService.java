package Service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import Service.Service;
import Service.ActionForward;

public class LogOutService extends Service{
	public ActionForward LogOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");

				
		ActionForward nextAction = new ActionForward();
		
		HttpSession session = request.getSession();
		session.removeAttribute("Name");
		session.removeAttribute("Id");
		session.invalidate();
		
		nextAction.setNextPath("/MainView.jsp");
		nextAction.setRedirect(false);
		
		return nextAction;
		
	}
}
