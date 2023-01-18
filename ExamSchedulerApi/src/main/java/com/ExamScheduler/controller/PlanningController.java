package com.ExamScheduler.controller;

import com.ExamScheduler.model.*;
import com.ExamScheduler.service.*;
import com.ExamScheduler.solver.TimeTableApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("planning")
public class PlanningController {
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

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/generate")
    public ResponseEntity<?> add(@RequestBody Map<String, ?> payload) {
        try {
            Map<String, String> exam_json = (Map<String, String>) payload.get("exam");
            DAOExam exam = new DAOExam(exam_json.get("name"), sdf.parse(exam_json.get("start_date")), sdf.parse(exam_json.get("end_date")));
            examService.save(exam);
            ArrayList<Integer> excluded_subjects_ids = (ArrayList<Integer>) payload.get("excluded_subjects");
            ArrayList<DAOSubject> excluded_subjects = new ArrayList<>();
            for (Integer id : excluded_subjects_ids) {
                excluded_subjects.add(subjectService.findSubjectbyId(id));
            }
            ArrayList<Integer> excluded_classes_ids = (ArrayList<Integer>) payload.get("excluded_classes");
            ArrayList<DAOClass> excluded_classes = new ArrayList<>();
            for (Integer id : excluded_classes_ids) {
                excluded_classes.add(classService.findClassbyId(id));
            }
            ArrayList<?> excluded_timeslots_json = (ArrayList<?>) payload.get("excluded_timeslots");
            ArrayList<DAOTimeSlot> excluded_timeslots = new ArrayList<>();
//            for (Object timeslot : excluded_timeslots_json) {
//                 Map<String,String> timeslot_json = (Map<String, String>) timeslot;
//                 DAOTimeSlot timeslot_dao = new DAOTimeSlot(sdf.parse(timeslot_json.get("start_time")),sdf.parse(timeslot_json.get("end_time")));
//                 timeSlotService.save(timeslot_dao);
//                 excluded_timeslots.add(timeSlotService.findTimeSlotByStart_dateAndEnd_date(sdf.parse(timeslot_json.get("start_time")),sdf.parse(timeslot_json.get("end_time"))));
//
//            }
            ArrayList<Integer> excluded_rooms_ids = (ArrayList<Integer>) payload.get("excluded_rooms");
            ArrayList<DAORoom> excluded_rooms = new ArrayList<>();
            for (Integer id : excluded_rooms_ids) {
                excluded_rooms.add(roomService.findRoombyId(id));
            }

            ArrayList<DAORoom> rooms = roomService.getAll();
            for (DAORoom room : new ArrayList<>(rooms)) {
                 if (excluded_rooms.contains(room)) {
                     rooms.remove(room);
                 }
            }
            ArrayList<DAOClass> classes = classService.getAll();
            for (DAOClass classe : new ArrayList<>(classes)) {
                if (excluded_classes.contains(classe)) {
                    classes.remove(classe);
                }
            }
            ArrayList<DAOSubject> subjects = subjectService.getAll();
            for (DAOSubject subject : new ArrayList<>(subjects)) {
                if (excluded_subjects.contains(subject)) {
                    subjects.remove(subject);
                }
            }

            LocalDate localDate_start = exam.getStart_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localDate_end = exam.getEnd_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = Duration.between(localDate_start.atStartOfDay(), localDate_end.atStartOfDay()).toDays();
            ArrayList<Date> days = new ArrayList<>();
            for (int i = 0; i < daysBetween; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(exam.getStart_date());
                calendar.add(Calendar.DAY_OF_MONTH,i);
                if (!excluded_timeslots.isEmpty()) {
                    for (DAOTimeSlot timeslot : excluded_timeslots) {
                        if (!(timeslot.getStart_date().equals(calendar.getTime()) || timeslot.getEnd_date().equals(calendar.getTime()))) {
                            days.add(calendar.getTime());
                        }
                    }
                } else {
                    days.add(calendar.getTime());
                }
            }

            ArrayList<DAOSession> sessions = TimeTableApp.main(new String[0],rooms, classes, subjects, exam, days, jwtUserDetailsService.getTeachers());
            System.out.println("sessions = " + sessions.isEmpty());
            for (DAOSession session : sessions) {
//                examService.save(exam);
//                session.setExam(exam);
//                timeSlotService.save(session.getDate());
                sessionService.save(session);
            }

            return ResponseEntity.ok(sessions);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAORoom>(HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
