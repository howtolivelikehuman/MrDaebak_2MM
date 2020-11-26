package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import DTO.Order;
import Service.ActionForward;
import Service.Service;

public class CheckOrderListService extends Service{
	public ActionForward ListOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			int page = Integer.parseInt(request.getParameter("page"));
			OrderDAO dao = OrderDAO.getInstance();
			ArrayList<Order> list = dao.getList(page);
			int totalpages = dao.getTotalPage();
			
			request.setAttribute("totalpage", totalpages);
			request.setAttribute("currentpage", page);
			request.setAttribute("list", list);
			
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "주문 리스트를 불러오는 도중 요류가 발생하였습니다.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("OrderListView.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	public ActionForward ReadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			int orderNum = Integer.parseInt( request.getParameter("OrdNo"));
			OrderDAO dao = OrderDAO.getInstance();
			Order order = dao.selectOrder(orderNum);
			order.setCart(dao.selectOrderedMenu(order.getNo()));
			request.setAttribute("order", order);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "주문 세부정보를 불러오는 도중 요류가 발생하였습니다.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("ReadOrderView.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward DeleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			System.out.println(request.getParameter("order_no"));
			int orderNum = Integer.parseInt(request.getParameter("order_no")); // url에 첨부된 글번호 파라미터 받기
			OrderDAO dao = OrderDAO.getInstance();
			boolean result = dao.delete(orderNum);
			
			if(!result) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "주문내역 삭제에 성공하였습니다.");
			}
		}catch(Exception e) {
			request.setAttribute("altmsg", "주문내역 삭제도중 오류가 발생하였습니다.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward UpdateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			int orderNum = Integer.parseInt(request.getParameter("order_no"));
			int status = Integer.parseInt(request.getParameter("order_status"));
			OrderDAO dao = OrderDAO.getInstance();
			boolean result = dao.update(orderNum, status);
			
			if(!result) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "주문 상태 수정에 성공하였습니다.");
			}
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "주문 상태 수정 도중 오류가 발생하였습니다.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
