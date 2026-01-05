package Controller;

import Manage_service.Manage_Service;
import Model.Shift;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/add_shift")
public class Add_shift extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manage_Service service=new Manage_Service();
      String name=req.getParameter("name");
      String role=req.getParameter("role");
      String min_count=req.getParameter("min_count");
      String max_count=req.getParameter("max_count");
      String date=req.getParameter("date");
      String start_time=req.getParameter("start_time");
      String end_time=req.getParameter("end_time");
      Shift s1=new Shift(name,role,Integer.parseInt(min_count),Integer.parseInt(max_count),LocalDate.parse(date), LocalTime.parse(start_time),LocalTime.parse(end_time));
        try {
            service.add_shift(s1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
