package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import Service.ActionForward;
import Service.MemberService.EditMyProfileService;
import Service.MemberService.SignUpService;

@WebServlet("*.myprofile") //얘는 끝에 이걸로끝나는애들 진공청소기마냥 다받음.
public class EditMyProfileController extends HttpServlet{
	ActionForward nextAction = new ActionForward();
	EditMyProfileService service = new EditMyProfileService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); 
		int Index = requestURI.lastIndexOf("/") + 1; 
		String requestPage = requestURI.substring(Index); 
		
		//System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("Read.myprofile")) { //회원정보 받아오기 (로직)
				service = new EditMyProfileService();
				nextAction = service.ReadMyProfile(request, response);
			}
			else if(requestPage.equals("EditView.myprofile")) { //개인정보 받아오기 (뷰)
				nextAction.setNextPath("/Member/EditView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Check.myprofile")) { //회원정보 받아오기 (뷰)
				nextAction.setNextPath("/MainView.jsp"); //맞는 회원 뷰로 바꿔야함.
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Update.myprofile")) { //회원정보 업데이트
				nextAction = service.UpdateMyProfile(request, response);
			}
			else if(requestPage.equals("Delete.myprofile")) { //회원정보 삭제
				nextAction = service.DeleteMyProfile(request, response);
			}
			else if(requestPage.equals("Result.myprofile")) { //결과
				nextAction.setNextPath("/MainView.jsp"); 
				nextAction.setRedirect(false);
			}
			
			if(nextAction != null) { 
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath());
				} else {
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
