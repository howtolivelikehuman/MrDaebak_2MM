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
			
			String str2 = request.getParameter("deletestockArray");
			str2 = str2.substring(str2.indexOf(':')+1, str2.length()-1);
			
			ArrayList<Stock> newlist = ParseNewList(str);
			int[] deletelist = ParseDeleteList(str2);
			
			
			boolean result = true;
			boolean result2 = true;
			
			StockDAO dao = StockDAO.getInstance();
			
			if(deletelist != null) {
				//삭제 먼저 수행
				result = dao.delete(deletelist);
				for(int i=0; i<deletelist.length; i++) {
					System.out.println(deletelist[i]);
				}
			}
				
			
		
			if(newlist != null) {
				for(int i=0; i<newlist.size(); i++) {
					if(newlist.get(i).getName().length() > 0) //적어도 이름은 있나 한번 더 체크
						System.out.println(newlist.get(i).getNo() + " " + newlist.get(i).getName());
						result2 = dao.merge(newlist.get(i));
				}
			}
			
			
			if(result == false || result2 == false) {
				request.setAttribute("altmsg", "재고를 업데이트 하는 도중 요류가 발생하였습니다.");
			}else {
				request.setAttribute("altmsg", "재고를 성공적으로 업데이트하였습니다.");
			}
			
			
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
	public ArrayList<Stock> ParseNewList(String s){
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(s);
		JsonObject object;
		ArrayList<Stock> list = new ArrayList<Stock>();
		Stock stock;
		
		for (int i = 0; i < jsonArray.size(); i++) {
			object = (JsonObject) jsonArray.get(i);
			stock = new Stock();
			stock.setNo(object.get("no").getAsInt());
			stock.setName(object.get("name").getAsString());
			stock.setAmount(object.get("amount").getAsInt());
			stock.setPrice(object.get("price").getAsInt());
			stock.setNextSupplyDate(object.get("nextSupplyDate").getAsString());
			list.add(stock);
		}
		return list.size() > 0 ? list : null;
	}
	
	public int[] ParseDeleteList(String s){
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(s);
		JsonObject object;
		if(jsonArray.size() > 0) {
			int [] list = new int[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				object = (JsonObject) jsonArray.get(i);
				list[i] = object.get("no").getAsInt();
			}
			return list;
		}
		return null;
	}
}
