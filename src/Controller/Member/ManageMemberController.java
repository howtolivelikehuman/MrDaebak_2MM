package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import Service.MemberService.ManageMemberService;
import Service.ActionForward;

@WebServlet("*.manageMem")

public class ManageMemberController extends HttpServlet{
	ActionForward nextAction  = new ActionForward();
	ManageMemberService service = new ManageMemberService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //요청된 uri 주소
		int Index = requestURI.lastIndexOf("/") + 1; //뒤에 빼기
		String requestPage = requestURI.substring(Index); // "xxx.manageMem" 꼴만 남게
		System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("MemberList.manageMem")) { //회원 목록 - DB조회
				nextAction = service.ListMember(request, response);

			} else if(requestPage.equals("MemberListView.manageMem")) { //회원 목록 - View
				nextAction.setNextPath("MemberListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("MemberRead.manageMem")) { //회원 조회 - DB
				nextAction = service.ReadMember(request, response);
				
			} else if(requestPage.equals("MemberReadView.manageMem")) { //회원 조회 - View
				nextAction.setNextPath("EditMemberView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("MemberEdit.manageMem")) { //회원 수정 - DB
				nextAction = service.EditMember(request, response);
				
			} else if(requestPage.equals("MemberDelete.manageMem")) { //회원 삭제 -DB
				nextAction = service.DeleteMember(request, response);
				
			} else if (requestPage.equals("Check.manageMem")) {
				nextAction.setNextPath("MemberList.manageMem?page=1");
				nextAction.setRedirect(false);
				
			} else if (requestPage.equals("Result.manageMem")) {
				nextAction.setNextPath("MemberList.manageMem?page=1");
				nextAction.setRedirect(false);
			} 

			
			if(nextAction != null) { //리다이렉트 방식으로 nextPath
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath()); // nextPath 로 redirect
				} else { //forward방식으로 nextpath
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
