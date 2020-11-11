<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.sql.ResultSet"%>
<%@ page import= "java.sql.Statement"%>
<%@ page import= "java.sql.Connection"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   hello jsp<br>
   <%
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         out.print("driver loading Succ");
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         if(null != stmt) stmt.close();
         if(null != rs) rs.close();
         if(null != conn) conn.close();
      }
   %>
</body>
</html>