package com.ExamScheduler.service;

import com.ExamScheduler.dao.UnavailableDao;
import com.ExamScheduler.model.DAOUnavailable;
import com.ExamScheduler.model.UnavailableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class UnavailableService {
    @Autowired
    private UnavailableDao unavailableDao;

    public DAOUnavailable findUnavailablebyId(long id) throws UsernameNotFoundException {
        DAOUnavailable unavailable = unavailableDao.findById(id);
        if (unavailable == null) {
            throw new UsernameNotFoundException("Unavailibility not find with id " + id);
        }
        return unavailable;
    }

    public DAOUnavailable save(DAOUnavailable user) throws DuplicateKeyException {
        DAOUnavailable newUser = new DAOUnavailable();
        newUser.setId((int) user.getId());
        newUser.setTeacher(user.getTeacher());
        newUser.setUnavailability(user.getUnavailability());
        return unavailableDao.save(newUser);
    }

    public Set<DAOUnavailable> findUnavailablebyTeacher(long id) throws UsernameNotFoundException {
        Set<DAOUnavailable> unavailable = unavailableDao.findByTeacher(id);
        if (unavailable == null) {
            throw new UsernameNotFoundException("Unavailability not find with teacher id " + id);
        }
        return unavailable;
    }

    public DAOUnavailable findUnavailablebyUnavailability(long id) throws UsernameNotFoundException {
        DAOUnavailable unavailable = unavailableDao.findByUnavailability(id);
        if (unavailable == null) {
            throw new UsernameNotFoundException("Unavailability not find with time slot id " + id);
        }
        return unavailable;
    }

    public ArrayList<DAOUnavailable> getAll() {
        return (ArrayList<DAOUnavailable>) unavailableDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOUnavailable user = unavailableDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Unavailability not found with id: " + id);
        }
        unavailableDao.delete(user);
    }
}
