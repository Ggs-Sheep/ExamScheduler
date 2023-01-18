package com.ExamScheduler.service;


import com.ExamScheduler.dao.RoomDao;
import com.ExamScheduler.model.DAORoom;
import com.ExamScheduler.model.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomService {
    @Autowired
    private RoomDao roomDao;

    public DAORoom findRoombyId(long id) throws UsernameNotFoundException {
        DAORoom found_class = roomDao.findById(id);
        if (found_class == null) {
            throw new UsernameNotFoundException("Room not find with id " + id);
        }
        return found_class;
    }
    public DAORoom save(DAORoom user) throws DuplicateKeyException {
        DAORoom newUser = new DAORoom();
        newUser.setId((int) user.getId());
        newUser.setName(user.getName());
        newUser.setCapacity(user.getCapacity());
        return roomDao.save(newUser);
    }

    public DAORoom findRoombyName(String name) throws UsernameNotFoundException {
        DAORoom found_class = roomDao.findByName(name);
        if (found_class == null) {
            throw new UsernameNotFoundException("Room not find with name " + name);
        }
        return found_class;
    }

    public ArrayList<DAORoom> getAll() {
        return (ArrayList<DAORoom>) roomDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAORoom user = roomDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Room not found with id: " + id);
        }
        roomDao.delete(user);
    }
}

