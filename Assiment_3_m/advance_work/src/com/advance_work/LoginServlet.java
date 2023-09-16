package com.advance_work;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
  
	public class LoginServlet extends HttpServlet {
		
	  
	     
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		  String email = req.getParameter("email");
		  String password = req.getParameter("password");
		  try (Connection con =DBconfig.getCon();){
			  String query = "select * from Login where email =? and password =?";
			  PreparedStatement ps = con.prepareStatement(query);
			  ps.setString(1, email);
			  ps.setString(2, password);
			  ResultSet rs  = ps.executeQuery();
			  if (rs.next()) {
				 RequestDispatcher rd = req.getRequestDispatcher("DisplayServlet");
				 rd.forward(req, res);
			  }
			  else {
				  res.setContentType("text/html");
				  PrintWriter pw = res.getWriter();
				  pw.print("<h1>invalid credential!!!!<h1>");
				  RequestDispatcher rd = req.getRequestDispatcher("login");
					 rd.forward(req, res);
			  }
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
