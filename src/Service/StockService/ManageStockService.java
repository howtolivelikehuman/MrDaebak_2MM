package Service.StockService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StockDAO;
import DTO.Member;
import DTO.Stock;
import Service.ActionForward;
import Service.Service;

public class ManageStockService extends Service{
	
	public ActionForward ListStock(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		try {
			StockDAO dao = StockDAO.getInstance();
			ArrayList<Stock> list = dao.getList();
			request.setAttribute("list", list);
		}catch (Exception e) {
			request.setAttribute("altmsg", "재고를 불러오는 도중 요류가 발생하였습니다.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("StockListView.stock");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	public ActionForward EditStock(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		nextAction = new ActionForward();
		nextAction.setNextPath("");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
}
