package Controller;

import Model.LeaveRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/approveLeave")
public class ApproveLeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        LeaveRequest leave = null;
        try {
            leave = Gobalservlet.service.getById(leaveId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Gobalservlet.service.updateStatus(leaveId, "APPROVED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Gobalservlet.service.updateStatus(leave.getEmpId(), "ON_LEAVE");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Gobalservlet.service.reassignPendingTasks(leave.getEmpId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().println("leave approved successfully");
    }

}
