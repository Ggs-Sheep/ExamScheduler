package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "time_slot")
public class DAOTimeSlot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id", unique = true, nullable = false)
    private long id;
    @Column(name = "start_date", nullable = false)
    private Date start_date;
    @Column(name = "end_date", nullable = false)
    private Date end_date;
    @OneToMany(mappedBy = "unavailability")
    @JsonIgnore
    private Set<DAOUnavailable> unavailabilities = new HashSet<>();

    @OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DAOSession> sessions = new HashSet<>();

    public DAOTimeSlot() {
    }

    public DAOTimeSlot(Date start_date, Date end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date startDate) {
        this.start_date = startDate;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date endDate) {
        this.end_date = endDate;
    }

    public Set<DAOUnavailable> getUnavailabilities() {
        return unavailabilities;
    }

    public void setUnavailabilities(Set<DAOUnavailable> unavailabilities) {
        this.unavailabilities = unavailabilities;
    }

    public Set<DAOSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<DAOSession> sessions) {
        this.sessions = sessions;
    }
}

