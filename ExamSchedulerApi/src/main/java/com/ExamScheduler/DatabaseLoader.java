package com.ExamScheduler;

import com.ExamScheduler.dao.*;
import com.ExamScheduler.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final ClassDao classDao;
    private final ExamDao examDao;
    private final RoomDao roomDao;
    private final SchoolDao schoolDao;
    private final SubjectDao subjectDao;
    private final TimeSlotDao timeSlotDao;
    private final UnavailableDao unavailableDao;
    private final UserDao userDao;
    private final SessionDao sessionDao;
    private final PasswordEncoder bcryptEncoder;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    public DatabaseLoader(ClassDao classDao, ExamDao examDao, RoomDao roomDao, SchoolDao schoolDao, SubjectDao subjectDao, TimeSlotDao timeSlotDao, UnavailableDao unavailableDao, UserDao userDao, SessionDao sessionDao, PasswordEncoder bcryptEncoder) {
        this.classDao = classDao;
        this.examDao = examDao;
        this.roomDao = roomDao;
        this.schoolDao = schoolDao;
        this.subjectDao = subjectDao;
        this.timeSlotDao = timeSlotDao;
        this.unavailableDao = unavailableDao;
        this.sessionDao = sessionDao;
        this.userDao = userDao;

        this.bcryptEncoder = bcryptEncoder;
    }


    @Override
    public void run(String[] strings) throws Exception {
        // Class
        DAOClass class1 = new DAOClass("1A");
        classDao.save(class1);
        DAOClass class2 = new DAOClass("1B");
        classDao.save(class2);
        classDao.save(new DAOClass("1C"));

        // User
        userDao.save(new DAOUser("clarence.claux@gmail.com", bcryptEncoder.encode("password"),false,true,"Clarence","Claux"));
        DAOUser teacher1 = new DAOUser("teacher1@gmail.com",bcryptEncoder.encode("password"),true,false,"Teacher1","Mister");
        DAOUser teacher2 = new DAOUser("teacher2@gmail.com",bcryptEncoder.encode("password"),true,false,"Teacher2","Mister");
        userDao.save(teacher1);
        userDao.save(teacher2);
        userDao.save(new DAOUser("student@gmail.com",bcryptEncoder.encode("password"),false,false,"Student","Mister"));
        userDao.save(new DAOUser("ma.couchoud@gmail.com",bcryptEncoder.encode("password"),false,true,"Matteo","Couchoud"));


        // Room
        DAORoom room1 = new DAORoom("Room 1",50);
        roomDao.save(room1);
        roomDao.save(new DAORoom("Room 2",50));
        roomDao.save(new DAORoom("Room 3",150));

        // Exam
        DAOExam exam1 = new DAOExam("Partiels S1",simpleDateFormat.parse("2023-01-30"),simpleDateFormat.parse("2023-01-30"));
        examDao.save(exam1);
        examDao.save(new DAOExam("Partiels S2",simpleDateFormat.parse("2023-01-30"),simpleDateFormat.parse("2023-01-30")));
        examDao.save(new DAOExam("Rattrapage",simpleDateFormat.parse("2023-01-25"),simpleDateFormat.parse("2023-01-25")));

        // Subject
        DAOSubject subject1 = new DAOSubject("Maths");
        subjectDao.save(subject1);
        subjectDao.save(new DAOSubject("Cybersecurity"));
        subjectDao.save(new DAOSubject("ECE"));

        // School
        schoolDao.save(new DAOSchool("CY Tech"));

        // TimeSlot
        DAOTimeSlot timeslot1 = new DAOTimeSlot(simpleDateFormat.parse("2023-01-30"),simpleDateFormat.parse("2023-01-30"));
        timeSlotDao.save(timeslot1);
        timeSlotDao.save(new DAOTimeSlot(simpleDateFormat.parse("2023-01-30"),simpleDateFormat.parse("2023-01-30")));
        timeSlotDao.save(new DAOTimeSlot(simpleDateFormat.parse("2023-01-25"),simpleDateFormat.parse("2023-01-25")));

        // Unavailable
        unavailableDao.save(new DAOUnavailable(teacher1,timeslot1));



    }
}
