package com.ExamScheduler.solver.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

public class Timeslot {
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot(Date date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return startTime.toString();
    }

    public Date getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}
