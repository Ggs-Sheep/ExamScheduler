package com.ExamScheduler.controller;

import com.ExamScheduler.model.DAOUser;
import com.ExamScheduler.model.UserDTO;
import com.ExamScheduler.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUserDetailsService userService;

    @GetMapping("")
    public List<DAOUser> list() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOUser> get(@PathVariable Integer id) {
        try {
            DAOUser user = userService.loadUSerbyId(id);
            return new ResponseEntity<DAOUser>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOUser>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO user, @PathVariable Integer id) {
        try {
            DAOUser existUser = userService.loadUSerbyId(id);
            user.setId(id);
            userService.save(user);
            return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        userService.deleteUSerbyId(id);
    }

    @GetMapping("/admin")
    public List<DAOUser> admins(){
        return userService.getAdmins();
    }

    @GetMapping("/teacher")
    public List<DAOUser> teacher(){
        return userService.getTeachers();
    }

    @GetMapping("/student")
    public List<DAOUser> student(){
        return userService.getStudents();
    }

}
