package Service.StockService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		request.setCharacterEncoding("UTF-8");
		try {
			//parsing
			String str = request.getParameter("stockArray");
			str = str.substring(str.indexOf(':')+1, str.length()-1);
			System.out.println(str);		
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = (JsonArray) jsonParser.parse(str);
			JsonObject object;
			Stock s;
			
			ArrayList<Stock> newlist = new ArrayList<Stock>();
			ArrayList<Stock> pastlist = new ArrayList<Stock>();
			
			for (int i = 0; i < jsonArray.size(); i++) {
				object = (JsonObject) jsonArray.get(i);
				s = new Stock();
				s.setNo(object.get("no").getAsInt());
				s.setName(object.get("name").getAsString());
				s.setAmount(object.get("amount").getAsInt());
				s.setPrice(object.get("price").getAsInt());
				s.setNextSupplyDate(object.get("nextSupplyDate").getAsString());
				newlist.add(s);
				System.out.println(s.getNo() + " " + s.getName() + " " + s.getPrice() + " " + s.getNextSupplyDate());
			}
			
			StockDAO dao = StockDAO.getInstance();
			pastlist = dao.getList();
			
			int size=pastlist.size();
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("altmsg", "재고를 업데이트 하는 도중 요류가 발생하였습니다.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("StockList.stock");
			nextAction.setRedirect(false);
		}
		
		return nextAction;
	}
}
