package Controller;
import Manage_service.Manage_Service;
import Model.Employee;
import Model.EmployeeShift;
import Model.Shift;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import static java.lang.Integer.parseInt;
@WebServlet("/add_shift_to_emp")
public class Add_shift_to_emp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manage_Service service=new Manage_Service();
       String s_id=req.getParameter("s_id");
       String e_id=req.getParameter("e_id");
        try {
            Shift s1=service.get_shift(parseInt(s_id));
            System.out.println(service.get_count(parseInt(s_id)));
            System.out.println(s1.getMax_count());
            int shift_id=parseInt(s_id);
            if(service.get_count(parseInt(s_id))<s1.getMax_count()) {
                EmployeeShift e_shift = new EmployeeShift(parseInt(e_id), shift_id, s1.getShiftDate(), s1.getStart(), s1.getEnd(), EmployeeShift.Status.valueOf("SCHEDULED"));
                if(service.employee_is_in_rest(parseInt(e_id),s1.getShiftDate(),s1.getStart())){
                    service.add_emp_shift(e_shift);
                }else{
                    resp.getWriter().println("this employee is not available for this shift");
                }
            }else{
                resp.getWriter().println("this shift is filled with employee");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
