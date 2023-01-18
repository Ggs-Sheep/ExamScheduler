package com.ExamScheduler.controller;

import com.ExamScheduler.model.DAOSubject;
import com.ExamScheduler.model.SubjectDTO;
import com.ExamScheduler.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    public List<DAOSubject> list() {
        return subjectService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOSubject> add(@RequestBody DAOSubject classroom) {
        try {
            return ResponseEntity.ok(subjectService.save(classroom));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOSubject>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOSubject> get(@PathVariable Integer id) {
        try {
            DAOSubject user = subjectService.findSubjectbyId(id);
            return new ResponseEntity<DAOSubject>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOSubject>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOSubject user, @PathVariable Integer id) {
        try {
            DAOSubject existUser = subjectService.findSubjectbyId(id);
            user.setId(id);
            subjectService.save(user);
            return new ResponseEntity<DAOSubject>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            subjectService.deleteUSerbyId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
