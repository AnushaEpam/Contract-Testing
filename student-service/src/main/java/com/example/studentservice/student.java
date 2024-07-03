//This Student class serves as a model or data structure to represent student information 

package com.example.studentservice;

public class Student {
    private Long id;
    private String name;
    private String email;
    // Default constructor
    public Student() {}

    // Constructor with name and email
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter and setter for id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getter and setter for name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Getter and setter for email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
}