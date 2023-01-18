package com.ExamScheduler.service;

import com.ExamScheduler.dao.ClassDao;
import com.ExamScheduler.model.ClassDTO;
import com.ExamScheduler.model.DAOClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService {
    @Autowired
    private ClassDao classDao;

    public DAOClass findClassbyId(long id) throws UsernameNotFoundException {
        DAOClass found_class = classDao.findById(id);
        if (found_class == null) {
            throw new UsernameNotFoundException("Class not find with id " + id);
        }
        return found_class;
    }
    public DAOClass save(DAOClass user) throws DuplicateKeyException {
        DAOClass newUser = new DAOClass();
        newUser.setId((int) user.getId());
        newUser.setName(user.getName());
        return classDao.save(newUser);
    }

    public DAOClass findClassbyName(String name) throws UsernameNotFoundException {
        DAOClass found_class = classDao.findByName(name);
        if (found_class == null) {
            throw new UsernameNotFoundException("Class not find with name " + name);
        }
        return found_class;
    }

    public ArrayList<DAOClass> getAll() {
        return (ArrayList<DAOClass>) classDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOClass user = classDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Class not found with id: " + id);
        }
        classDao.delete(user);
    }
}
