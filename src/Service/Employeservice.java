package Service;

import Controller.Employee_filter;
import Controller.Gobalservlet;
import Model.Employee;
import Model.Manager;
import jakarta.servlet.http.HttpServlet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employeservice extends HttpServlet {
    Dbconnection con=new Dbconnection();
    public boolean validateEmployee(Employee e1) throws SQLException {
        String sql="select * from employee where name=? and password=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,e1.getName());
        pre.setString(2,e1.getPassword());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return true;
        }
        return false;
    }
    public void add_employee(Employee e1) throws SQLException {
        String sql="insert into employee(name,password,manager_id,role,status) values(?,?,?,?,?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,e1.getName());
        pre.setString(2,e1.getPassword());
        pre.setInt(3, Gobalservlet.service.getManId(e1.getManager()));
        pre.setString(4,e1.getRole());
        pre.setString(5,e1.getStatus().name());
       // pre.setInt(6,e1.get);
        pre.executeUpdate();
    }
    public void delete_employee(int id) throws SQLException {
        String sql="delete from employee where emp_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,id);
        pre.executeUpdate();
    }
    public  void update_employee(int id, String status) throws SQLException {
        String sql="update employee set status=? where emp_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,status);
        pre.setInt(2,id);
        pre.executeUpdate();
    }
    public int get_manager_id(Employee e1) throws SQLException {
        String sql="select * from employee where name=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,e1.getName());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return result.getInt("manager_id");
        }
        return 0;
    }
    public int get_employee_id(Employee e1) throws SQLException {
        String sql="select * from employee where name=? and password=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,e1.getName());
        pre.setString(2,e1.getPassword());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return result.getInt("emp_id");
        }
        return 0;
    }
}
