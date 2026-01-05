package Controller;

import Model.LeaveRequest;
import Model.Manager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
@WebServlet("/Get_leave_request")
public class Get_leave_request extends HttpServlet {
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
        List<LeaveRequest> list;
        try {
            list = Gobalservlet.service.getLeaveRequest(man_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("list", list);
        req.getRequestDispatcher("/manager_leave_request.jsp").forward(req,resp);
    }
}
