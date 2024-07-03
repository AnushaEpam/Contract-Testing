//This controller provides two main functionalities:
//Retrieving a student by ID (GET /students/{id})
//Adding a new student (POST /students)

package com.example.studentservice;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private Map<Long, Student> students = new HashMap<>();
    private long idCounter = 1;

    // GET endpoint to retrieve a student by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return students.get(id);
    }

    // POST endpoint to add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        student.setId(idCounter++);
        students.put(student.getId(), student);
        return student;
    }
}