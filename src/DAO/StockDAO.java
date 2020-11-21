package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Stock;

public class StockDAO{
	private static StockDAO dao;
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
	private StockDAO() {}
	public synchronized static StockDAO getInstance() {
		if(dao == null) {
			dao = new StockDAO();
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

	public boolean delete(int[] no) {
		boolean result = false;
		sql = "DELETE FROM stock WHERE no In (";
		try {
			con = ds.getConnection();
			
			
			for(int i=0; i<no.length-1; i++) {
				sql = sql + no[i] + ",";
			}
			sql = sql + no[no.length-1] + ")";
			
			ps = con.prepareStatement(sql);
			result = 1 ==ps.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(con,ps);
		}
		return result;
	}
	
	public boolean merge(Stock stock) throws Exception{
		sql = "MERGE INTO stock USING dual ON (no = ?)"
				+ "WHEN MATCHED THEN "
				+ "UPDATE SET NAME = ?, AMOUNT = ? , NEXTSUPPLYDATE = ? , PRICE = ? "
				+ "WHEN NOT MATCHED THEN "
				+ "INSERT VALUES(STOCK_SEQ.NEXTVAL, ?,?,?,?)";
		
		boolean result = false;
		
		try {
			con = ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, stock.getNo());
			//update
			ps.setString(2, stock.getName());
			ps.setInt(3, stock.getAmount());
			ps.setString(4, stock.getNextSupplyDate());
			ps.setInt(5, stock.getPrice());
			//insert
			ps.setString(6, stock.getName());
			ps.setInt(7, stock.getAmount());
			ps.setString(8, stock.getNextSupplyDate());
			ps.setInt(9, stock.getPrice());
			result = 1 == ps.executeUpdate();			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				close(con,ps);
				
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}
	
	public boolean update(Stock stock) throws Exception{
		sql = "UPDATE stock SET name = ?, amount = ?, nextSupplyDate = ?, price = ? WHERE no = ?";
		boolean result = false;
		
		try {
			con = ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, stock.getName());
			ps.setInt(2, stock.getAmount());
			ps.setString(3, stock.getNextSupplyDate());
			ps.setInt(4, stock.getPrice());
			ps.setInt(5, stock.getNo());
			result = 1 == ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				close(con,ps);
				
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}
	
	public boolean insert(Stock stock)  throws Exception{
		sql = "INSERT INTO stock VALUES(stock_seq.NEXTVAL, ?,?,?,?)";
		boolean result = false;
		
		try {
			con = ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, stock.getName());
			ps.setInt(2, stock.getAmount());
			ps.setString(3, stock.getNextSupplyDate());
			ps.setInt(4, stock.getPrice());
			result = 1 == ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				close(con,ps);
				
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}
	
	public Stock select(int no) throws Exception{
		Stock stock = null;
		sql = "SELECT * FROM stock WHERE no = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				stock = new Stock();
				stock.setNo(no);
				stock.setName(rs.getString("name"));
				stock.setAmount(rs.getInt("amount"));
				stock.setNextSupplyDate(rs.getDate("nextSupplyDate").toString());
				System.out.println(rs.getDate("nextSupplyDate") + " " + stock.getNextSupplyDate());
				stock.setPrice(rs.getInt("price"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			close(con,ps,rs);
		}
		return stock;
	}

	public ArrayList<Stock> getList() throws Exception{
		ArrayList<Stock> list = new ArrayList<Stock>();
		Stock stock = null;
		sql = "select * from stock ORDER BY name ASC";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				stock = new Stock();
				stock.setNo(rs.getInt("no"));
				stock.setName(rs.getString("name"));
				stock.setAmount(rs.getInt("amount"));
				stock.setNextSupplyDate(rs.getDate("nextSupplyDate").toString());
				stock.setPrice(rs.getInt("price"));
				list.add(stock);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			}
		
		finally {
			close(con,ps,rs);
		}
		
		return list.isEmpty() ? null : list;
	}

	public boolean setAvailable() throws Exception{
		boolean result = false;
		sql = "UPDATE MENU SET AVAILABLE = 1";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			result = 1 == ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			}
	
		finally {
			close(con,ps,rs);
		}
		return result;
		}

	public boolean setUnAvailable(ArrayList<Integer> stockidx) throws Exception{
		boolean result = false;
		sql = "UPDATE MENU SET available = 0 " 
		+ "WHERE MENU.NO IN (SELECT menuno FROM MENUWITHSTOCK WHERE MENUWITHSTOCK.stockno  IN ("; 
		
		for(int i=0; i < stockidx.size()-1; i++) {
			sql = sql + stockidx.get(i) + ",";
		}
		sql = sql+stockidx.get(stockidx.size()-1) + "))";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			result = 1 == ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			}
	
		finally {
			close(con,ps,rs);
		}
		return result;
		}
}
