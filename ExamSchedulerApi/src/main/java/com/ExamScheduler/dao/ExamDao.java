package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOExam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDao extends CrudRepository<DAOExam, Integer> {
    DAOExam findById(long id);

    DAOExam findByName(String name);
}

