package Controller;

import Manage_service.Manage_Service;
import Model.Employee;
import Model.Manager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add_employee")
public class Add_employee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manage_Service service=new Manage_Service();
        String name=req.getParameter("name");
        String password= req.getParameter("password");
        String role=req.getParameter("role");
        String status=req.getParameter("status");
        HttpSession session=req.getSession();
        Manager manager=(Manager)session.getAttribute("Manager");
        System.out.println(manager);
        Employee e1=new Employee(manager,name,password,role,status);
        try {
            service.add_employee(e1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().println("employee add successfully");
    }
}
