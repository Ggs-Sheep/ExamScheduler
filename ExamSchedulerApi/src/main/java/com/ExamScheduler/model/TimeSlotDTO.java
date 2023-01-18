package com.ExamScheduler.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TimeSlotDTO {
    private int id;
    private Date start_date;
    private Date end_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
