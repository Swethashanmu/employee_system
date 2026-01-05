package Controller;

import Manage_service.Manage_Service;
import Model.Employee;
import Model.Manager;
import Model.Task;
import Service.Managerservice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/validate")
public class Validateservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Manage_Service service=new Manage_Service();
       String name=req.getParameter("name");
       String password=req.getParameter("password");
       String role=req.getParameter("role");
       System.out.println(role);
       if("manager".equals(role)){
           System.out.println("i am manager");
           Manager m1=new Manager(name,password);
           try {
               if(service.validate_manager(m1)){
                   System.out.println("manager");
                   HttpSession session=req.getSession(true);
                   session.setAttribute("Manager",m1);
                   req.getRequestDispatcher("/dashboard.html").forward(req,resp);
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
       if("employee".equals(role)){
           Employee e1=new Employee(name,password);
           try {
               if(service.validateEmployee(e1)){
                   System.out.println("i am employee");
                   HttpSession session=req.getSession(true);
                   session.setAttribute("Employee",e1);
                   resp.sendRedirect(
                           req.getContextPath() + "/employee-dashboard.html"
                   );
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

    }
}
