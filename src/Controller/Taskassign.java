package Controller;

import Model.LeaveRequest;
import Model.Manager;
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
@WebServlet("/task_assign")
public class Taskassign extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Manager manager = (Manager) session.getAttribute("Manager");
        int man_id;
        try {
            man_id = Gobalservlet.service.getManagerId(manager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Task> list;
        try {
            list = Gobalservlet.service.getAllTaskForManager(man_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(man_id);
        System.out.println(list);
        req.setAttribute("taskList", list);
        req.getRequestDispatcher("/task-reassign.jsp").forward(req,resp);
    }
}
