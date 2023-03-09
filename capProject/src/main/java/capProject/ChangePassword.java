package capProject;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String password = request.getParameter("password");
		User user = new User();
		
		String htmlResponse = "<html><h2>Password Changed.</h2></html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlResponse);
		
}
}