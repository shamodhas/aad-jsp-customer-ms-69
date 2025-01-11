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
 * Created: 1/11/2025 2:14 PM
 * Project: Customer-JSP
 * --------------------------------------------
 **/

@WebServlet(name = "CustomerUpdateServlet", value = "/customer-update")
public class CustomerUpdateServlet extends HttpServlet {
    String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
    String DB_USER = "root";
    String DB_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("customer_id");
        String name = req.getParameter("customer_name");
        String address = req.getParameter("customer_address");
        String email = req.getParameter("customer_email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );
            String sql = "UPDATE customer SET name=?, address=?, email=? WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setString(3, email);
            pstm.setString(4, id);
            int effectedRowCount = pstm.executeUpdate();
            if (effectedRowCount > 0) {
                resp.sendRedirect(
                        "customer-update.jsp?message=Customer updated successfully"
                );
            } else {
                resp.sendRedirect(
                        "customer-update.jsp?error=Customer not found"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(
                    "customer-update.jsp?error=Fail to update customer"
            );
        }
    }
}
