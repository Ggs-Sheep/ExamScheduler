package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exam")
public class DAOExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", unique = true, nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "start_date", nullable = false)
    private Date start_date;
    @Column(name = "end_date", nullable = false)
    private Date end_date;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<DAOSession> sessions = new HashSet<>();

    public DAOExam() {
    }

    public DAOExam(String name, Date start_date, Date end_date) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<DAOSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<DAOSession> sessions) {
        this.sessions = sessions;
    }
}

