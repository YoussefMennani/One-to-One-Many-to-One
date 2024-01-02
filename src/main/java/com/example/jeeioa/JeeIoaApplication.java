package com.example.jeeioa;

import com.example.jeeioa.entities.AccountUM5R;
import com.example.jeeioa.entities.Classroom;
import com.example.jeeioa.entities.Student;
import com.example.jeeioa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JeeIoaApplication {
    private static StudentRepository studentRepository;

    @Autowired
    public JeeIoaApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(JeeIoaApplication.class, args);
        //AccountUM5R accountUM5R =new AccountUM5R(0L,"achraf@um5r.ma","achraf","K123456","234444");
        //Classroom classroom =new Classroom(0L,"IOA-2023","Reda","2023-2025");
        //Student student =new Student(0L,"Achraf","Harimza",accountUM5R,classroom);
        //studentRepository.save(student);
        //System.out.printf("students saved .....");
     /*   List<Student> students = studentRepository.findAll();

        for (Student std : students) {
            System.out.println("Student: " + std);
        }*/
    }

}
