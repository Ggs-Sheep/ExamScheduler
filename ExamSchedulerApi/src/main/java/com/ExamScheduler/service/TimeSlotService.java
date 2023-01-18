package com.ExamScheduler.service;

import com.ExamScheduler.dao.TimeSlotDao;
import com.ExamScheduler.model.DAOTimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotDao timeSlotDao;

    public DAOTimeSlot findTimeSlotbyId(long id) throws UsernameNotFoundException {
        DAOTimeSlot found_class = timeSlotDao.findById(id);
        if (found_class == null) {
            throw new UsernameNotFoundException("Time Slot not find with id " + id);
        }
        return found_class;
    }
    public DAOTimeSlot save(DAOTimeSlot user) throws DuplicateKeyException {
        DAOTimeSlot newUser = new DAOTimeSlot();
        newUser.setId((int) user.getId());
        newUser.setStart_date(user.getStart_date());
        newUser.setEnd_date(user.getEnd_date());
        return timeSlotDao.save(newUser);
    }

    public ArrayList<DAOTimeSlot> getAll() {
        return (ArrayList<DAOTimeSlot>) timeSlotDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOTimeSlot user = timeSlotDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Time Slot not found with id: " + id);
        }
        timeSlotDao.delete(user);
    }

//    public DAOTimeSlot findTimeSlotByStart_dateAndEnd_date(Date start_date, Date end_date) throws UsernameNotFoundException {
//        DAOTimeSlot found_class = timeSlotDao.findByStart_dateAndEnd_date(start_date, end_date);
//        if (found_class == null) {
//            throw new UsernameNotFoundException("Time Slot not foind ");
//        }
//        return found_class;
//    }
}
