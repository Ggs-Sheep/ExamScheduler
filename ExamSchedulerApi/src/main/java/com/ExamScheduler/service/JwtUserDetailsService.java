package com.ExamScheduler.service;

import java.util.ArrayList;
import java.util.Optional;

import com.ExamScheduler.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ExamScheduler.dao.UserDao;
import com.ExamScheduler.model.DAOUser;
import com.ExamScheduler.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		DAOUser user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}
	
	public DAOUser save(UserDTO user) throws  DuplicateKeyException{
		DAOUser newUser = new DAOUser();
		newUser.setId(user.getId());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAdmin(user.isAdmin());
		newUser.setTeacher(user.isTeacher());
		newUser.setFirst_name(user.getFirst_name());
		newUser.setLast_name(user.getLast_name());
		return userDao.save(newUser);
	}

	public DAOUser loadUSerbyId(long id) throws UsernameNotFoundException {
		DAOUser user = userDao.findById(id);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with id: " + id);
		}
		return user;
	}

	public void deleteUSerbyId(long id) throws UsernameNotFoundException {
		DAOUser user = userDao.findById(id);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with id: " + id);
		}
		userDao.delete(user);
	}

	public ArrayList<DAOUser> getAll() {
		return (ArrayList<DAOUser>) userDao.findAll();
	}

	public DAOUser loadUSerbyName(String email) throws UsernameNotFoundException {
		DAOUser user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with id: " + email);
		}
		return user;
	}

	public ArrayList<DAOUser> getAdmins() {
		return (ArrayList<DAOUser>) userDao.findDAOUserByAdmin(true);
	}

	public ArrayList<DAOUser> getTeachers() {
		return (ArrayList<DAOUser>) userDao.findDAOUserByTeacher(true);
	}

	public ArrayList<DAOUser> getStudents() {
		return (ArrayList<DAOUser>) userDao.findDAOUserByAdminAndTeacher(false,false);
	}
}