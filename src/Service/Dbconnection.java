package Service;

import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Dbconnection {
    private static final String URL="jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME="postgres";
    private static final String PASSWORD="zoho";
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public Dbconnection()  {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Dbconnection connection;
    public Dbconnection getConnection() throws SQLException, ClassNotFoundException {
        if(connection==null){
            return new Dbconnection();
        }
        return connection;
    }

   /* public static class Task_history {
        Dbconnection con=new Dbconnection();
        public void add_taskhistory(Task t1, String description) throws SQLException {
            String sql="insert into task_history (task_id,employee_id,status,description,createdat) values(?,?,?,?,?)";
            PreparedStatement pre=con.getCon().prepareStatement(sql);
            pre.setInt(1,t1.getTask_id());
            pre.setInt(2,t1.getEmp_id());
            pre.setObject(3,t1.getStatus());
            pre.setString(4,description);
            pre.setObject(5, LocalDateTime.now());
            pre.executeUpdate();
        }
    }*/
}
