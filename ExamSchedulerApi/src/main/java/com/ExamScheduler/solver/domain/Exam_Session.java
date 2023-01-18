package com.ExamScheduler.solver.domain;

import com.ExamScheduler.model.DAOClass;
import com.ExamScheduler.model.DAORoom;
import com.ExamScheduler.model.DAOSubject;
import com.ExamScheduler.model.DAOUser;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Exam_Session {

    @PlanningId
    private Long id;

    private DAOSubject subject;
    private DAOUser teacher;
    private DAOClass studentGroup;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private DAORoom room;

    // No-arg constructor required for OptaPlanner
    public Exam_Session() {
    }

    public Exam_Session(long id, DAOSubject subject, DAOUser teacher, DAOClass studentGroup) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Exam_Session(long id, DAOSubject subject, DAOUser teacher, DAOClass studentGroup, Timeslot timeslot, DAORoom room) {
        this(id, subject, teacher, studentGroup);
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DAOSubject getSubject() {
        return subject;
    }

    public void setSubject(DAOSubject subject) {
        this.subject = subject;
    }

    public DAOUser getTeacher() {
        return teacher;
    }

    public void setTeacher(DAOUser teacher) {
        this.teacher = teacher;
    }

    public DAOClass getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(DAOClass studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public DAORoom getRoom() {
        return room;
    }

    public void setRoom(DAORoom room) {
        this.room = room;
    }
}
