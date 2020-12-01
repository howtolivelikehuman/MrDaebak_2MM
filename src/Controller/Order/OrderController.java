package Controller.Order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.OrderService.OrderService;

@WebServlet("*.order")
public class OrderController extends HttpServlet{
	ActionForward nextAction = new ActionForward();
	OrderService service = new OrderService();
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		int Index = requestURI.lastIndexOf("/") + 1;
		String requestPage = requestURI.substring(Index);
		System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("GetOrderForm.order")) { //메뉴가져오기- DB조회
				nextAction = service.GetOrderForm(request, response);

			} else if(requestPage.equals("OrderView.order")) { //주문  - View
				nextAction.setNextPath("/Order/OrderView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("DoOrder.order")) { //주문하기
				nextAction = service.DoOrder(request, response);
				
			}else if(requestPage.equals("ResultView.order")) { //재고 완료 뷰
				nextAction.setNextPath("/MainView.jsp");
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
