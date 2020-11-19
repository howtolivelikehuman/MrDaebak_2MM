package Service.MemberService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.Customer;
import DTO.Employee;
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
		int memNum = Integer.parseInt( request.getParameter("MemNo")); // url에 첨부된 글번호 파라미터 받기
		
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(memNum);
		
		request.setAttribute("member", member);

		
		
		nextAction = new ActionForward();
		nextAction.setNextPath("MemberReadView.manageMem");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward EditMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		request.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("user_no"));
		String password = request.getParameter("user_password");
		String name = request.getParameter("user_name");
		String mobile = request.getParameter("user_mobile");
		String address= request.getParameter("user_address");
		int type = Integer.parseInt(request.getParameter("user_type"));
		
		Member member = null;
		//고객
		if(type == 0) {
			boolean isVip = Boolean.parseBoolean(request.getParameter("user_isVip"));
			
			member = new Customer();
			member.setNo(no);
			member.setPw(password);
			member.setName(name);
			member.setMobile(mobile);
			member.setAddress(address);
			
			((Customer)member).setVip(isVip);
		}
		else if (type == 1) {
			String position = request.getParameter("user_position");
			
			member = new Employee();
			member.setNo(no);
			member.setPw(password);
			member.setName(name);
			member.setMobile(mobile);
			member.setAddress(address);
			
			((Employee)member).setPosition(position);
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.update(member);
		
		if(result) {
			request.setAttribute("altmsg", "수정에 성공하였습니다.");
		}else {
			request.setAttribute("altmsg", "수정도중 요류가 발생하였습니다.");
		}
			
		nextAction = new ActionForward();
		nextAction.setNextPath("Result.manageMem");
		nextAction.setRedirect(false);
		
		return nextAction;
		}
	
	public ActionForward DeleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		int no = Integer.parseInt(request.getParameter("user_no"));
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.delete(no);
		
		nextAction = new ActionForward();
		
		if(result) {
			request.setAttribute("altmsg", "삭제에 성공하였습니다.");			
		}else {
			request.setAttribute("altmsg", "삭제도중 요류가 발생하였습니다.");
		}
		
		nextAction.setNextPath("Result.manageMem");
		nextAction.setRedirect(false);	

		return nextAction;
	}
	
	public boolean DeleteOrderlist() {
		boolean result;
		return true;
	}
	
}
