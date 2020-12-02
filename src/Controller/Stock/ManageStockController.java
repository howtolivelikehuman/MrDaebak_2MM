package Controller.Stock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.StockService.ManageStockService;

@WebServlet("*.stock")
public class ManageStockController extends HttpServlet{
	ActionForward nextAction = new ActionForward();
	ManageStockService service = new ManageStockService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String requestURI = request.getRequestURI(); //요청된 uri 주소
		int Index = requestURI.lastIndexOf("/") + 1; //뒤에 빼기
		String requestPage = requestURI.substring(Index); // "xxx.stock" 꼴만 남게
		//System.out.println("Controller 등장 " + requestPage);
		
		try {
			if(requestPage.equals("StockList.stock")) { //재고 목록 - DB조회
				nextAction = service.ListStock(request, response);

			} else if(requestPage.equals("StockListView.stock")) { //재고 목록 - View
				nextAction.setNextPath("/Stock/StockListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("StockEdit.stock")) { //재고 수정 -DB
				nextAction = service.EditStock(request, response);
				
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
}
