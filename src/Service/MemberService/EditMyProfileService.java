package Service.MemberService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import DAO.MemberDAO;
import DTO.Member;
import Service.ActionForward;
import Service.Service;

public class EditMyProfileService extends Service{
	
	public ActionForward UpdateMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("user_no"));
		String password = request.getParameter("user_password");
		String name = request.getParameter("user_name");
		String mobile = request.getParameter("user_mobile");
		String address= request.getParameter("user_address");
		
		Member dto = new Member();
		dto.setNo(no);
		dto.setPw(password);
		dto.setName(name);
		dto.setAddress(address);
		dto.setMobile(mobile);
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.update(dto);
		
		if(result) {
			request.setAttribute("altmsg", "수정에 성공하였습니다.");
		}else {
			request.setAttribute("altmsg", "수정도중 요류가 발생하였습니다.");
		}
		
		nextAction = new ActionForward();
		nextAction.setNextPath("Result.myprofile");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward DeleteMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("user_no"));
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.delete(no);
		
		nextAction = new ActionForward();
		if(result) {
			request.setAttribute("altmsg", "삭제에 성공하였습니다.");
			nextAction.setNextPath("Logic.logout");
		}else {
			request.setAttribute("altmsg", "삭제도중 요류가 발생하였습니다.");
			nextAction.setNextPath("Result.myprofile");
		}
		nextAction.setRedirect(false);	

		return nextAction;
	}
	
	public ActionForward ReadMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = (String) request.getSession().getAttribute("Id");
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(id);
		System.out.println(member.getNo());
		
		request.setAttribute("member", member);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("EditView.myprofile");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
}
