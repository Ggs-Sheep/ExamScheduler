package com.ExamScheduler.controller;


import com.ExamScheduler.model.DAOTimeSlot;
import com.ExamScheduler.model.DAOUnavailable;
import com.ExamScheduler.model.DAOUser;
import com.ExamScheduler.model.UnavailableDTO;
import com.ExamScheduler.service.JwtUserDetailsService;
import com.ExamScheduler.service.TimeSlotService;
import com.ExamScheduler.service.UnavailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("unavailable")
public class UnavailableController {
    @Autowired
    private UnavailableService unavailableService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    TimeSlotService timeSlotService;

    @GetMapping("")
    public List<DAOUnavailable> list() {
        return unavailableService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOUnavailable> add(@RequestBody Map<String, Integer> payload) {
        try {
            long teacher_id = Long.valueOf(payload.get("teacher"));
            DAOUser teacher = jwtUserDetailsService.loadUSerbyId(teacher_id);
            long timeslot_id = Long.valueOf(payload.get("unavailability"));
            DAOTimeSlot timeslot = timeSlotService.findTimeSlotbyId(timeslot_id);
            DAOUnavailable unavailable = new DAOUnavailable(teacher,timeslot);
            return ResponseEntity.ok(unavailableService.save(unavailable));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOUnavailable>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOUnavailable> get(@PathVariable Integer id) {
        try {
            DAOUnavailable user = unavailableService.findUnavailablebyId(id);
            return new ResponseEntity<DAOUnavailable>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAOUnavailable>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOUnavailable user, @PathVariable Integer id) {
        try {
            DAOUnavailable existUser = unavailableService.findUnavailablebyId(id);
            user.setId(id);
            unavailableService.save(user);
            return new ResponseEntity<DAOUnavailable>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        unavailableService.deleteUSerbyId(id);
    }

}
