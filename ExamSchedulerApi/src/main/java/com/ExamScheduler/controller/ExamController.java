package com.ExamScheduler.controller;

import com.ExamScheduler.model.DAOExam;
import com.ExamScheduler.model.DAOSession;
import com.ExamScheduler.model.ExamDTO;
import com.ExamScheduler.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("")
    public List<DAOExam> list() {
        return examService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOExam> add(@RequestBody DAOExam exam) {
        try {
            return ResponseEntity.ok(examService.save(exam));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOExam>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOExam> get(@PathVariable Integer id) {
        try {
            DAOExam user = examService.findExambyId(id);
            return new ResponseEntity<DAOExam>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOExam>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOExam user, @PathVariable Integer id) {
        try {
            DAOExam existUser = examService.findExambyId(id);
            user.setId(id);
            examService.save(user);
            return new ResponseEntity<DAOExam>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        examService.deleteUSerbyId(id);
    }

    @GetMapping("/{id}/session")
    public Set<DAOSession> listSession(@PathVariable Integer id) {
        return examService.getAllSession(id);
    }

}

