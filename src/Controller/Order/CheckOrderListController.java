package Controller.Order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.OrderService.CheckOrderListService;

@WebServlet("*.orderlist")
public class CheckOrderListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ActionForward nextAction  = new ActionForward();
	CheckOrderListService service = new CheckOrderListService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		int Index = requestURI.lastIndexOf("/") + 1;
		String requestPage = requestURI.substring(Index);
		System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("OrderList.orderlist")) { //주문 목록 - DB조회
				nextAction = service.ListOrder(request, response);

			} else if(requestPage.equals("OrderListView.orderlist")) { //주문 목록 - View
				nextAction.setNextPath("/Order/OrderListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("ReadOrder.orderlist")) { //주문 조회 - DB
				nextAction = service.ReadOrder(request, response);
				
			}
			else if(requestPage.equals("ReadOrderView.orderlist")) { //주문 조회 - View
				nextAction.setNextPath("/Order/OrderDetailView.jsp");
				nextAction.setRedirect(false);
				
			}else if(requestPage.equals("UpdateOrder.orderlist")) { //주문 수정 - DB
				nextAction = service.UpdateOrder(request, response);
				
			} else if(requestPage.equals("DeleteOrder.orderlist")) { //주문 삭제 - DB
				nextAction = service.DeleteOrder(request, response);
				
			} else if(requestPage.equals("Result.orderlist")) { //결과
				nextAction.setNextPath("/Order/OrderList.orderlist?page=1");
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response);
	}
}
