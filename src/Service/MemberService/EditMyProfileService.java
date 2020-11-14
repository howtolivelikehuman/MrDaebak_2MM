package Service.MemberService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.Member;
import Service.Action;
import Service.ActionForward;

public class EditMyProfileService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward nextAction = new ActionForward();
		nextAction.setNextPath("");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward ReadMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = (String) request.getSession().getAttribute("Id");
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(id);
		
		request.setAttribute("member", member);
		
		ActionForward nextAction = new ActionForward();
		nextAction.setNextPath("modifyView.myprofile");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
}
