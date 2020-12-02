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
import Service.EncryptSHA256;

public class LogInService extends Service{
	public ActionForward LogIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			String id = request.getParameter("user_id");
			String password = EncryptSHA256.SHA256(request.getParameter("user_password"));
					
			MemberDAO dao = MemberDAO.getInstance();
			Member member = dao.select(id, password);
			
			if(member == null) {
				throw new Exception();
			}
			else {
				request.setAttribute("member", member);
				System.out.println(member.getName() + " "+ member.getId());
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(3600);				
				session.setAttribute("Name", member.getName());
				session.setAttribute("Id",member.getId());
				session.setAttribute("No",member.getNo());
				session.setAttribute("Type", member.getType());
				request.setAttribute("altmsg", member.getName() + "님 환영합니다.");
				
				nextAction = new ActionForward();
				nextAction.setNextPath("Result.login");
				nextAction.setRedirect(false);
			}		
		}
		catch (Exception e) {
			request.setAttribute("altmsg", "아이디 비밀번호를 다시 확인해주세요.");
			nextAction = new ActionForward();
			nextAction.setNextPath("LoginView.login");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
