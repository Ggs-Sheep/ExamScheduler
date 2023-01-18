package com.ExamScheduler.dao;

import com.ExamScheduler.model.DAORoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends CrudRepository<DAORoom, Integer> {
    DAORoom findById(long id);

    DAORoom findByName(String name);

}
