package com.ExamScheduler.solver.domain;

import java.util.List;

import com.ExamScheduler.model.DAORoom;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class TimeTable {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Timeslot> timeslotList;
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<DAORoom> roomList;
    @PlanningEntityCollectionProperty
    private List<Exam_Session> examSessionList;

    @PlanningScore
    private HardSoftScore score;

    // No-arg constructor required for OptaPlanner
    public TimeTable() {
    }

    public TimeTable(List<Timeslot> timeslotList, List<DAORoom> roomList, List<Exam_Session> examSessionList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.examSessionList = examSessionList;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public List<DAORoom> getRoomList() {
        return roomList;
    }

    public List<Exam_Session> getLessonList() {
        return examSessionList;
    }

    public HardSoftScore getScore() {
        return score;
    }

}
