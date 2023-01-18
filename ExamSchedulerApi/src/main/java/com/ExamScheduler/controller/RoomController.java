package com.ExamScheduler.controller;

import com.ExamScheduler.model.DAORoom;
import com.ExamScheduler.model.RoomDTO;
import com.ExamScheduler.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public List<DAORoom> list() {
        return roomService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<DAORoom> add(@RequestBody DAORoom room) {
        try {
            return ResponseEntity.ok(roomService.save(room));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAORoom>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DAORoom> get(@PathVariable Integer id) {
        try {
            DAORoom user = roomService.findRoombyId(id);
            return new ResponseEntity<DAORoom>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DAORoom>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DAORoom user, @PathVariable Integer id) {
        try {
            DAORoom existUser = roomService.findRoombyId(id);
            user.setId(id);
            roomService.save(user);
            return new ResponseEntity<DAORoom>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roomService.deleteUSerbyId(id);
    }

}
