//This Enrollment class represents an enrollment of a student in a course.
package com.example.enrollservice;

public class Enrollment {
    private Long studentId;
    private String courseName;

    // Default constructor
    public Enrollment() {}

    // Parameterized constructor
    public Enrollment(Long studentId, String courseName) {
        this.studentId = studentId;
        this.courseName = courseName;
    }

    // Getter for studentId
    public Long getStudentId() {
        return studentId;
    }

    // Setter for studentId
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Setter for courseName
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
