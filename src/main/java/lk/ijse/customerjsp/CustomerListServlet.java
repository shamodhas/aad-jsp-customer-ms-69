package lk.ijse.customerjsp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.live
 * --------------------------------------------
 * Created: 1/11/2025 11:46 AM
 * Project: Customer-JSP
 * --------------------------------------------
 **/

@WebServlet(name = "CustomerListServlet", value = "/customer-list")
public class CustomerListServlet extends HttpServlet {
    String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
    String DB_USER = "root";
    String DB_PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDTO> customerList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );
            String sql = "SELECT * FROM customer";
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                CustomerDTO customerDTO = new CustomerDTO(
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)
                );
                customerList.add(customerDTO);
            }

            // Attaches the customers list to the request object, making it accessible in the JSP.
            req.setAttribute("customers", customerList);

            // Used to forward the request to jsp file
            RequestDispatcher rd = req.getRequestDispatcher("customer-list.jsp");

            // Sends the request and response objects to the specified JSP for rendering
            rd.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(
                    "customer-list.jsp?error=Failed to retrieve customers"
            );
        }
    }
}
