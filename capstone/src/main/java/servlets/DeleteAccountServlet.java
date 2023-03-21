package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = request.getParameter("password");
        String message = "";
        String redirectPage = "";

        try {
        	
        	// Print session attributes to console:
        	Enumeration<String> attributeNames = session.getAttributeNames();
        	while (attributeNames.hasMoreElements()) {
        	    String attributeName = attributeNames.nextElement();
        	    Object attributeValue = session.getAttribute(attributeName);
        	    System.out.println(attributeName + " = " + attributeValue);
        	}
        	
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true&threadPriority=1",
                    "capstone", "capstone");

            // Check if password matches with the one stored in the database
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE uname=? AND upwd=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Password matches, delete user account from the database
                ps = con.prepareStatement("DELETE FROM users WHERE uname=?");
                ps.setString(1, username);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    // Account deleted successfully
                    message = "Account deleted successfully";
                    redirectPage = "delete-success.jsp";
                } else {
                    // Account deletion failed
                    message = "Account deletion failed";
                    redirectPage = "DeleteAccount.jsp";
                }
            } else {
                // Password does not match
                message = "Invalid password. Account deletion failed";
                redirectPage = "DeleteAccount.jsp";
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message = "An error occurred while processing your request. Please try again later";
            redirectPage = "DeleteAccount.jsp";
        }

        session.invalidate();
        request.setAttribute("message", message);
        request.getRequestDispatcher(redirectPage).forward(request, response);
    }

}
