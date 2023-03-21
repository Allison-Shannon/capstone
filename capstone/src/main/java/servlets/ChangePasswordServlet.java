package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/changepassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentpassword");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirmpassword");
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        if (checkPassword(userEmail, currentPassword)) {
            if (newPassword.equals(confirmPassword)) {
                if (updatePassword(userEmail, newPassword)) {
                    session.setAttribute("password_changed", true);
                    response.sendRedirect("update-success.jsp");
                } else {
                    request.setAttribute("status", "failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "New password and confirm password do not match.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("status", "failed");
            request.setAttribute("message", "Current password is incorrect.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean checkPassword(String email, String password) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true&threadPriority=1", "capstone","capstone");
            pst = con.prepareStatement("SELECT * FROM users WHERE uemail=? AND upwd=?");
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            result = rs.next();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    private boolean updatePassword(String email, String newPassword) {
        Connection con = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            con = getConnection();
            pst = con.prepareStatement("UPDATE users SET upwd=? WHERE uemail=?");
            pst.setString(1, newPassword);
            pst.setString(2, email);
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, pst);
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/users?&useSSL=false&allowPublicKeyRetrieval=true&threadPriority=1", "capstone","capstone");
    }

    private void closeConnection(Connection con, PreparedStatement pst) {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
