package Controller;

import Manage_service.Manage_Service;
import Model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task_created")
public class Task_created extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manage_Service service=new Manage_Service();
       String task_name=req.getParameter("task");
       String emp_id=req.getParameter("emp_id");
       String man_id=req.getParameter("man_id");
        Task t1=new Task(Integer.parseInt(emp_id),Integer.parseInt(man_id), Task.TaskStatus.valueOf("ASSIGNED"),task_name);
        try {
            service.add_task(t1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            service.add_taskhistory(t1,task_name+": this task assigned to this employee "+emp_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Task> list;
        try {
            list= service.getAllTaskForManager(Integer.parseInt(man_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("taskList", list);
        req.getRequestDispatcher("task-reassign.jsp").forward(req, resp);
    }
}
