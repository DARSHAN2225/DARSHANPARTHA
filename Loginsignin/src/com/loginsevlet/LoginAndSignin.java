package com.loginsevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAndSignin extends HttpServlet{
  Connection con=null;
  
  @Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("######## inside init() method");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/darshan","root","root");
		    System.out.println("Driver connected");
		    
		} catch (ClassNotFoundException e) {
		     System.out.println("exception caught"+e);
		} catch (SQLException e) {
			 System.out.println("exception caught@@@"+e);
			
		}
	
	}
   @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   
	   ResultSet rs=null;
	   Statement st=null;
	   boolean b=false;
	   res.setContentType("text/html");
	   PrintWriter writer=res.getWriter();
	   String strid=req.getParameter("id");
	   String strpassword=req.getParameter("password");
	   System.out.println("inside the dopost() method");
    
	   System.out.println("########");
	   
	   try {
		   System.out.println("########");
		st=con.createStatement();
		rs=st.executeQuery("SELECT ID,PASSWORD FROM LOGIN ");
		System.out.println("########");
		 
		while(rs.next()) {
			 System.out.println("con statment connection");
			 if((strid.equalsIgnoreCase(rs.getString("ID")))  && (strpassword.equalsIgnoreCase(rs.getString("PASSWORD")))) {
				 System.out.println("########");
				 b=true;
				 break;
			 }
		 }
		 
		 RequestDispatcher rd=null;
		 
		 if (b) {
			 rd=req.getRequestDispatcher("./Jsp/login.jsp");
			 rd.include(req,res);
			 
		 }else {
			 rd = req.getRequestDispatcher("./Jsp/loginerror.jsp"); 
				rd.include(req, res);
				
		 }
	} catch (SQLException e) {
		System.out.println("exception caugth " + e);
	
	}
	   
		finally {
			try {
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			
				} catch (SQLException e) {
			
				}
			}
			
   }
   
   private void distroy() {
	System.out.println("######3inside distroy method");
	try {
	if(con!=null) {
		
			con.close();
	}
		} catch (SQLException sql) {
			System.out.println("exception caught " + sql);
		

		}
	}
}




	   
	  
		   
	   
   

