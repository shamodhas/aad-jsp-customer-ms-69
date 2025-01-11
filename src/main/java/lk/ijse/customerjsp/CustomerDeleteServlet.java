package lk.ijse.customerjsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.live
 * --------------------------------------------
 * Created: 1/11/2025 1:47 PM
 * Project: Customer-JSP
 * --------------------------------------------
 **/

@WebServlet(name = "CustomerDeleteServlet", value = "/customer-delete")
public class CustomerDeleteServlet extends HttpServlet {
    String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
    String DB_USER = "root";
    String DB_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );
            String sql = "DELETE FROM customer WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, customer_id);
            int effectedRowCount = pstm.executeUpdate();
            if (effectedRowCount > 0) {
                resp.sendRedirect(
                        "customer-delete.jsp?message=Customer deleted successfully"
                );
            } else {
                resp.sendRedirect(
                        "customer-delete.jsp?error=Customer not found"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(
                    "customer-delete.jsp?error=Fail to delete customer"
            );
        }
    }
}
