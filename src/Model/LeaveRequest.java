package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LeaveRequest {
    private int leaveId;
    private int empId;
    private int managerId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private Status status;
    private LocalDateTime createdAt;
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
    public int getLeaveId() {
        return leaveId;
    }
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }
    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
