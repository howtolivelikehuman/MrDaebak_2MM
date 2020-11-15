package Service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.Customer;
import DTO.Member;
import Service.Action;
import Service.ActionForward;

public class LogInService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
				
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(id, password);
		
		
		ActionForward nextAction = new ActionForward();
		if(member == null ) {
			request.setAttribute("state", "failed");
			nextAction.setNextPath("ResultView.login");
			nextAction.setRedirect(false);
		}
		else {
			request.setAttribute("state", "success");
			request.setAttribute("member", member);
			System.out.println(member.getName() + " "+ member.getId());
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3600);
			session.setAttribute("Name", member.getName());
			session.setAttribute("Id",member.getId());
			
			if(member instanceof Customer) {
				session.setAttribute("type", "Customer");
				
				nextAction.setNextPath("Customer.login");
				nextAction.setRedirect(false);
			}
			else {
				session.setAttribute("type", "Employee");
				
				nextAction.setNextPath("Employee.login");
				nextAction.setRedirect(false);
			}
			
		}
		
//		ActionForward nextAction = new ActionForward();
//		nextAction.setNextPath("ResultView.login");
//		nextAction.setRedirect(false);
//		
		return nextAction;
		
	}
}
