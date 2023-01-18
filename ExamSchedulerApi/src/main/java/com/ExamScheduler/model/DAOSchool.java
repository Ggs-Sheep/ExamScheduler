package com.ExamScheduler.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "school")
public class DAOSchool implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id", unique = true, nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;

    public DAOSchool() {
    }

    public DAOSchool(String name) {
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

}
