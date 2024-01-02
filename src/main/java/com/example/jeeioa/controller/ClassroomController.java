package com.example.jeeioa.controller;

import com.example.jeeioa.entities.Classroom;
import com.example.jeeioa.entities.Student;
import com.example.jeeioa.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms/")
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;


    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassRoom() {
        List<Classroom> classrooms = classroomRepository.findAll();
        if (classrooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(classrooms);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);
        return classroomOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom savedClassroom = classroomRepository.save(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClassroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom updatedClassroom) {
        Optional<Classroom> existingClassroomOptional = classroomRepository.findById(id);

        if (existingClassroomOptional.isPresent()) {
            updatedClassroom.setId(id);
            Classroom savedClassroom = classroomRepository.save(updatedClassroom);
            return ResponseEntity.ok(savedClassroom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);

        if (classroomOptional.isPresent()) {
            classroomRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
