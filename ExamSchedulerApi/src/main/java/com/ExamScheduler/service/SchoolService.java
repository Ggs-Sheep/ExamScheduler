package com.ExamScheduler.service;

import com.ExamScheduler.dao.SchoolDao;
import com.ExamScheduler.model.DAOSchool;
import com.ExamScheduler.model.SchoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public DAOSchool findSchoolbyId(long id) throws UsernameNotFoundException {
        DAOSchool found_class = schoolDao.findById(id);
        if (found_class == null) {
            throw new UsernameNotFoundException("School not find with id " + id);
        }
        return found_class;
    }
    public DAOSchool save(DAOSchool user) throws DuplicateKeyException {
        DAOSchool newUser = new DAOSchool();
        newUser.setId((int) user.getId());
        newUser.setName(user.getName());
        return schoolDao.save(newUser);
    }

    public DAOSchool findSchoolbyName(String name) throws UsernameNotFoundException {
        DAOSchool school = schoolDao.findByName(name);
        if (school == null) {
            throw new UsernameNotFoundException("School not find with name " + name);
        }
        return school;
    }

    public ArrayList<DAOSchool> getAll() {
        return (ArrayList<DAOSchool>) schoolDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOSchool user = schoolDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("School not found with id: " + id);
        }
        schoolDao.delete(user);
    }
}
