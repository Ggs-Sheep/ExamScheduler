package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAOTimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimeSlotDao extends CrudRepository<DAOTimeSlot, Integer> {
    DAOTimeSlot findById(long id);

}

