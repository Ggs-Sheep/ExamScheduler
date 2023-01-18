package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOUnavailable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UnavailableDao extends CrudRepository<DAOUnavailable, Integer> {
    DAOUnavailable findById(long id);

    Set<DAOUnavailable> findByTeacher(long id);

    DAOUnavailable findByUnavailability(long id);
}
