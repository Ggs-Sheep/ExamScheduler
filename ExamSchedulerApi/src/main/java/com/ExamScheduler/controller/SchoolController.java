package com.ExamScheduler.controller;


import com.ExamScheduler.model.DAOSchool;
import com.ExamScheduler.model.SchoolDTO;
import com.ExamScheduler.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("")
    public List<DAOSchool> list() {
        return schoolService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOSchool> add(@RequestBody DAOSchool classroom) {
        try {
            return ResponseEntity.ok(schoolService.save(classroom));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOSchool>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOSchool> get(@PathVariable Integer id) {
        try {
            DAOSchool user = schoolService.findSchoolbyId(id);
            return new ResponseEntity<DAOSchool>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOSchool>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOSchool user, @PathVariable Integer id) {
        try {
            DAOSchool existUser = schoolService.findSchoolbyId(id);
            user.setId(id);
            schoolService.save(user);
            return new ResponseEntity<DAOSchool>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        schoolService.deleteUSerbyId(id);
    }

}
