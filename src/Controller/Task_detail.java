package Controller;

import Model.Task;
import Model.TaskHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/taskDetails")
public class Task_detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        Task task = null;
        try {
            task = Gobalservlet.service.getTaskById(taskId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<TaskHistory> history = null;
        try {
            history = Gobalservlet.service.getTaskHistory(taskId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("task", task);
        req.setAttribute("history", history);
        req.getRequestDispatcher("task-details.jsp")
                .forward(req, resp);
    }
}
