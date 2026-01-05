package Service;

import Model.Shift;
import jakarta.servlet.annotation.WebServlet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Shiftservice {
    Dbconnection con=new Dbconnection();
    public void add_shift(Shift s1) throws SQLException {
        String sql="insert into shift(name,role,start_time,end_time,shiftdate,min_emp,max_emp) values (?,?,?,?,?,?,?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,s1.getName());
        pre.setString(2,s1.getRole());
        pre.setObject(3,s1.getStart());
        pre.setObject(4,s1.getEnd());
        pre.setObject(5,s1.getShiftDate());
        pre.setInt(6,s1.getMin_count());
        pre.setInt(7,s1.getMax_count());
        pre.executeUpdate();
    }
    public void delete_shift(int id) throws SQLException {
        String sql="delete from shift where shift_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,id);
        pre.executeUpdate();
    }
    public  void reduce_shift_count(int shift_id) throws SQLException {
        String sql="update shift set max_count=max_count-1 where shift_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,shift_id);
        pre.executeUpdate();
    }
    public Shift get_shift(int shift_id) throws SQLException {
        String sql="select * from shift where shift_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,shift_id);
        ResultSet result=pre.executeQuery();
        Shift s1 = null;
        if(result.next()){
             s1=new Shift(result.getString("name"),
                    result.getString("role"),
                    result.getInt("min_emp"),
                    result.getInt("max_emp"),
                    result.getDate("shiftDate").toLocalDate(),
                    result.getTime("start_time").toLocalTime(),
                    result.getTime("end_time").toLocalTime());
        }
        return s1;
    }
    public int get_count(int shift_id) throws SQLException {
        String sql="select count(*) as total from employee_shift where shift_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,shift_id);
        ResultSet result= pre.executeQuery();
        if(result.next()){
            return result.getInt("total");
        }
        return 0;
    }
}
