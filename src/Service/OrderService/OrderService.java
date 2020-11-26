package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.MemberDAO;
import DAO.OrderDAO;
import DTO.Member;
import DTO.Menu;
import DTO.MenuDetail;
import DTO.Order;
import DTO.OrderedMenu;
import DTO.Stock;
import DTO.Style;
import Service.ActionForward;
import Service.Service;

public class OrderService extends Service{
	public ActionForward GetOrderForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			OrderDAO dao = OrderDAO.getInstance();
			
			ArrayList<Menu> menulist = dao.getMenulist();
			ArrayList<Style> stylelist = dao.getStylelist();
			
			for(int i=0; i<menulist.size(); i++) {
				int mno = menulist.get(i).getNo();
				
				menulist.get(i).setAvailableStyle(dao.getAvailableStyle(mno));
				//메뉴에 해당하는 항목
				menulist.get(i).setMenuDetailList(dao.getMenuDetaillist(mno));
				//기타 항목
				menulist.get(i).setExtraDetailList(dao.getExtraDetaillist(mno));
			}
			HttpSession session = request.getSession();
			int memberno = (int)session.getAttribute("No");
			
			MemberDAO mdao = MemberDAO.getInstance();
			Member customer = mdao.select(memberno);
			
			request.setAttribute("member", customer);
			request.setAttribute("menulist", menulist);
			request.setAttribute("stylelist", stylelist);
			
		}catch (Exception e) {
			request.setAttribute("altmsg", "주문을 위한 정보를 불러오는 도중 요류가 발생하였습니다.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("OrderView.order");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward DoOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		boolean result = true;
		try {
			String str = (String) request.getParameter("order");
			Order order = new Order();
			ParseNewList(str, order);
			HttpSession session = request.getSession();
			
			if(session.getAttribute("No") != null) {
				order.setMemberNo((int)session.getAttribute("No"));
				order.setMemberID((String)session.getAttribute("Id"));
			}
			
			
			
			OrderDAO orderDAO = OrderDAO.getInstance();
			int orderno = orderDAO.InsertOrder(order);
			for(int i=0; i<order.getCart().size(); i++) {
				result = orderDAO.InsertOrderedMenu(order.getCart().get(i),orderno);
			}
			
			if(result == false) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "주문이 성공적으로 완료되었습니다.");
			}
		}catch (Exception e) {
			request.setAttribute("altmsg", "주문을 위한 정보를 불러오는 도중 요류가 발생하였습니다.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("ResultView.order");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public void ParseNewList(String s, Order order){
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(s);
		JsonArray jA;
		JsonArray jA2;
		JsonElement jel;
		JsonElement jel2;
		OrderedMenu md;
		
		order.setName(jsonObject.get("name").getAsString());
		order.setMobile(jsonObject.get("mobile").getAsString());
		order.setAddress(jsonObject.get("address").getAsString());
		order.setTotalPrice(jsonObject.get("totalPrice").getAsInt());
		order.setCardNum(jsonObject.get("cardNum").getAsString());
		order.setIsDiscounted(jsonObject.get("isVip").getAsBoolean());
		order.setDeliverydateTime(jsonObject.get("deliverydateTime").getAsString());
		order.setInfo(jsonObject.get("info").getAsString());
		
		jA = jsonObject.getAsJsonArray("cart");
		
		ArrayList<OrderedMenu> list = new ArrayList<OrderedMenu>();
		for(int i=0; i<jA.size(); i++) {
			jel = jA.get(i);
			md = new OrderedMenu();
			
			md.setMenu(jel.getAsJsonObject().get("menu").getAsString());
			md.setStyle(jel.getAsJsonObject().get("style").getAsString());
			md.setPrice(jel.getAsJsonObject().get("price").getAsInt());
			
			jA2= jel.getAsJsonObject().getAsJsonArray("orderedDetailList");
			
			String orderedDetailList = "";
			for(int j=0; j<jA2.size(); j++) {
				jel2 = jA2.get(j);
				orderedDetailList = orderedDetailList + " " +jel2.getAsJsonObject().get("name").getAsString() +" "+ jel2.getAsJsonObject().get("ea").getAsString();
			}
			md.setOrderedDetailList(orderedDetailList);
			list.add(md);
		}
		order.setCart(list);
	}
}
