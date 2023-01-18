package com.ExamScheduler.model;

public class UnavailableDTO {
    private int id;
    private DAOUser teacher;
    private DAOTimeSlot unavailability;

    public UnavailableDTO() {
    }

    public UnavailableDTO(DAOUser teacher, DAOTimeSlot unavailability) {
        this.teacher = teacher;
        this.unavailability = unavailability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DAOUser getTeacher() {
        return teacher;
    }

    public void setTeacher(DAOUser teacher) {
        this.teacher = teacher;
    }

    public DAOTimeSlot getUnavailability() {
        return unavailability;
    }

    public void setUnavailability(DAOTimeSlot unavailability) {
        this.unavailability = unavailability;
    }
}
