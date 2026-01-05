package Controller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.ErrorLogger;
import java.io.IOException;
@WebFilter("/*")
public class ExceptionLoggingFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req =
                (HttpServletRequest) request;
        try {
            chain.doFilter(request, response);
        }
        catch (Exception e) {
            ErrorLogger.log(
                    req.getRequestURI(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e
            );
            req.setAttribute(
                    "errorType",
                    e.getClass().getSimpleName()
            );
            req.setAttribute(
                    "errorMessage",
                    "An unexpected error occurred. Please try again later."
            );
            req.getRequestDispatcher("/error-page.jsp")
                    .forward(req, response);
        }
    }
}

