package Service.MemberService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.Customer;
import DTO.Employee;
import DTO.Member;
import Service.ActionForward;
import Service.EncryptSHA256;
import Service.Service;

public class ManageMemberService extends Service{
	
	public ActionForward ListMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	

		request.setCharacterEncoding("UTF-8");
		
		try {
			int page = Integer.parseInt(request.getParameter("page"));
			
			MemberDAO dao = MemberDAO.getInstance();
			ArrayList<Member> list = dao.getList(page);
			int totalpages = dao.getTotalPage();
			
			request.setAttribute("totalpage", totalpages);
			request.setAttribute("currentpage", page);
			request.setAttribute("list", list);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "회원목록을 불러오는데 실피했습니다.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("MemberListView.manageMem");
			nextAction.setRedirect(false);
		}

		return nextAction;
	}
	
	public ActionForward ReadMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	

		request.setCharacterEncoding("UTF-8");
		
		try {

			int memNum = Integer.parseInt( request.getParameter("MemNo")); // url에 첨부된 글번호 파라미터 받기	
			MemberDAO dao = MemberDAO.getInstance();
			Member member = dao.select(memNum);
			if(member == null) {
				throw new Exception();
			}
			request.setAttribute("member", member);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "회원정보를 불러오는데 실피했습니다.");
			
		}finally {
			
			nextAction = new ActionForward();
			nextAction.setNextPath("MemberReadView.manageMem");
			nextAction.setRedirect(false);
		}
		return nextAction;		
	}
	
	public ActionForward EditMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		request.setCharacterEncoding("UTF-8");
		
		

		try {

			int no = Integer.parseInt(request.getParameter("user_no"));
			
			String password = request.getParameter("user_password");
			password = EncryptSHA256.SHA256(password);
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
			
			if(!dao.update(member)) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "회원정보 수정에 성공하였습니다.");
			}
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "수정도중 요류가 발생하였습니다.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.manageMem");
			nextAction.setRedirect(false);
		}
		
		return nextAction;
		}
	
	public ActionForward DeleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		
		try {
			int no = Integer.parseInt(request.getParameter("user_no"));
			MemberDAO dao = MemberDAO.getInstance();
			if(!dao.delete(no)) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "삭제에 성공하였습니다.");	
			}
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "삭제도중 요류가 발생하였습니다.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.manageMem");
			nextAction.setRedirect(false);	
		}
		
		return nextAction;
	}
}
