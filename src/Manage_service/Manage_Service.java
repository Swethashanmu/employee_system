package Manage_service;

import Model.*;
import Service.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
public class Manage_Service {
   public static final Managerservice m_service=new Managerservice();
   public static final Employeservice e_service=new Employeservice();
   public static final Shiftservice s_service=new Shiftservice();
    public static final Taskservice t_service=new Taskservice();
   public  static final Task_history h_service=new Task_history();
   public static final LeaveService l_service=new LeaveService();
   public static final Employeeshift_service emp_service=new Employeeshift_service();
    public boolean validate_manager(Manager m1) throws SQLException {
        return m_service.validate_manager(m1);
    }
    public boolean validateEmployee(Employee e1) throws SQLException {
        return e_service.validateEmployee(e1);
    }
    public void add_employee(Employee e1) throws SQLException {
        e_service.add_employee(e1);
    }
    public void delete_employee(int id) throws SQLException {
         e_service.delete_employee(id);
    }
    public void add_shift(Shift s1) throws SQLException {
        s_service.add_shift(s1);
    }
    public void delete_shift(int id) throws SQLException {
        s_service.delete_shift(id);
    }
    public  void update_employee(int id,String status) throws SQLException {
        e_service.update_employee(id,status);
    }
    public void add_task(Task t1) throws SQLException {
        t_service.add_task(t1);
    }
    public int get_taskid(Task t1) throws SQLException {
       return t_service.get_taskid(t1);
    }
    public void add_taskhistory(Task t1,String description) throws SQLException {
       h_service.add_taskhistory(t1,description);
    }
    public List<Task> getAllTaskForManager(int id) throws SQLException {
        return t_service.getAllTaskForManager(id);
    }
    public  void reduce_shift_count(int shift_id) throws SQLException {
        s_service.reduce_shift_count(shift_id);
    }
    public Shift get_shift(int shift_id) throws SQLException {
        return s_service.get_shift(shift_id);
    }
    public void add_emp_shift(EmployeeShift s1) throws SQLException {
        emp_service.add_emp_shift(s1);
    }
    public int get_count(int shift_id) throws SQLException {
           return emp_service.get_count(shift_id);
    }
    public List<Integer> findShiftsToComplete(LocalDateTime now) throws SQLException {
        return emp_service.findShiftsToComplete(now);
    }
    public void markCompleted(int empShiftId) throws SQLException {
        emp_service.markCompleted(empShiftId);
    }
    public boolean employee_is_in_rest(int emp_id, LocalDate newdate, LocalTime newStartTime) throws SQLException {
        return emp_service.employee_is_in_rest(emp_id,newdate,newStartTime);
    }
    public List<Task> getTasksForEmployee(int empId) throws SQLException {
        return t_service.getTasksForEmployee(empId);
    }
    public List<TaskHistory> getTaskHistory(int taskId) throws SQLException {
        return h_service.getTaskHistory(taskId);
    }
    public Task getTaskById(int taskId) throws SQLException {
        return t_service.getTaskById(taskId);
    }
    public void addTaskHistory(int taskId, int empId, String status, String desc) throws SQLException {
        h_service.addTaskHistory(taskId,empId,status,desc);
    }
    public void updateTaskStatus(int taskId, String status) throws SQLException {
        t_service.updateTaskStatus(taskId,status);
    }
    public int get_manager_id(Employee e1) throws SQLException {
        return e_service.get_manager_id(e1);
    }
    public void createLeaveRequest(
            int empId,
            int managerId,
            LocalDate fromDate,
            LocalDate toDate,
            String reason) throws SQLException {
        l_service.createLeaveRequest(empId,managerId,fromDate,toDate,reason);
    }
    public List<LeaveRequest> getLeaveRequest(int managerid) throws SQLException {
        return m_service.getLeaveRequest(managerid);
    }
    public int getManagerId(Manager manager) throws SQLException {
        return m_service.getManagerId(manager);
    }
    public LeaveRequest getById(int leaveId) throws SQLException {
        return l_service.getById(leaveId);
    }
    public void updateStatus(int leaveId, String status) throws SQLException {
        l_service.updateStatus(leaveId,status);
    }
    public void reassignPendingTasks(int leavingEmpId) throws SQLException {
        t_service.reassignPendingTasks(leavingEmpId);
    }
    public int getManId(Manager manager) throws SQLException {
       return  m_service.getManId(manager);
    }
    public int getTaskId(Task task) throws SQLException {
        return t_service.getTaskId(task);
    }
    public int get_employee_id(Employee e1) throws SQLException {
        return e_service.get_employee_id(e1);
    }
    public  void updateTaskAssignee(int taskId,int  newEmp) throws SQLException {
        t_service.updateTaskAssignee(taskId,newEmp);
    }
    public  void delete_employeeShift(int shift_no) throws SQLException {
        emp_service.delete_employeeShift(shift_no);
    }
}
