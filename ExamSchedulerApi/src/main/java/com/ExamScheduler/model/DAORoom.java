package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class DAORoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", unique = true, nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private Set<DAOSession> sessions = new HashSet<>();

    public DAORoom() {
    }

    public DAORoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<DAOSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<DAOSession> sessions) {
        this.sessions = sessions;
    }
}
