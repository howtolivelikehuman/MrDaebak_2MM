package Service.MemberService;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.Customer;
import DTO.Member;
import Service.Service;
import Service.ActionForward;

public class LogInService extends Service{
	public ActionForward LogIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
				
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(id, password);
		
		
		ActionForward nextAction = new ActionForward();
		if(member == null) {
			request.setAttribute("state", "failed");
			request.setAttribute("altmsg", "아이디 비밀번호를 다시 확인해주세요.");
			nextAction.setNextPath("LoginView.login");
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
			session.setAttribute("Type", member.getType());
			
			if(member instanceof Customer) {
				session.setAttribute("type", "Customer");
				request.setAttribute("altmsg", member.getName() + "님 환영합니다.");
				nextAction.setNextPath("Customer.login");
				nextAction.setRedirect(false);
			}
			else {
				session.setAttribute("type", "Employee");
				request.setAttribute("altmsg", member.getName() + "님 환영합니다.");
				nextAction.setNextPath("Employee.login");
				nextAction.setRedirect(false);
			}
			
		}		
		return nextAction;
		
	}
}
