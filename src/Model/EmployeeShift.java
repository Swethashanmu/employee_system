package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeShift {
    private int employeeShiftId;
    private int employeeId;
    private int shiftId;
    private LocalDate shiftDate;
    private LocalTime shiftStart;
    private LocalTime shiftEnd;
    private Status status;
    public enum Status{
        SCHEDULED,
        COMPLETED
    }
    public EmployeeShift(int employeeId, int shiftId, LocalDate shiftDate, LocalTime shiftStart,LocalTime shiftEnd, Status status) {
        this.employeeId = employeeId;
        this.shiftDate = shiftDate;
        this.shiftEnd = shiftEnd;
        this.shiftId = shiftId;
        this.shiftStart = shiftStart;
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getShiftDate() {
        return shiftDate;
    }
    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeShiftId() {
        return employeeShiftId;
    }

    public void setEmployeeShiftId(int employeeShiftId) {
        this.employeeShiftId = employeeShiftId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(LocalTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

}

