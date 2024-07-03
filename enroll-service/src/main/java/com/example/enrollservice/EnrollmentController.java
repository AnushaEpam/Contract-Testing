//this controller provides two main functionalities:
//Enrolling a new student in a course (which also creates the student in the student service).
//Enrolling an existing student in a course.

package com.example.enrollservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private RestTemplate restTemplate;

    private final String studentServiceUrl = "http://localhost:8080/students";

    // POST endpoint to enroll a new student
    @PostMapping("/new")
    public Enrollment enrollNewStudent(@RequestBody Student student, @RequestParam String courseName) {
        Student newStudent = restTemplate.postForObject(studentServiceUrl, student, Student.class);
        return new Enrollment(newStudent.getId(), courseName);
    }

    // POST endpoint to enroll an existing student
    @PostMapping("/existing/{studentId}")
    public Enrollment enrollExistingStudent(@PathVariable Long studentId, @RequestParam String courseName) {
        Student existingStudent = restTemplate.getForObject(studentServiceUrl + "/" + studentId, Student.class);
        return new Enrollment(existingStudent.getId(), courseName);
    }
}