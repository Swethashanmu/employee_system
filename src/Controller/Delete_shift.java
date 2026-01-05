package Controller;

import Manage_service.Manage_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
@WebServlet("/Delete_shift")
public class Delete_shift extends HttpServlet {
    Manage_Service service=new Manage_Service();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String shift_id=req.getParameter("s_id");
        try {
            service.delete_shift(Integer.parseInt(shift_id));
            service.delete_employeeShift(Integer.parseInt(shift_id));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
