package com.ExamScheduler.service;

import com.ExamScheduler.dao.SessionDao;
import com.ExamScheduler.model.DAOSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class SessionService {
    @Autowired
    private SessionDao sessionDao;

    public DAOSession findSessionbyId(long id) throws UsernameNotFoundException {
        DAOSession session = sessionDao.findById(id);
        if (session == null) {
            throw new UsernameNotFoundException("Session not find with id " + id);
        }
        return session;
    }

    public DAOSession save(DAOSession user) throws DuplicateKeyException {
        DAOSession newUser = new DAOSession();
        newUser.setId(user.getId());
        newUser.setTeacher(user.getTeacher());
        newUser.setDate(user.getDate());
        newUser.setSubject(user.getSubject());
        newUser.setClassroom(user.getClassroom());
        newUser.setRoom(user.getRoom());
        newUser.setExam(user.getExam());
        return sessionDao.save(newUser);
    }

    public Set<DAOSession> findSessionbyTeacher(long id) throws UsernameNotFoundException {
        Set<DAOSession> sessions = sessionDao.findByTeacher(id);
        if (sessions == null) {
            throw new UsernameNotFoundException("Session not find with teacher id " + id);
        }
        return sessions;
    }

    public Set<DAOSession> findSessionbyDate(long id) throws UsernameNotFoundException {
        Set<DAOSession> sessions = sessionDao.findByDate(id);
        if (sessions == null) {
            throw new UsernameNotFoundException("Session not find with date id " + id);
        }
        return sessions;
    }

    public Set<DAOSession> findSessionbyClass(long id) throws UsernameNotFoundException {
        Set<DAOSession> sessions = sessionDao.getAllByClassroom(id);
        if (sessions == null) {
            throw new UsernameNotFoundException("Session not find with class id " + id);
        }
        return sessions;
    }

    public Set<DAOSession> findSessionbyRoom(long id) throws UsernameNotFoundException {
        Set<DAOSession> sessions = sessionDao.findByRoom(id);
        if (sessions == null) {
            throw new UsernameNotFoundException("Session not find with room id " + id);
        }
        return sessions;
    }

    public ArrayList<DAOSession> getAll() {
        return (ArrayList<DAOSession>) sessionDao.findAll();
    }

    public void deleteUSerbyId(long id) throws UsernameNotFoundException {
        DAOSession user = sessionDao.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Session not found with id: " + id);
        }
        sessionDao.delete(user);
    }
}
