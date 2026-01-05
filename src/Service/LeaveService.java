package Service;

import Model.LeaveRequest;

import java.sql.*;
import java.time.LocalDate;

public class LeaveService {
    Dbconnection con=new Dbconnection();
    public void insertLeaveRequest(int empId, int managerId, LocalDate fromDate, LocalDate toDate, String reason) throws SQLException {
        String sql = """
            INSERT INTO leave_request
            (emp_id, manager_id, from_date, to_date, reason, status)
            VALUES (?, ?, ?, ?, ?, 'PENDING')
        """;
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.setInt(2, managerId);
            ps.setDate(3, Date.valueOf(fromDate));
            ps.setDate(4, Date.valueOf(toDate));
            ps.setString(5, reason);
            ps.executeUpdate();
        }

    }
    public void createLeaveRequest(
            int empId,
            int managerId,
            LocalDate fromDate,
            LocalDate toDate,
            String reason) throws SQLException {
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date cannot be after To date");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Reason is required");
        }
        LeaveRequest leave = new LeaveRequest();
        leave.setEmpId(empId);
        leave.setManagerId(managerId);
        leave.setFromDate(fromDate);
        leave.setToDate(toDate);
        leave.setReason(reason);
        leave.setStatus(LeaveRequest.Status.valueOf("PENDING"));
        saveLeaveRequest(leave);
    }
    public void saveLeaveRequest(LeaveRequest leave) throws SQLException {
        String sql="insert into leave_request (emp_id, manager_id, from_date, to_date, reason, status)\n" +
                "        VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,leave.getEmpId());
        pre.setInt(2,leave.getManagerId());
        pre.setDate(3,Date.valueOf(leave.getFromDate()));
        pre.setDate(4, Date.valueOf(leave.getToDate()));
        pre.setString(5,leave.getReason());
        pre.setString(6,leave.getStatus().name());
        pre.executeUpdate();
    }
    public LeaveRequest getById(int leaveId) throws SQLException {
        String sql="select * from leave_request where leave_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,leaveId);
        ResultSet rs=pre.executeQuery();
        LeaveRequest leave = null;
        if (rs.next()) {
            leave = new LeaveRequest();
            leave.setLeaveId(rs.getInt("leave_id"));
            leave.setEmpId(rs.getInt("emp_id"));
            leave.setManagerId(rs.getInt("manager_id"));
            leave.setFromDate(rs.getDate("from_date").toLocalDate());
            leave.setToDate(rs.getDate("to_date").toLocalDate());
            leave.setReason(rs.getString("reason"));
            leave.setStatus(LeaveRequest.Status.valueOf(rs.getString("status")));
        }
        return leave;
    }
    public void updateStatus(int leaveId, String status) throws SQLException {
        String sql = "UPDATE leave_request SET status = ? WHERE leave_id = ?";
             PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, leaveId);
            ps.executeUpdate();
    }
}
