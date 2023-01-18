package com.ExamScheduler.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ExamScheduler.model.DAOUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {


	DAOUser findByEmail(String email);

	DAOUser findById(long id);

	ArrayList<DAOUser> findDAOUserByAdmin(boolean is_admin);

	ArrayList<DAOUser> findDAOUserByTeacher(boolean is_teacher);
	ArrayList<DAOUser> findDAOUserByAdminAndTeacher(boolean is_admin,boolean is_teacher);

	@Modifying
	@Query("update DAOUser u set u.admin = :admin where u.id = :id")
	void updateAdmin(@Param(value = "id") long id, @Param(value = "admin") boolean admin);
}