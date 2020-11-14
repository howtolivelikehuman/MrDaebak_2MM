package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Action;
import Service.ActionForward;
import Service.MemberService.SignUpService;

@WebServlet("*.signup") //얘는 끝에 이걸로끝나는애들 진공청소기마냥 다받음.

public class SignUpController extends HttpServlet{
	ActionForward nextAction = null; 
	Action action = null;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //요청된 uri 주소
		int Index = requestURI.lastIndexOf("/") + 1; //뒤에 빼기
		String requestPage = requestURI.substring(Index); // "xxx.signup" 꼴만 남게
		
		System.out.println("Controller 등장 " + requestPage);
		System.out.println("이거 실행");
		
		try {
			if(requestPage.equals("signUpView.signup")) { //회원가입 창요청
				nextAction = new ActionForward();
				nextAction.setNextPath("signUpView.jsp");
				nextAction.setRedirect(false);
			}
			
			else if(requestPage.equals("CheckIdLogic.signup")) { //아이디 확인
				action = new SignUpService();
				nextAction = ((SignUpService)action).CheckID(request, response);
			}
			
			else if(requestPage.equals("signUpLogic.signup")) { //회원가입 실행
				action = new SignUpService();
				nextAction = action.execute(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
