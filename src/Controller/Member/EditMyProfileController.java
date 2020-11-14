package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Action;
import Service.ActionForward;
import Service.MemberService.EditMyProfileService;
import Service.MemberService.SignUpService;

@WebServlet("*.myprofile") //얘는 끝에 이걸로끝나는애들 진공청소기마냥 다받음.
public class EditMyProfileController extends HttpServlet{
	ActionForward nextAction = null; 
	Action action = null;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //요청된 uri 주소
		int Index = requestURI.lastIndexOf("/") + 1; //뒤에 빼기
		String requestPage = requestURI.substring(Index); // "xxx.signup" 꼴만 남게
		
		System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("Read.myprofile")) { //회원정보 받아오기 (로직)
				action = new EditMyProfileService();
				nextAction = ((EditMyProfileService)action).ReadMyProfile(request, response);
				
			}
			
			else if(requestPage.equals("modifyView.myprofile")) { //회원정보 받아오기 (뷰)
				nextAction = new ActionForward();
				nextAction.setNextPath("modifyView.jsp");
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
