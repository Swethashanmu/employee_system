package Model;

import java.util.ArrayList;

public class Employee {
    private  int emp_id;
    private String name;
    private String password;
    private Manager manager;
    private String role;
    private Status status;
    public enum Status{
        ACTIVE,
        INACTIVE,
        ON_LEAVE
    }
    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Employee(Manager manager, String name, String password, String role,String status) {
        this.manager = manager;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status=Status.valueOf(status);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
