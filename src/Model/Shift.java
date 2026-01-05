package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shift {
    private  int shift_id;
    private String name;
    private String role;
    private LocalDate shiftDate;
    private int min_count;
    private int max_count;
   private LocalTime start;
   private LocalTime end;

    public Shift(String name,String role, int min_count, int max_count,LocalDate shiftDate,LocalTime start,LocalTime end) {
        this.end = end;
        this.shiftDate=shiftDate;
        this.max_count = max_count;
        this.min_count = min_count;
        this.name = name;
        this.role = role;
        this.start = start;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public int getMin_count() {
        return min_count;
    }

    public void setMin_count(int min_count) {
        this.min_count = min_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }
}

