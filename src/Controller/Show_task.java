package Controller;

import Manage_service.Manage_Service;
import Model.Employee;
import Model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/show_task")
public class Show_task extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Manage_Service service=new Manage_Service();
        HttpSession session=req.getSession(false);
        Employee e1=(Employee)session.getAttribute("Employee");
        int n;
        try {
             n=Gobalservlet.service.get_employee_id(e1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Task> taskList = null;
        try {
            taskList = Gobalservlet.service.getTasksForEmployee(n);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("taskList", taskList);
        req.getRequestDispatcher("employee-task-list.jsp")
                .forward(req, resp);
    }
}
