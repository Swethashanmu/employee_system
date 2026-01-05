package Controller;

import Manage_service.Manage_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/deactivate")
public class Deactivate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Manage_Service service=new Manage_Service();
        String emp_id = req.getParameter("e_id");
        System.out.println(emp_id);
       // String status = req.getParameter("status");
        try {
            Gobalservlet.service.update_employee(Integer.parseInt(emp_id),"INACTIVE");
            resp.getWriter().println("account deactivated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
