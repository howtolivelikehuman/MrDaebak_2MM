package Service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.Member;
import Service.Action;
import Service.ActionForward;

public class SignUpService implements Action{
	ActionForward nextAction;
	boolean result;
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		String name = request.getParameter("user_name");
		String mobile = request.getParameter("user_mobile");
		String address= request.getParameter("user_address");
		
		Member dto = new Member();
		dto.setId(id);
		dto.setPw(password);
		dto.setName(name);
		dto.setAddress(address);
		dto.setMobile(mobile);
		
		MemberDAO dao = MemberDAO.getInstance();
		result = dao.insert(dto);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("signUpResultView.jsp?result="+result);
		nextAction.setRedirect(true);
		
		return nextAction;
		
	}
	
	public ActionForward CheckID(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		result = dao.isExistID(id);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("CheckIdView.jsp?id=" + id + "&result=" + result);
		nextAction.setRedirect(true);
		
		return nextAction;
	}
}
