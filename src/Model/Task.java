package Model;

public class Task {
    private int taskid;
    private String task_name;
    private int emp_id;
    private int man_id;
    private TaskStatus status;
    public int getEmp_id() {
        return emp_id;
    }
    public enum TaskStatus {
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED;
        public static TaskStatus fromDb(String value) {
            if (value == null) return ASSIGNED;

            for (TaskStatus s : values()) {
                if (s.name().equalsIgnoreCase(value)) {
                    return s;
                }
            }
            return ASSIGNED;
        }
    }

    public Task() {
    }

    public Task(int emp_id, TaskStatus status, int task_id, String task_name) {
        this.emp_id = emp_id;
        this.status = status;
        this.taskid = task_id;
        this.task_name = task_name;
    }

    public Task(int emp_id, int man_id, TaskStatus status, String task_name) {
        this.emp_id = emp_id;
        this.man_id = man_id;
        this.status = status;
        this.task_name = task_name;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getMan_id() {
        return man_id;
    }

    public void setMan_id(int man_id) {
        this.man_id = man_id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTask_id(int task_id) {
        this.taskid = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
}

