package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unavailable")
public class DAOUnavailable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unavailable_id", unique = true, nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private DAOUser teacher;
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private DAOTimeSlot unavailability;

    public DAOUnavailable() {

    }

    public DAOUnavailable(DAOUser teacher, DAOTimeSlot unavailability) {
        this.teacher = teacher;
        this.unavailability = unavailability;
    }

    public long getId() {
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
