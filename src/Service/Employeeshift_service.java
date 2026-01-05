package Service;

import Model.EmployeeShift;
import Model.Shift;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Employeeshift_service {
    Dbconnection con=new Dbconnection();
    public void add_emp_shift(EmployeeShift s1) throws SQLException {
        String sql="insert into employee_shift(emp_id,shift_id,shift_date,shift_start,shift_end,status) values(?,?,?,?,?,?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,s1.getEmployeeId());
        pre.setInt(2,s1.getShiftId());
        pre.setDate(3,java.sql.Date.valueOf(s1.getShiftDate()));
        pre.setTime(4,java.sql.Time.valueOf(s1.getShiftStart()));
        pre.setTime(5,java.sql.Time.valueOf(s1.getShiftEnd()));
        pre.setString(6,s1.getStatus().name());
        pre.executeUpdate();
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
    public List<Integer> findShiftsToComplete(LocalDateTime now)
            throws SQLException {
        List<Integer> completedShiftIds = new ArrayList<>();
        String sql = """
        SELECT empshift_id
        FROM employee_shift
        WHERE status = 'SCHEDULED'
          AND (shift_date + shift_end) <= ?
    """;
        PreparedStatement ps = con.getCon().prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(now));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            completedShiftIds.add(rs.getInt("empshift_id"));
        }
        return completedShiftIds;
    }
    public void markCompleted(int empShiftId) throws SQLException {
        String sql = """
        UPDATE employee_shift
        SET status = 'COMPLETED'
        WHERE empshift_id = ?
          AND status = 'SCHEDULED'
    """;
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setInt(1, empShiftId);
            ps.executeUpdate();
        }
    }
    public boolean employee_is_in_rest(int emp_id, LocalDate newdate, LocalTime newStartTime) throws SQLException {
        String sql="SELECT shift_date, shift_end\n" +
                "FROM employee_shift\n" +
                "WHERE emp_id = ?\n" +
                "  AND status = 'COMPLETED'\n" +
                "ORDER BY shift_date DESC, shift_end DESC\n" +
                "LIMIT 1;\n";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,emp_id);
        ResultSet result=pre.executeQuery();
        if(result.next()){
            LocalDateTime lastEnd =
                    LocalDateTime.of(result.getDate("shift_date").toLocalDate(),result.getTime("shift_end").toLocalTime());
            LocalDateTime newStart =
                    LocalDateTime.of(newdate, newStartTime);
            long restSeconds =
                    Duration.between(lastEnd, newStart).getSeconds();
            System.out.println(restSeconds);
            return restSeconds >= 30 * 60;
        }
        return true;
    }
    public  void delete_employeeShift(int shift_no) throws SQLException {
        String sql="delete from employee_shift where shift_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,shift_no);
       pre.executeUpdate();
    }
}
