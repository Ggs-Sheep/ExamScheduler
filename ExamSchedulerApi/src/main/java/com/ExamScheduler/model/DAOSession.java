package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "session")
public class DAOSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", unique = true, nullable = false)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slot_id", nullable = false)
    private DAOTimeSlot date;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private DAOSubject subject;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private DAOClass classroom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private DAOUser teacher;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private DAORoom room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id", nullable = false)
    @JsonIgnore
    private DAOExam exam;

    public DAOSession() {
    }

    public DAOSession(DAOTimeSlot date, DAOSubject subject, DAOClass classroom, DAOUser teacher, DAORoom room, DAOExam exam) {
        this.date = date;
        this.subject = subject;
        this.classroom = classroom;
        this.teacher = teacher;
        this.room = room;
        this.exam = exam;
    }

    public DAOSession(DAOTimeSlot date, DAOSubject subject, DAOClass classroom, DAOUser teacher, DAORoom room) {
        this.date = date;
        this.subject = subject;
        this.classroom = classroom;
        this.teacher = teacher;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DAOTimeSlot getDate() {
        return date;
    }

    public void setDate(DAOTimeSlot date) {
        this.date = date;
    }

    public DAOSubject getSubject() {
        return subject;
    }

    public void setSubject(DAOSubject subject) {
        this.subject = subject;
    }

    public DAOClass getClassroom() {
        return classroom;
    }

    public void setClassroom(DAOClass classroom) {
        this.classroom = classroom;
    }

    public DAOUser getTeacher() {
        return teacher;
    }

    public void setTeacher(DAOUser teacher) {
        this.teacher = teacher;
    }

    public DAORoom getRoom() {
        return room;
    }

    public void setRoom(DAORoom room) {
        this.room = room;
    }

    public DAOExam getExam() {
        return exam;
    }

    public void setExam(DAOExam exam) {
        this.exam = exam;
    }
}
