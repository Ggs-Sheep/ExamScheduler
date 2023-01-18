package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao extends CrudRepository<DAOClass, Integer> {
    DAOClass findById(long id);

    DAOClass findByName(String name);
}
