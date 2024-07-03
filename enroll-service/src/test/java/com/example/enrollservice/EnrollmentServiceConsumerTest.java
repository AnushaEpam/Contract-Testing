//It defines a contract (pact) between the EnrollmentService (consumer) and the StudentService (provider).
package com.example.enrollservice;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@SpringBootTest
@PactTestFor(providerName = "StudentService")
public class EnrollmentServiceConsumerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Pact(consumer = "EnrollmentService")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
            .given("A student exists")
            .uponReceiving("A request for a student")
                .path("/students/1")
                .method("GET")
            .willRespondWith()
                .status(200)
                .body("{\"id\": 1, \"name\": \"John Doe\", \"email\": \"john@example.com\"}")
            .given("A new student can be added")
            .uponReceiving("A request to add a student")
                .path("/students")
                .method("POST")
                .body("{\"name\": \"Jane Doe\", \"email\": \"jane@example.com\"}")
            .willRespondWith()
                .status(200)
                .body("{\"id\": 2, \"name\": \"Jane Doe\", \"email\": \"jane@example.com\"}")
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    public void testGetStudent() {
        Student student = restTemplate.getForObject("/students/1", Student.class);
        assertEquals(1L, student.getId());
        assertEquals("John Doe", student.getName());
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    public void testAddStudent() {
        Student newStudent = new Student("Jane Doe", "jane@example.com");
        Student addedStudent = restTemplate.postForObject("/students", newStudent, Student.class);
        assertEquals(2L, addedStudent.getId());
        assertEquals("Jane Doe", addedStudent.getName());
    }
}