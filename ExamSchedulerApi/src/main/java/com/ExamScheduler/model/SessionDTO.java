package com.ExamScheduler.model;

public class SessionDTO {
    private long id;
    private DAOTimeSlot date;
    private DAOSubject subject;
    private DAOClass classroom;
    private DAOUser teacher;
    private DAORoom room;
    private DAOExam exam;

    public SessionDTO() {
    }

    public SessionDTO(DAOTimeSlot date, DAOSubject subject, DAOClass classroom, DAOUser teacher, DAORoom room, DAOExam exam) {
        this.date = date;
        this.subject = subject;
        this.classroom = classroom;
        this.teacher = teacher;
        this.room = room;
        this.exam = exam;
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
