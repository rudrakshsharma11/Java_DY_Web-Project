package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyServlet extends HttpServlet {
	

	 private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            // Initialize the database connection (update the JDBC URL, username, and password)
	            String jdbcUrl = "jdbc:mysql://localhost:3306/student_db";
	            String dbUsername = "root";
	            String dbPassword = "Rudraksh@1101";

	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

	            // Query to check if the username and password are valid and retrieve user details
	            String sql = "SELECT * FROM your_table_name WHERE username = ? AND password = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                // Authentication successful
	                String name = rs.getString("name");
	                String address = rs.getString("address");
	                String email = rs.getString("email");

	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();

	                out.println("<html><body>");
	                out.println("<h2>Welcome,</h2> " + name );
	                out.println("<h4>Address : </h3>"+ address);
	                out.println("<p>Email: " + email + "</p>");
	                out.println("</body></html>");
	            } else {
	                // Authentication failed
	                response.getWriter().println("Check Your Credentials");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
	


