package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Menu;
import DTO.MenuDetail;
import DTO.Order;
import DTO.OrderedMenu;
import DTO.Style;

public class OrderDAO {
	private static OrderDAO dao;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	static private DataSource ds;
	
	
	static {
		try {
			System.out.println("start DBCP!");
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//싱글톤 패턴
	private OrderDAO() {}
	public synchronized static OrderDAO getInstance() {
		if(dao == null) {
			dao = new OrderDAO();
		}
		return dao;
	}

	//close 하는 메소드.
	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) { rs.close();}
			if(con != null) {con.close(); }
			if(ps != null) {ps.close(); }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void close(Connection con, PreparedStatement ps) {
		close(con,ps,null);
	}
			
	public ArrayList<Menu> getMenulist()throws Exception{
		ArrayList<Menu> list = new ArrayList<Menu>();
		Menu menu = null;
		
		sql = "SELECT no, name, available, price, info "
				+ " FROM menu ";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				menu = new Menu();
				menu.setNo(rs.getInt("no"));
				menu.setName(rs.getString("name"));
				menu.setAvailable(rs.getInt("available"));
				menu.setPrice(rs.getInt("price"));
				menu.setInfo(rs.getString("info"));
				list.add(menu);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<Style> getStylelist()throws Exception{
		ArrayList<Style> list = new ArrayList<Style>();
		Style style = null;
		
		sql = "SELECT no, name, price, info "
				+ " FROM style ";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				style = new Style();
				style.setNo(rs.getInt("no"));
				style.setName(rs.getString("name"));
				style.setPrice(rs.getInt("price"));
				style.setInfo(rs.getString("info"));
				list.add(style);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<MenuDetail> getMenuDetaillist(int menuNo)throws Exception{
		ArrayList<MenuDetail> list = new ArrayList<MenuDetail>();
		MenuDetail menuDetail = null;
		
		sql = "select MENU.NO, MENUWITHSTOCK.ea, STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO AS STOCKNO "
				+ "FROM MENU, menuwithstock, STOCK "
				+ "WHERE MENU.NO  = ? "
				+ "AND menuwithstock.menuno = MENU.NO "
				+ "AND menuwithstock.stockno = STOCK.NO "
				+ "AND STOCK.NO = STOCK.NO";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				menuDetail = new MenuDetail();
				menuDetail.setMenuNo(rs.getInt("no"));
				menuDetail.setEa(rs.getInt("ea"));
				menuDetail.setName(rs.getString("name"));
				menuDetail.setPrice(rs.getInt("price"));
				menuDetail.setStockAmount(rs.getInt("amount"));
				menuDetail.setStockNo(rs.getInt("stockno"));
				list.add(menuDetail);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<MenuDetail> getExtraDetaillist(int menuNo)throws Exception{
		ArrayList<MenuDetail> list = new ArrayList<MenuDetail>();
		MenuDetail menuDetail = null;
		
		sql = "Select DISTINCT STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO AS STOCKNO "
				+ " FROM MENU, menuwithstock, STOCK "
				+  "WHERE  ( STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO ) "
				+ " NOT IN (select STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO FROM MENU, menuwithstock, STOCK "
				+ " WHERE MENU.NO  = ? AND menuwithstock.menuno = MENU.NO AND menuwithstock.stockno = STOCK.NO AND STOCK.NO = STOCK.NO)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				menuDetail = new MenuDetail();
				menuDetail.setMenuNo(0);
				menuDetail.setEa(0);
				menuDetail.setName(rs.getString("name"));
				menuDetail.setPrice(rs.getInt("price"));
				menuDetail.setStockAmount(rs.getInt("amount"));
				menuDetail.setStockNo(rs.getInt("stockno"));
				list.add(menuDetail);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<Integer> getAvailableStyle(int menuNo)throws Exception{
		ArrayList<Integer> list = new ArrayList<Integer>();
		sql = "SELECT STYLENO FROM MENUWITHSTYLE WHERE MENUNO = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("styleno"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}

	public int InsertOrder(Order order)  throws Exception{
		sql = "INSERT INTO Orders VALUES(0,?,?,?,?,?,?,default,?,?,?,?)";
		int Orderno = -1;
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, order.getName());
			ps.setString(2, order.getMobile());
			ps.setString(3, order.getAddress());
			ps.setInt(4, order.getTotalPrice());
			ps.setString(5, order.getCardNum());
			ps.setBoolean(6, order.getIsDiscounted());
			ps.setInt(7,0); //status == 0 (Preparing)
			ps.setString(8, order.getMemberID());
			ps.setInt(9, order.getMemberNo());
			ps.setString(10, order.getInfo());
			ps.executeUpdate();
			
			sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'orders' and table_schema =  DATABASE()";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Orderno = rs.getInt("AUTO_INCREMENT")-1;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
			
		}
		finally {  // 닫는건 예외처리 상관없이 실행되어야 함으로.
			close(con,ps);
		}
		return Orderno; 
	}
	
	public boolean InsertOrderedMenu(OrderedMenu orderedmenu, int Orderno) throws Exception{
		boolean result = false;
		sql = "INSERT INTO ORDEREDMENU VALUES (?,?,?,?,?)";
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1 , Orderno);
			ps.setString(2, orderedmenu.getMenu());
			ps.setString(3, orderedmenu.getStyle());
			ps.setString(4, orderedmenu.getOrderedDetailList());
			ps.setInt(5, orderedmenu.getPrice());
			result = ps.executeUpdate() == 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
			
		}
		finally {  // 닫는건 예외처리 상관없이 실행되어야 함으로.
			close(con,ps);
		}
		return result; 
		
	}

	public int getTotalPage() throws Exception{
		int total = 0;
		sql = "SELECT COUNT(*) FROM ORDERS";
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("COUNT(*)");
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
		return (total-1)/10 +1;
	}

	public int getTotalPage(int no) throws Exception{
		int total = 0;
		sql = "SELECT COUNT(*) FROM ORDERS WHERE memberNo = ?";
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("COUNT(*)");
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
		return (total-1)/10 +1;
	}

	public ArrayList<Order> getList(int page)throws Exception{
		ArrayList<Order> list = new ArrayList<Order>();
		Order order = null;
		int start = page * 10 -10;
		sql = "SELECT no, name, totalPrice, memberID, status, ordertime FROM ORDERS order by status,no LIMIT ?, ? ";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, 10);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				order = new Order();
				order.setNo(rs.getInt("no"));
				order.setName(rs.getString("name"));
				order.setTotalPrice(rs.getInt("totalPrice"));
				order.setMemberID(rs.getString("memberID"));
				order.setOrderTime(rs.getString("ordertime"));
				order.setStatus(rs.getInt("status"));
				list.add(order);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<Order> getList(int page, int memberno)throws Exception{
		ArrayList<Order> list = new ArrayList<Order>();
		Order order = null;
		int start = page * 10 -10;
		sql = "SELECT no, name, totalPrice, memberID, status, ordertime "
				+ " FROM ORDERS WHERE memberNo = ? order by status,no LIMIT ?, ? ";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberno);
			ps.setInt(2, start);
			ps.setInt(3, 10);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				order = new Order();
				order.setNo(rs.getInt("no"));
				order.setName(rs.getString("name"));
				order.setTotalPrice(rs.getInt("totalPrice"));
				order.setMemberID(rs.getString("memberID"));
				order.setOrderTime(rs.getString("ordertime"));
				order.setStatus(rs.getInt("status"));
				list.add(order);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}

	public Order selectOrder(int no) throws Exception {
		Order order = null;
		sql = "SELECT * FROM ORDERS WHERE no = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				order = new Order();
				order.setNo(rs.getInt("no"));
				order.setName(rs.getString("name"));
				order.setAddress(rs.getString("address"));
				order.setMobile(rs.getString("phone"));
				order.setTotalPrice(rs.getInt("totalPrice"));
				order.setCardNum(rs.getString("cardnum"));
				order.setDiscounted(rs.getBoolean("isdiscounted"));
				order.setOrderTime(rs.getString("ordertime"));
				order.setStatus(rs.getInt("status"));
				order.setMemberID(rs.getString("memberID"));
				order.setMemberNo(rs.getInt("memberNo"));
				order.setInfo(rs.getString("info"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}finally {
			close(con,ps,rs);
		}
		return order;
	}
	
	public ArrayList<OrderedMenu> selectOrderedMenu(int ordno) throws Exception{
		ArrayList<OrderedMenu> list = new ArrayList<OrderedMenu>();
		OrderedMenu ordMenu = null;
		sql = "SELECT * FROM ORDEREDMENU WHERE orderNo = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ordno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ordMenu = new OrderedMenu();
				ordMenu.setOrderNo(ordno);
				ordMenu.setMenu(rs.getString("menu"));
				ordMenu.setStyle(rs.getString("style"));
				ordMenu.setOrderedDetailList(rs.getString("additional"));
				ordMenu.setPrice(rs.getInt("price"));
				list.add(ordMenu);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}finally {
			close(con,ps,rs);
		}
		return list.isEmpty() ? null : list;
	}

	public boolean delete(int no) throws Exception{
		boolean result = false;
		sql = "DELETE FROM ORDERS WHERE no = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			result = 1 == ps.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		finally {
			close(con,ps);
		}
		return result;
	}

	public boolean update(int no, int status) throws Exception{
		boolean result = false;
		sql = "UPDATE ORDERS SET status = ? WHERE no = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, no);
			result = 1 == ps.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		finally {
			close(con,ps);
		}
		return result;
	}

}
