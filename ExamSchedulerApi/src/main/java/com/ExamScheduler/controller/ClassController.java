package com.ExamScheduler.controller;

import com.ExamScheduler.model.ClassDTO;
import com.ExamScheduler.model.DAOClass;
import com.ExamScheduler.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("")
    public List<DAOClass> list() {
        return classService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOClass> add(@RequestBody DAOClass classroom) {
        try {
            return ResponseEntity.ok(classService.save(classroom));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOClass>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOClass> get(@PathVariable Integer id) {
        try {
            DAOClass user = classService.findClassbyId(id);
            return new ResponseEntity<DAOClass>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOClass>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOClass user, @PathVariable Integer id) {
        try {
            DAOClass existUser = classService.findClassbyId(id);
            user.setId(id);
            classService.save(user);
            return new ResponseEntity<DAOClass>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        classService.deleteUSerbyId(id);
    }

}
