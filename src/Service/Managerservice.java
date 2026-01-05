package Service;
import Model.Employee;
import Model.LeaveRequest;
import Model.Manager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Managerservice {
    Dbconnection con=new Dbconnection();
    public boolean validate_manager(Manager m1) throws SQLException {
        String sql="select * from manager where name=? and password=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,m1.getName());
        pre.setString(2,m1.getPassword());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return true;
        }
        return false;
    }
    public List<LeaveRequest> getLeaveRequest(int managerid) throws SQLException {
        List<LeaveRequest> list=new ArrayList<>();
        String sql="select * from leave_request where manager_id=? and status='PENDING' order by created_at DESC";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setInt(1, managerid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest lr = new LeaveRequest();
                    lr.setLeaveId(rs.getInt("leave_id"));
                    lr.setEmpId(rs.getInt("emp_id"));
                    lr.setManagerId(rs.getInt("manager_id"));
                    lr.setFromDate(
                            rs.getDate("from_date").toLocalDate()
                    );
                    lr.setToDate(
                            rs.getDate("to_date").toLocalDate()
                    );
                    lr.setReason(rs.getString("reason"));
                    lr.setStatus(LeaveRequest.Status.valueOf(rs.getString("status")));
                    lr.setCreatedAt(
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                    list.add(lr);
                }
            }
        }
        return list;
    }
    public int getManagerId(Manager manager) throws SQLException {
        String sql="select * from manager where name=? and password=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
      pre.setString(1,manager.getName());
      pre.setString(2,manager.getPassword());
      ResultSet result=pre.executeQuery();
      if(result.next()){
          return result.getInt("manager_id");
      }
      return 0;
    }
    public int getManId(Manager manager) throws SQLException {
        String sql="select * from manager where name=? and password=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,manager.getName());
        pre.setString(2,manager.getPassword());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return result.getInt("manager_id");
        }
        return 0;
    }
}
