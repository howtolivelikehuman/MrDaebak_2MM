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
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//½Ì±ÛÅæ ÆÐÅÏ
	private OrderDAO() {}
	public synchronized static OrderDAO getInstance() {
		if(dao == null) {
			dao = new OrderDAO();
		}
		return dao;
	}

	//close ÇÏ´Â ¸Þ¼Òµå.
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
		
		sql = "select STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO AS STOCKNO " + 
				"FROM MENU, menuwithstock, STOCK WHERE " + 
				"menuwithstock.menuno = MENU.NO " + 
				"AND menuwithstock.stockno = STOCK.NO " + 
				"MINUS " + 
				"select STOCK.NAME, STOCK.PRICE, STOCK.AMOUNT, STOCK.NO AS STOCKNO " + 
				"FROM MENU, menuwithstock, STOCK WHERE MENU.NO  = ? " + 
				"AND menuwithstock.menuno = MENU.NO " + 
				"AND menuwithstock.stockno = STOCK.NO " + 
				"AND STOCK.NO = STOCK.NO";
		
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

	
	
}
