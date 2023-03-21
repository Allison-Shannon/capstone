package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String uemail = request.getParameter("username");
		 String upwd = request.getParameter("password");
		 HttpSession session = request.getSession();
		 RequestDispatcher dispatcher = null;
		 
		 // Null check for username/password/email
			/*
			 * if (uemail == null || uemail.isEmpty() || upwd == null || upwd.isEmpty()) {
			 * request.setAttribute("status", "failed"); RequestDispatcher loginDispatcher =
			 * request.getRequestDispatcher("login.jsp"); loginDispatcher.forward(request,
			 * response); return; }
			 */
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true&threadPriority=1", "capstone","capstone");
			 
			 System.out.println("Email: " + uemail);
			 System.out.println("Password: " + upwd);
			 
			 PreparedStatement pst = con.prepareStatement("select * from users where uemail = ? and upwd = ?");
			 pst.setString(1, uemail);
			 pst.setString(2, upwd);
			 ResultSet rs = pst.executeQuery();
			 
			 // If query returns matching user record, set session attribute and foward user to index
			 if (rs.next()) {
				   session.setAttribute("name", rs.getString("uname"));
				   dispatcher = request.getRequestDispatcher("index.jsp");
				} else {
				   request.setAttribute("status", "failed");
				   dispatcher = request.getRequestDispatcher("loginfailed.jsp");
				}
				dispatcher.forward(request, response);
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
	}

}
