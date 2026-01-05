package Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/task_reassign")
public class TaskReassign extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("task_id"));
        int empId  = Integer.parseInt(req.getParameter("emp_id"));
        try {
           Gobalservlet.service.updateTaskAssignee(taskId, empId);
            resp.sendRedirect(req.getContextPath() + "/task-list.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
