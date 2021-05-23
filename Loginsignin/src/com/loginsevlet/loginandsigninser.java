package com.loginsevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class loginandsigninser extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter pw=res.getWriter();
	
	String strid=req.getParameter("id");
	String strfirstname=req.getParameter("firstname");
	String strlastname=req.getParameter("lastname");
	String stremployementid=req.getParameter("employementid");
	String strstartdate=req.getParameter("startdate");
	String strdesignation=req.getParameter("designation");
	String strdepartment=req.getParameter("department");
	String strenddate=req.getParameter("enddate");
	String strstatus=req.getParameter("status");
	String strdob=req.getParameter("dob");
	String strreportingmanager=req.getParameter("reportingmanager");
	String strgender=req.getParameter("gender");
	String strbloodgroup=req.getParameter("bloodgroup");
	String straddress=req.getParameter("address");
	
	
	String strrelationship=req.getParameter("relationship");
	String strinstitution=req.getParameter("institution");
	
	String strpercentage=req.getParameter("percentage");
	String strpassword=req.getParameter("password");
	
	
	System.out.println("####name is"+strfirstname);
	System.out.println("****dob is"+strdob);
	System.out.println("****reportingmanager is"+strreportingmanager);
	System.out.println("****gender is"+strgender);
	System.out.println("**#$%^id is"+strid);
	System.out.println("***^*lastname is"+strlastname);
	System.out.println("*@employementid is"+stremployementid);
	System.out.println("*$%^&startdate is"+strstartdate);
	System.out.println("**$%^designation is"+strdesignation);
	System.out.println("**%%%department is"+strdepartment);
	System.out.println("*@@@@@@enddate is"+strenddate);
	System.out.println("**&&&status is"+strstatus);
	System.out.println("**###bloodgroup is"+strbloodgroup);
	System.out.println("**#$address is"+straddress);
	
	System.out.println("**#$%^relationship is"+strrelationship);
	System.out.println("**#$%^institution is"+strinstitution);
	System.out.println("**@***percentage is"+strpercentage);
	System.out.println("*!!!password is"+strpassword);
	
	
	Connection con=null;
	PreparedStatement pst=null;
	
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver is loaded");
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/darshan","root","root");
		    System.out.println("*******database connected "+con);
		     
		    pst=con.prepareStatement("INSERT INTO LOGIN(ID,FIRSTNAME,LASTNAME,EMPLOYEMENTID,STARTDATE,DESIGNATION,DEPARTMENT,ENDDATE,STATUS,DOB,REPORTINGMANAGER,GENDER,BLOODGROUP,ADDRESS,RELATIONSHIP,INSTITUION,PERCENTAGE,PASSWORD) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    pst.setString(1, strid);
		    pst.setString(2, strfirstname);
		    pst.setString(3, strlastname);
		    pst.setString(4, stremployementid);
		    pst.setString(5, strstartdate);
		    pst.setString(6, strdesignation);
		    pst.setString(7, strdepartment);
		    pst.setString(8, strenddate);
		    pst.setString(9, strstatus);
		    pst.setString(10, strdob);
		    pst.setString(11, strreportingmanager);
		    pst.setString(12, strgender);
		    pst.setString(13, strbloodgroup);
		    pst.setString(14, straddress);
		   
		    pst.setString(15, strrelationship);
		    pst.setString(16, strinstitution);
		    pst.setString(17, strpercentage);
		    pst.setString(18, strpassword);
		    
		    int i=pst.executeUpdate();
		    RequestDispatcher rd=null;
		    
		    if(i==1) {
		    	rd=req.getRequestDispatcher("./Jsp/sign.jsp");
		    	rd.forward(req,res);
		    }
		    
		    else {
		    	rd=req.getRequestDispatcher("./Jsp/signerror.jsp");
		    	rd.forward(req,res);
		    }
		    
		    
		} catch (SQLException e) {
			System.out.println("exception caugth" + e);
		}
		
	} catch (ClassNotFoundException e) {
		System.out.println("exception caugth" + e);
      
	}
	
	 finally {
		 if(pst!=null) {
			 try {
				pst.close();
				
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
			
			}
		 }
	 }
	
	}
	

}
