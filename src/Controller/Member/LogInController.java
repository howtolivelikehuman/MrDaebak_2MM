package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.Action;
import Service.MemberService.LogInService;

@WebServlet("*.login") //얘는 끝에 이걸로끝나는애들 진공청소기마냥 다받음.

public class LogInController extends HttpServlet{
	ActionForward nextAction = null; 
	Action action = null;
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URI에서 사용자가 요청한 페이지 추출
		String requestURI = request.getRequestURI(); //요청된 uri 주소
		int Index = requestURI.lastIndexOf("/") + 1; //뒤에 빼기
		String requestPage = requestURI.substring(Index); // "xxx.login" 꼴만 남게
		
		System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("LoginView.login")) { //로그인 창요청
				nextAction = new ActionForward();
				nextAction.setNextPath("loginView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Logic.login")) { //로그인 논리 요청
				action = new LogInService();
				nextAction = action.execute(request, response);
			}
			else if(requestPage.equals("ResultView.login")) { //로그인 결과 창요청
				nextAction = new ActionForward();
				nextAction.setNextPath("loginResultView.jsp");
				nextAction.setRedirect(false);
			}
			
			System.out.println("다음 경로 " + nextAction.getNextPath());
			System.out.println(request.getAttribute("member"));
			
			if(nextAction != null) { //리다이렉트 방식으로 nextPath
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath()); // nextPath 로 redirect
				} else { //forward방식으로 nextpath
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
