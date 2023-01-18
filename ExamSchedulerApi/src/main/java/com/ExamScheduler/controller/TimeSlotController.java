package com.ExamScheduler.controller;


import com.ExamScheduler.model.DAOTimeSlot;
import com.ExamScheduler.model.TimeSlotDTO;
import com.ExamScheduler.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("timeslot")
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("")
    public List<DAOTimeSlot> list() {
        return timeSlotService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOTimeSlot> add(@RequestBody DAOTimeSlot slot) {
        try {
            return ResponseEntity.ok(timeSlotService.save(slot));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOTimeSlot> get(@PathVariable Integer id) {
        try {
            DAOTimeSlot user = timeSlotService.findTimeSlotbyId(id);
            return new ResponseEntity<DAOTimeSlot>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOTimeSlot>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOTimeSlot user, @PathVariable Integer id) {
        try {
            DAOTimeSlot existUser = timeSlotService.findTimeSlotbyId(id);
            user.setId(id);
            timeSlotService.save(user);
            return new ResponseEntity<DAOTimeSlot>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        timeSlotService.deleteUSerbyId(id);
    }

}
