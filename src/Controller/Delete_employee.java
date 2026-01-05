package Controller;

import Manage_service.Manage_Service;
import Model.Manager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete_employee")
public class Delete_employee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manage_Service service=new Manage_Service();
        String id=req.getParameter("e_id");
        try {
            service.delete_employee(Integer.parseInt(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
