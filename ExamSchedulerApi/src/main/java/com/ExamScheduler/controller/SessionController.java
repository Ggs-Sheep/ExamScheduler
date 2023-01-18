package com.ExamScheduler.controller;

import com.ExamScheduler.model.*;
import com.ExamScheduler.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ClassService classService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ExamService examService;

    @GetMapping("")
    public List<DAOSession> list() {
        return sessionService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAOSession> add(@RequestBody Map<String, Integer> payload) {
        try {
            long teacher_id = Long.valueOf(payload.get("teacher"));
            DAOUser teacher = jwtUserDetailsService.loadUSerbyId(teacher_id);
            long timeslot_id = Long.valueOf(payload.get("date"));
            DAOTimeSlot timeslot = timeSlotService.findTimeSlotbyId(timeslot_id);
            long subject_id = Long.valueOf(payload.get("subject"));
            DAOSubject subject = subjectService.findSubjectbyId(subject_id);
            long class_id = Long.valueOf(payload.get("class"));
            DAOClass classroom = classService.findClassbyId(class_id);
            long room_id = Long.valueOf(payload.get("room"));
            DAORoom room = roomService.findRoombyId(room_id);
            long exam_id = Long.valueOf(payload.get("exam"));
            DAOExam exam = examService.findExambyId(exam_id);
            DAOSession session = new DAOSession(timeslot,subject,classroom,teacher,room,exam);
            return ResponseEntity.ok(sessionService.save(session));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAOSession> get(@PathVariable long id) {
        try {
            DAOSession user = sessionService.findSessionbyId(id);
            return new ResponseEntity<DAOSession>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAOSession user, @PathVariable long id) {
        try {
            DAOSession existUser = sessionService.findSessionbyId(id);
            user.setId(id);
            sessionService.save(user);
            return new ResponseEntity<DAOSession>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sessionService.deleteUSerbyId(id);
    }

}
