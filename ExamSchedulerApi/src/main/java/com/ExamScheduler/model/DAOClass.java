package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
public class DAOClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", unique = true, nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "classroom")
    @JsonIgnore
    private Set<DAOSession> sessions = new HashSet<>();


    public DAOClass() {
    }

    public DAOClass(String name) {
        this.name = name;
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

    public Set<DAOSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<DAOSession> sessions) {
        this.sessions = sessions;
    }

}
