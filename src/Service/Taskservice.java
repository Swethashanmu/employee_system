package Service;
import Controller.Gobalservlet;
import Model.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Taskservice {
    Dbconnection con=new Dbconnection();
    public void add_task(Task t1) throws SQLException {
        String sql="insert into task(task_name,emp_id,man_id,status) values (?,?,?,?)";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,t1.getTask_name());
        pre.setInt(2,t1.getEmp_id());
        pre.setInt(3,t1.getMan_id());
        pre.setString(4,t1.getStatus().name());
        pre.executeUpdate();
    }
    public int get_taskid(Task t1) throws SQLException {
        String sql="select task_id from task where task_name=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,t1.getTask_name());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return result.getInt("task_id");
        }
        return 0;
    }
    public List<Task> getAllTaskForManager(int id) throws SQLException {
        List<Task> arr=new ArrayList<>();
        String sql="select * from task where man_id=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,id);
        ResultSet result=pre.executeQuery();
        while(result.next()){
            Task t1=new Task(result.getInt("emp_id"), Task.TaskStatus.valueOf(result.getString("status")),result.getInt("task_id"),result.getString("task_name"));
            arr.add(t1);
        }
        return  arr;
    }
    public List<Task> getTasksForEmployee(int empId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = """
        SELECT task_id, task_name, status
        FROM task
        WHERE emp_id = ?
    """;
        PreparedStatement ps = con.getCon().prepareStatement(sql);
        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Task t = new Task();
            t.setTask_id(rs.getInt("task_id"));
            t.setTask_name(rs.getString("task_name"));
            t.setStatus(Task.TaskStatus.valueOf(rs.getString("status")));
            tasks.add(t);
        }
        return tasks;
    }
    public Task getTaskById(int taskId) throws SQLException {
        String sql = """
        SELECT task_id, task_name, emp_id, man_id, status
        FROM task
        WHERE task_id = ?
    """;
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setInt(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setTask_id(rs.getInt("task_id"));
                    task.setTask_name(rs.getString("task_name"));
                    task.setEmp_id(rs.getInt("emp_id"));
                    task.setMan_id(rs.getInt("man_id"));
                    task.setStatus(
                            Task.TaskStatus.valueOf(rs.getString("status"))
                    );
                    return task;
                }
            }
        }
        return null;
    }
    public void updateTaskStatus(int taskId, String status) throws SQLException {
        String sql = """
        UPDATE task
        SET status = ?
        WHERE task_id = ?
    """;
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, taskId);
            ps.executeUpdate();
        }
    }
    public  List<Integer> getPendingTasks(int leavingEmpId) throws SQLException {
        List<Integer> list=new ArrayList<>();
        String sql = "SELECT task_id\n" +
                "FROM task\n" +
                "WHERE emp_id = ?\n" +
                "  AND status != 'COMPLETED'";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,leavingEmpId);
        ResultSet result=pre.executeQuery();
        while(result.next()){
           list.add(result.getInt("task_id")) ;
    }
        return list;
    }
    public int findEmployeeWithLeastWorkload() throws SQLException {
         String sql="SELECT emp_id, COUNT(task_id) AS task_count\n" +
                 "FROM task\n" +
                 "WHERE status != 'COMPLETED'\n" +
                 "GROUP BY emp_id\n" +
                 "ORDER BY task_count ASC\n" +
                 "LIMIT 1;\n";
         PreparedStatement pre=con.getCon().prepareStatement(sql);
         ResultSet result=pre.executeQuery();
         if(result.next()){
             return result.getInt("emp_id");
         }
         return 0;
    }
    public  void updateTaskAssignee(int taskId,int  newEmp) throws SQLException {
        String sql= """
                UPDATE task
                SET emp_id = ?
                WHERE task_id = ?;
                """;
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setInt(1,newEmp);
        pre.setInt(2,taskId);
        pre.executeUpdate();
    }
    public void reassignPendingTasks(int leavingEmpId) throws SQLException {
        List<Integer> tasks = getPendingTasks(leavingEmpId);
        for (int taskId : tasks) {
            int newEmp = findEmployeeWithLeastWorkload();
            updateTaskAssignee(taskId, newEmp);
            Gobalservlet.service.addTaskHistory(
                    taskId,
                    newEmp,
                    "Assigned",
                    "Task reassigned due to leave"
            );
        }
    }
    public int getTaskId(Task task) throws SQLException {
        String sql="select * from task where task_name=?";
        PreparedStatement pre=con.getCon().prepareStatement(sql);
        pre.setString(1,task.getTask_name());
        ResultSet result=pre.executeQuery();
        if(result.next()){
            return result.getInt("task_id");
        }
        return 0;
    }

}
