package com.example.jeeioa.controller;

import com.example.jeeioa.entities.AccountUM5R;
import com.example.jeeioa.entities.Classroom;
import com.example.jeeioa.entities.Student;
import com.example.jeeioa.repositories.AccountUM5RRepository;
import com.example.jeeioa.repositories.ClassroomRepository;
import com.example.jeeioa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private AccountUM5RRepository accountUM5RRepository;


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Check if Classroom ID is provided
        Long idClassRoom = student.getClassroom().getId();
        Long idAccount = student.getAccount().getId();

        Optional<Classroom> existingClassroom = idClassRoom!=null ? classroomRepository.findById(idClassRoom) : Optional.empty();
        Optional<AccountUM5R> existingAccount = idAccount!=null ? accountUM5RRepository.findById(idAccount) : Optional.empty();
        Student savedStudent;

        if (existingClassroom.isPresent()) {
            student.setClassroom(existingClassroom.get());
        }

        if (existingAccount.isPresent()) {
            student.setAccount(existingAccount.get());
        }

        if(existingAccount.isPresent() || existingClassroom.isPresent()){
            savedStudent = studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        }


        savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);

        if (existingStudentOptional.isPresent()) {
            updatedStudent.setId(id);
            Student savedStudent = studentRepository.save(updatedStudent);
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

