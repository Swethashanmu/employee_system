package Controller;

import Model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/updateTask")
public class Update_task extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String status = req.getParameter("status");
        String description = req.getParameter("description");
        Employee employee = (Employee) session.getAttribute("Employee");
        int empId;
        try {
            empId=Gobalservlet.service.get_employee_id(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Gobalservlet.service.updateTaskStatus(taskId, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Gobalservlet.service.addTaskHistory(
                     taskId, empId, status, description
             );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("taskDetails?taskId=" + taskId);

    }
}
