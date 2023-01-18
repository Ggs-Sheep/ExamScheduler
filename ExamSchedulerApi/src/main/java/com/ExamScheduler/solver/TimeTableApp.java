package com.ExamScheduler.solver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.ExamScheduler.model.*;
import com.ExamScheduler.solver.domain.Exam_Session;
import com.ExamScheduler.solver.domain.TimeTable;
import com.ExamScheduler.solver.domain.Timeslot;
import com.ExamScheduler.solver.solver.TimeTableConstraintProvider;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TimeTableApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableApp.class);

    public static ArrayList<DAOSession> main(String[] args,ArrayList<DAORoom> rooms,
                            ArrayList<DAOClass> classes,ArrayList<DAOSubject> subjects,
                            DAOExam exam, ArrayList<Date> days,
                            ArrayList<DAOUser> teachers) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SolverFactory<TimeTable> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(TimeTable.class)
                .withEntityClasses(Exam_Session.class)
                .withConstraintProviderClass(TimeTableConstraintProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        // Load the problem
        TimeTable problem = generateDemoData(rooms,classes,subjects,exam, days,teachers);

        // Solve the problem
        Solver<TimeTable> solver = solverFactory.buildSolver();
        TimeTable solution = solver.solve(problem);


        // Visualize the solution
        ArrayList<DAOSession> sessions = printTimetable(solution);
        for (DAOSession session : sessions) {
            session.setExam(exam);
//            session.setId(new SessionKey(session.getDate().getId(), session.getExam().getId(), session.getSubject().getId()));
        }
        return sessions;
    }

    public static TimeTable generateDemoData(ArrayList<DAORoom> rooms,
                                             ArrayList<DAOClass> classes,ArrayList<DAOSubject> subjects,
                                              DAOExam exam, ArrayList<Date> days,
                                             ArrayList<DAOUser> teachers) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Timeslot> timeslotList = new ArrayList<>();
        for (Date date: days) {
            timeslotList.add(new Timeslot(date, LocalTime.of(8, 30), LocalTime.of(11, 30)));
            timeslotList.add(new Timeslot(date, LocalTime.of(13, 0), LocalTime.of(17, 30)));
        }

        List<Exam_Session> examSessionList = new ArrayList<>();

        long id = 0;
        for (DAOSubject subject: subjects) {
            for (DAOClass class_: classes) {
                examSessionList.add(new Exam_Session(id++,subject, teachers.get(teachers.size()-1), class_));
            }
        }

        return new TimeTable(timeslotList, rooms, examSessionList);
    }

    private static ArrayList<DAOSession> printTimetable(TimeTable timeTable) {
        ArrayList<DAOSession> sessions = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        LOGGER.info("");
        List<DAORoom> roomList = timeTable.getRoomList();
        List<Exam_Session> examSessionList = timeTable.getLessonList();
        Map<Timeslot, Map<DAORoom, List<Exam_Session>>> lessonMap = examSessionList.stream()
                .filter(examSession -> examSession.getTimeslot() != null && examSession.getRoom() != null)
                .collect(Collectors.groupingBy(Exam_Session::getTimeslot, Collectors.groupingBy(Exam_Session::getRoom)));
//        LOGGER.info("|            | " + roomList.stream()
//                .map(room -> String.format("%-10s", room.getName())).collect(Collectors.joining(" | ")) + " |");
//        LOGGER.info("|" + "------------|".repeat(roomList.size() + 1));
        for (Timeslot timeslot : timeTable.getTimeslotList()) {
            List<List<Exam_Session>> cellList = roomList.stream()
                    .map(room -> {
                        Map<DAORoom, List<Exam_Session>> byRoomMap = lessonMap.get(timeslot);
                        if (byRoomMap == null) {
                            return Collections.<Exam_Session>emptyList();
                        }
                        List<Exam_Session> cellExamSessionList = byRoomMap.get(room);
                        if (cellExamSessionList == null) {
                            return Collections.<Exam_Session>emptyList();
                        }
                        return cellExamSessionList;
                    })
                    .collect(Collectors.toList());

//            sessions.add(new DAOSession(
//                    new DAOTimeSlot(new Date(simpleDateFormat.format(timeslot.getStartTime())),new Date(simpleDateFormat.format(timeslot.getEndTime()))),
//
//            ))

//            LOGGER.info(" == HERE == ");
            for (Timeslot timeslot1 : timeTable.getTimeslotList()) {
                cellList.stream().map(cell -> {
                    if (cell.isEmpty()) {
                        return "            ";
                    }
                    Exam_Session examSession = cell.get(0);
                    String day = sdf.format(timeslot1.getDate());


                    Date date1 = timeslot1.getDate();
                    date1.setHours(timeslot1.getStartTime().getHour());
                    date1.setMinutes(timeslot1.getStartTime().getMinute());
                    Date date2 = timeslot1.getDate();
                    date2.setHours(timeslot1.getEndTime().getHour());
                    date2.setMinutes(timeslot1.getEndTime().getMinute());
                    return sessions.add(new DAOSession(
                            //new DAOTimeSlot(new Date(simpleDateFormat.format(timeslot1.getStartTime())),new Date(simpleDateFormat.format(timeslot1.getEndTime()))),
                            new DAOTimeSlot(date1,date2),
                            examSession.getSubject(),
                            examSession.getStudentGroup(),
                            examSession.getTeacher(),
                            examSession.getRoom()
                    ));
                    //String.format("%-10s", examSession.getSubject().getName() + " " + examSession.getRoom().getName() + " " + examSession.getTeacher().getFirst_name() + " " + examSession.getStudentGroup().getName());
                }).forEachOrdered(cell -> LOGGER.info("| " + cell));


            }




//            LOGGER.info("| " + String.format("%-10s",
//                    simpleDateFormat.format(timeslot.getDate()) + " " + timeslot.getStartTime()) + " | "
//                    + cellList.stream().map(cellLessonList -> String.format("%-10s",
//                            cellLessonList.stream().map(Exam_Session::getSubject).collect(Collectors.joining(", "))))
//                            .collect(Collectors.joining(" | "))
//                    + " |");
//            LOGGER.info("|            | "
//                    + cellList.stream().map(cellLessonList -> String.format("%-10s",
//                            cellLessonList.stream().map(Exam_Session::getTeacher).collect(Collectors.joining(", "))))
//                            .collect(Collectors.joining(" | "))
//                    + " |");
//            LOGGER.info("|            | "
//                    + cellList.stream().map(cellLessonList -> String.format("%-10s",
//                            cellLessonList.stream().map(Exam_Session::getStudentGroup).collect(Collectors.joining(", "))))
//                            .collect(Collectors.joining(" | "))
//                    + " |");
//            LOGGER.info("|" + "------------|".repeat(roomList.size() + 1));
        }
        List<Exam_Session> unassignedExamSessions = examSessionList.stream()
                .filter(examSession -> examSession.getTimeslot() == null || examSession.getRoom() == null)
                .collect(Collectors.toList());
        if (!unassignedExamSessions.isEmpty()) {
            LOGGER.info("");
            LOGGER.info("Unassigned lessons");
            for (Exam_Session examSession : unassignedExamSessions) {
                LOGGER.info("  " + examSession.getSubject() + " - " + examSession.getTeacher() + " - " + examSession.getStudentGroup());
            }
        }
        return sessions;
    }

}
