package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Customer;
import DTO.Employee;
import DTO.Member;

public class MemberDAO {
		//private static String db_url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//private static String db_username = "mrdaebak";
		//private static String db_password = "1234";
		//private static String class_name = "oracle.jdbc.driver.OracleDriver";
	
		private static MemberDAO dao;
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
		private MemberDAO() {}
		public synchronized static MemberDAO getInstance() {
			if(dao == null) {
				dao = new MemberDAO();
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

		public boolean update(Member member) {
			
				sql = "UPDATE member SET pw = ?, name = ?, phone = ?, address = ? WHERE no = ?";
				
			if(member instanceof Customer) {				
				sql = "UPDATE member SET pw = ?, name = ?, phone = ?, address = ?, vip = ? WHERE no = ?";
			}			
			else if(member instanceof Employee) {
				sql = "UPDATE member SET pw = ?, name = ?, phone = ?, address = ?, position = ? WHERE no = ?";
			}
			boolean result = false;
			
			try {
				con = ds.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1, member.getPw());
				ps.setString(2, member.getName());
				ps.setString(3, member.getMobile());
				ps.setString(4, member.getAddress());
							
				if(member instanceof Customer) {
					ps.setInt(5,((Customer)member).isVip() ? 1 : 0);
					ps.setInt(6, member.getNo());
					
				}else if(member instanceof Employee) {
					ps.setString(5, ((Employee)member).getPosition());
					ps.setInt(6, member.getNo());
					
				}else {
					ps.setInt(5, member.getNo());
				}
				result = 1 == ps.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					close(con,ps);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		public boolean insert(Member member) {
			sql = "INSERT INTO member VALUES(0, ?, ?, ?, ?, ?, ?, default, default)";
			boolean result = false;
			
			try{
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, member.getId());
				ps.setString(2, member.getPw());
				ps.setString(3, member.getName());
				ps.setString(4, member.getMobile());
				ps.setString(5, member.getAddress());
				
				if(member instanceof Employee) {
					ps.setInt(6, 1); // 1 = 매니저
				}
				else {
					ps.setInt(6, 0); //0 = (손님)
				}
				//ps.setInt(7, 0); //vip여부 default = 0 (vip아님)
				//ps.setInt(8, 0); //직책 default = Customer
				
				
				result = ps.executeUpdate() == 1;	
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {  // 닫는건 예외처리 상관없이 실행되어야 함으로.
				close(con,ps);
			}
			return result; 

		}
		
		
		public Member select(String id, String password) {
			Member member = null;
			sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, password);
				rs = ps.executeQuery();
				 
				if(rs.next()) {
					
					int type = rs.getInt("type");
					switch (type) {
					
						case 0: //customer
							member = new Customer();
							member.setNo(rs.getInt("no"));
							member.setId(rs.getString("id"));
							member.setPw(rs.getString("pw"));
							member.setName(rs.getString("name"));
							member.setMobile(rs.getString("phone"));
							member.setAddress(rs.getString("address"));
							member.setType(type);
							
							if(rs.getInt("vip") == 0) {
								((Customer)member).setVip(false);
							}
							else {
								((Customer)member).setVip(true);
							}
							break;
							
						case 1: //employee
							member = new Employee();
							member.setNo(rs.getInt("no"));
							member.setId(rs.getString("id"));
							member.setPw(rs.getString("pw"));
							member.setName(rs.getString("name"));
							member.setMobile(rs.getString("phone"));
							member.setAddress(rs.getString("address"));
							member.setType(type);
							((Employee)member).setPosition(rs.getString("position"));
							break;
							
						default:
							break;
						}
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(con,ps,rs);
			}
			return member;
		}
		
		public Member select(String id) {
			Member member = null;
			sql = "SELECT * FROM member WHERE id = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				 
				if(rs.next()) {
					
					int type = rs.getInt("type");
					
					switch (type) {
					
					case 0: //customer
						member = new Customer();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						
						if(rs.getInt("vip") == 0) {
							((Customer)member).setVip(false);
						}
						else {
							((Customer)member).setVip(true);
						}
						break;
						
					case 1: //employee
						member = new Employee();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						((Employee)member).setPosition(rs.getString("position"));
						break;
						
					default:
						break;
					}
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(con,ps,rs);
			}
			return member;
		}
		
		public Member select(int no) {
			Member member = null;
			sql = "SELECT * FROM member WHERE no = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, no);
				rs = ps.executeQuery();
				 
				if(rs.next()) {
					
					int type = rs.getInt("type");
					
					switch (type) {
					
					case 0: //customer
						member = new Customer();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						member.setType(rs.getInt("type"));
						
						if(rs.getInt("vip") == 0) {
							((Customer)member).setVip(false);
						}
						else {
							((Customer)member).setVip(true);
						}
						break;
						
					case 1: //employee
						member = new Employee();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						member.setType(rs.getInt("type"));
						((Employee)member).setPosition(rs.getString("position"));
						break;
						
					default:
						break;
					}
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(con,ps,rs);
			}
			return member;
		}
		
		public boolean delete(int no) {
			boolean result = false;
			sql = "DELETE FROM MEMBER WHERE no = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, no);
				result = 1 ==ps.executeUpdate();	
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(con,ps);
			}
			return result;
		}
		
		public boolean isExistID(String id) {
			boolean exist = false;
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				exist = rs.next();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					if(con != null) con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return exist;
		}
		
		public ArrayList<Member> getList(int page){
			ArrayList<Member> list = new ArrayList<Member>();
			Member member = null;
			int start = page * 10 -10;
			int end = page * 10 -1;
			sql = "SELECT no, id, name, type FROM MEMBER LIMIT ?, ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					member = new Member();
					member.setNo(rs.getInt("no"));
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setType(rs.getInt("type"));
					list.add(member);	
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		finally {
			close(con,ps,rs);
		}
		return list.isEmpty() ? null : list;
			
		}
		
		public int getTotalPage() {
			int total = 0;
			sql = "SELECT COUNT(*) FROM Member";
			try{
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()) {
					total = rs.getInt("COUNT(*)");
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		finally {
			close(con,ps,rs);
		}
			return (total-1)/10 +1;
		}
}
