package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOSchool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends CrudRepository<DAOSchool, Integer> {
    DAOSchool findById(long id);

    DAOSchool findByName(String name);

}
