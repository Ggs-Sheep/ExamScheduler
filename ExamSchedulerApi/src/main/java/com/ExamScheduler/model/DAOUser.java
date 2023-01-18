package com.ExamScheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class DAOUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private long id;
	@Column(name = "email", nullable = false)
	private String email;
	@Column
	@JsonIgnore
	private String password;
	@Column(name = "is_teacher", nullable = false)
	private boolean teacher;
	@Column(name = "is_admin",nullable = false)
	private boolean admin;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@OneToMany(mappedBy = "teacher")
	@JsonIgnore
	private Set<DAOUnavailable> unavailabilities = new HashSet<>();

	@OneToMany(mappedBy = "teacher")
	@JsonIgnore
	private Set<DAOSession> sessions = new HashSet<>();


	public DAOUser() {
	}

	public DAOUser(String email, String password, boolean teacher, boolean admin, String first_name, String last_name) {
		this.email = email;
		this.password = password;
		this.teacher = teacher;
		this.admin = admin;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTeacher() {
		return teacher;
	}

	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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