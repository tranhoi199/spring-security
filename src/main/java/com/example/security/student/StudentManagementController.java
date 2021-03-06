package com.example.security.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    private static final List<Student> studentList = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anan Smith")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return studentList;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));
    }
}
