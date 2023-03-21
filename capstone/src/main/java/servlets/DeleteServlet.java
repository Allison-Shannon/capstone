package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete-user")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		Connection con = null;

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true", "capstone","capstone");				

			// Execute SQL query
			String sql = "DELETE FROM users WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			int rowsDeleted = stmt.executeUpdate();

			// Close connection
			con.close();

			// Redirect to success page
			response.sendRedirect("delete-success.jsp");
		} catch(Exception e) {
			out.println(e);
		}

		out.close();
	}
}
