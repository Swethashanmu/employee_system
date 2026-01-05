package Controller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/add_employee","/add_shift","/deactivate","/delete_employee","/Delete_shift","/task_created","/Get_leave_request","/approveLeave","/task_reassign","/task_assign"})
public class Manager_filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =
                (HttpServletRequest) servletRequest;
        HttpServletResponse response =
                (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Manager") == null) {
            response.sendRedirect("index.html");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
