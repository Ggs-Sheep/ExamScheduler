package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SessionDao extends CrudRepository<DAOSession, Integer> {
    DAOSession findById(long id);
    Set<DAOSession> findByTeacher(long id);
    Set<DAOSession> findByDate(long id);
    Set<DAOSession> getAllByClassroom(long id);
    Set<DAOSession> findByRoom(long id);
    Set<DAOSession> findByExam(long id);
}
