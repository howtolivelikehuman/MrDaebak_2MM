package Service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.Employee;
import DTO.Member;
import Service.Service;
import Service.ActionForward;

public class SignUpService extends Service{

	
	public ActionForward SignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");		
		try {
			String id = request.getParameter("user_id");
			String password = request.getParameter("user_password");
			String name = request.getParameter("user_name");
			String mobile = request.getParameter("user_mobile");
			String address= request.getParameter("user_address");
			String code = request.getParameter("user_code");
			
			Member dto;
			
			if(code.contentEquals("2mm") ) {
				 dto = new Employee();
			}else {
				 dto = new Member();
			}
			
			dto.setId(id);
			dto.setPw(password);
			dto.setName(name);
			dto.setAddress(address);
			dto.setMobile(mobile);
			
			
			MemberDAO dao = MemberDAO.getInstance();
			if(!dao.insert(dto)) {
				throw new Exception();
			}
			else {
				request.setAttribute("altmsg", "회원가입에 성공하였습니다.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("altmsg", "회원가입에 실패하였습니다.");
		}
		finally {

			nextAction = new ActionForward();
			nextAction.setNextPath("Result.signup");
			nextAction.setRedirect(false);
			
		}
		return nextAction;
	}
	
	public ActionForward CheckID(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.isExistID(id);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("/Member/CheckIdView.jsp?id=" + id + "&result=" + result);
		nextAction.setRedirect(false);
		
		return nextAction;
	}
}
