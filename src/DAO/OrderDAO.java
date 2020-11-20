package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Menu;
import DTO.Stock;

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
				+ " FROM menu "
				+ " WHERE type = 0 ";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				menu = new Menu();
				menu.setNo(rs.getInt("no"));
				menu.setName(rs.getString("name"));
				menu.setType(0);
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
	
	public ArrayList<Stock> getStocklist(int menuNo){
		ArrayList<Stock> list = new ArrayList<Stock>();
		Stock stock = null;
		
		sql = "SELECT no, name, amount, PRICE FROM STOCK, MENUWITHSTOCK "
				+ "WHERE MENUWITHSTOCK.MENUNO = ? "
				+ "AND STOCK.NO = MENUWITHSTOCK.STOCKNO ";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				stock = new Stock();
				stock.setNo(rs.getInt("no"));
				stock.setName(rs.getString("name"));
				stock.setAmount(rs.getInt("amount"));
				stock.setPrice(rs.getInt("price"));
				list.add(stock);	
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
