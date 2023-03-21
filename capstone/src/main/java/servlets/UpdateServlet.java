package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update-user")
public class UpdateServlet extends HttpServlet {
  
    private static final long serialVersionUID = 1L;
    
    // Remember to change all servlets to use the following constants for JDBC connection
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL ="jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true&threadPriority=1";
    private static final String USER = "capstone";
    private static final String PASS = "capstone";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String uemail = request.getParameter("uemail");
        String umobile = request.getParameter("umobile");

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE users SET uname=?, uemail=?, upwd=?, umobile=? WHERE id=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, uname);
            stmt.setString(2, upwd);
            stmt.setString(3, uemail);
            stmt.setString(4, umobile);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            response.sendRedirect("update-success.jsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
