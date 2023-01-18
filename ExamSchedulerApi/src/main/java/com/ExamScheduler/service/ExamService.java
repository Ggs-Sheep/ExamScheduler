package com.ExamScheduler.service;

import com.ExamScheduler.dao.ExamDao;
import com.ExamScheduler.dao.SessionDao;
import com.ExamScheduler.model.DAOExam;
import com.ExamScheduler.model.DAOSession;
import com.ExamScheduler.model.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;

    @Autowired
    private SessionDao sessionDao;

    public DAOExam findExambyId(long id) throws UsernameNotFoundException {
        DAOExam found_class = examDao.findById(id);
        if (found_class == null) {
            throw new UsernameNotFoundException("Exam not find with id " + id);
        }
        return found_class;
    }
    public DAOExam save(DAOExam user) throws DuplicateKeyException {
        DAOExam newUser = new DAOExam();
        newUser.setId((int) user.getId());
        newUser.setName(user.getName());
        newUser.setStart_date(user.getStart_date());
        newUser.setEnd_date(user.getEnd_date());
        return examDao.save(newUser);
    }

    public DAOExam findExambyName(String name) throws UsernameNotFoundException {
        DAOExam found_class = examDao.findByName(name);
        if (found_class == null) {
            throw new UsernameNotFoundException("Exam not find with name " + name);
        }
        return found_class;
    }

    public ArrayList<DAOExam> getAll() {
        return (ArrayList<DAOExam>) examDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOExam user = examDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Exam not found with id: " + id);
        }
        examDao.delete(user);
    }

    public Set<DAOSession> getAllSession(long id) throws UsernameNotFoundException {
        DAOExam exam = examDao.findById(id);
        if (exam == null) {
            throw new UsernameNotFoundException("Exam not found with id: " + id);
        }
        return sessionDao.findByExam(id);

    }
}

