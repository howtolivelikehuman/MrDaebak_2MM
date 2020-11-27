package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDAO;
import DTO.Order;
import Service.ActionForward;
import Service.Service;

public class CheckMyOrderListService extends Service{
	public ActionForward ListMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			int memberno = (int)session.getAttribute("No");
			int page = Integer.parseInt(request.getParameter("page"));
			
			OrderDAO dao = OrderDAO.getInstance();
			ArrayList<Order> list = dao.getList(page, memberno);
			int totalpages = dao.getTotalPage(memberno);
			
			request.setAttribute("totalpage", totalpages);
			request.setAttribute("currentpage", page);
			request.setAttribute("list", list);
			
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "내 주문 리스트를 불러오는 도중 요류가 발생하였습니다.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("MyOrderListView.myorderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward ReadMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("UTF-8");
		
		try {
			int orderNum = Integer.parseInt( request.getParameter("OrdNo"));
			OrderDAO dao = OrderDAO.getInstance();
			Order order = dao.selectOrder(orderNum);
			order.setCart(dao.selectOrderedMenu(order.getNo()));
			request.setAttribute("order", order);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "내 주문 세부정보를 불러오는 도중 요류가 발생하였습니다.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("ReadMyOrderView.myorderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
