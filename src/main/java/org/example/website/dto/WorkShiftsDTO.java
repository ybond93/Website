package org.example.website.dto;

public class WorkShiftsDTO {
    private int shiftId;
    private String shiftType;
    private Integer year;
    private String month;
    private String day;
    private Integer empId;

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getId() {
        return shiftId;
    }
}