package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Employee;
import DTO.Menu;
import DTO.MenuDetail;
import DTO.Order;
import DTO.OrderedMenu;
import DTO.Stock;
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
			
	public ArrayList<Menu> getMenulist(){
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
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<Style> getStylelist(){
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
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<MenuDetail> getMenuDetaillist(int menuNo){
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
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<MenuDetail> getExtraDetaillist(int menuNo){
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
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}
	
	public ArrayList<Integer> getAvailableStyle(int menuNo){
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
		}
	finally {
		close(con,ps,rs);
	}
	return list.isEmpty() ? null : list;
		
	}

	public int InsertOrder(Order order) {
		sql = "INSERT INTO Orders VALUES(0,?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setString(7, order.getDeliverydateTime());
			ps.setInt(8,0); //status == 0 (Preparing)
			ps.setString(9, order.getMemberID());
			ps.setInt(10, order.getMemberNo());
			ps.setString(11, order.getInfo());
			ps.executeUpdate();
			
			sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'orders' and table_schema =  DATABASE()";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Orderno = rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {  // 닫는건 예외처리 상관없이 실행되어야 함으로.
			close(con,ps);
		}
		return Orderno; 
	}
	
	public boolean InsertOrderedMenu(OrderedMenu orderedmenu, int Orderno) {
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
			
		}
		finally {  // 닫는건 예외처리 상관없이 실행되어야 함으로.
			close(con,ps);
		}
		return result; 
		
	}
}
