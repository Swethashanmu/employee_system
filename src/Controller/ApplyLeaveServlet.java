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
import java.time.LocalDate;
@WebServlet("/applyLeave")
public class ApplyLeaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Employee emp = (Employee) session.getAttribute("Employee");
        int managerId = 0;
        try {
            managerId = Gobalservlet.service.get_manager_id(emp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LocalDate fromDate = LocalDate.parse(req.getParameter("fromDate"));
        LocalDate toDate = LocalDate.parse(req.getParameter("toDate"));
        String reason = req.getParameter("reason");
        try {
            Gobalservlet.service.createLeaveRequest(
                    Gobalservlet.service.get_employee_id(emp), managerId, fromDate, toDate, reason
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      resp.getWriter().println("leave applied successfully");
    }
}
