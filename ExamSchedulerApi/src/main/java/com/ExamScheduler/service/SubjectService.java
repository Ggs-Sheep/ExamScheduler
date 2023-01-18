package com.ExamScheduler.service;

import com.ExamScheduler.dao.SubjectDao;
import com.ExamScheduler.model.DAOSubject;
import com.ExamScheduler.model.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {
    @Autowired
    private SubjectDao subjectDAO;

    public DAOSubject findSubjectbyId(long id) throws UsernameNotFoundException {
        DAOSubject subject = subjectDAO.findById(id);
        if (subject == null) {
            throw new UsernameNotFoundException("Class not find with id " + id);
        }
        return subject;
    }
    public DAOSubject save(DAOSubject user) throws DuplicateKeyException {
        DAOSubject newUser = new DAOSubject();
        newUser.setId((int) user.getId());
        newUser.setName(user.getName());
        return subjectDAO.save(newUser);
    }

    public DAOSubject findSubjectbyName(String name) throws UsernameNotFoundException {
        DAOSubject found_class = subjectDAO.findByName(name);
        if (found_class == null) {
            throw new UsernameNotFoundException("Class not find with subject " + name);
        }
        return found_class;
    }

    public ArrayList<DAOSubject> getAll() {
        return (ArrayList<DAOSubject>) subjectDAO.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOSubject user = subjectDAO.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Class not found with id: " + id);
        }
        subjectDAO.delete(user);
    }
}
