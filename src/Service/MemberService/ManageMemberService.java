package Service.MemberService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.Member;
import Service.ActionForward;
import Service.Service;

public class ManageMemberService extends Service{
	
	public ActionForward ListMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		request.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<Member> list = dao.getList(page);
		int totalpages = dao.getTotalPage();
		
		request.setAttribute("totalpage", totalpages);
		request.setAttribute("currentpage", page);
		request.setAttribute("list", list);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("MemberListView.manageMem");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward ReadMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		nextAction = new ActionForward();
		nextAction.setNextPath("");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward EditMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
			
		request.setCharacterEncoding("UTF-8");
		nextAction = new ActionForward();
		nextAction.setNextPath("");
		nextAction.setRedirect(false);
		
		return nextAction;
		}
	
	public ActionForward DeleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		nextAction = new ActionForward();
		nextAction.setNextPath("");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public boolean DeleteOrderlist() {
		boolean result;
		return true;
	}
	
}
