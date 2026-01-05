package Service;

import Controller.Gobalservlet;
import Model.Task;
import Model.TaskHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task_history {
    Dbconnection con=new Dbconnection();
    public void add_taskhistory(Task t1, String description) throws SQLException {
     String sql="insert into task_history(task_id,employee_id,status,description,createdAt) values(?,?,?,?,?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1, Gobalservlet.service.getTaskId(t1));
        pre.setInt(2,t1.getEmp_id());
        pre.setString(3,t1.getStatus().name());
        pre.setString(4,description);
        pre.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        pre.executeUpdate();
    }
    public List<TaskHistory> getTaskHistory(int taskId) throws SQLException {
        List<TaskHistory> history = new ArrayList<>();
        String sql = """
        SELECT employee_id, status, description, createdat
        FROM task_history
        WHERE task_id = ?
        ORDER BY createdat ASC
    """;
        PreparedStatement ps = con.getCon().prepareStatement(sql);
        ps.setInt(1, taskId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TaskHistory h = new TaskHistory();
            h.setEmployeeId(rs.getInt("employee_id"));
            h.setStatus(Task.TaskStatus.fromDb(rs.getString("status")));
            h.setDescription(rs.getString("description"));
            h.setCreatedAt(rs.getTimestamp("createdat").toLocalDateTime());
            history.add(h);
        }
        return history;
    }
    public void addTaskHistory(int taskId, int empId,
                                String status, String desc)
            throws SQLException {
        String sql = """
        INSERT INTO task_history
        (task_id, employee_id, status, description, createdat)
        VALUES (?, ?, ?, ?, NOW())
    """;
        PreparedStatement ps = con.getCon().prepareStatement(sql);
        ps.setInt(1, taskId);
        ps.setInt(2, empId);
        ps.setString(3, status);
        ps.setString(4, desc);
        ps.executeUpdate();
    }


}
