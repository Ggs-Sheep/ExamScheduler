package com.ExamScheduler.dao;


import com.ExamScheduler.model.DAOSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends CrudRepository<DAOSubject, Integer> {
    DAOSubject findById(long id);

    DAOSubject findByName(String name);
}
