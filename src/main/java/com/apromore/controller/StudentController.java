package com.apromore.controller;

import com.apromore.dto.StudentDetails;
import com.apromore.dto.Students;
import com.apromore.exception.StudentException;
import com.apromore.service.StudentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/", produces = "application/json")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    @RateLimiter(name = "studentsRateLimit")
    public ResponseEntity<Students> getAllStudents(@RequestHeader final HttpHeaders requestHeaders,
                                                   final HttpServletRequest request) {
        return new ResponseEntity<>(studentService.fetchStudents(), HttpStatus.OK);
    }

    @GetMapping("/student/{roll}")
    @RateLimiter(name = "studentsRateLimit")
    public ResponseEntity<StudentDetails> getStudent(@PathVariable("roll") final Integer studentRoll,
                                                    @RequestHeader final HttpHeaders requestHeaders,
                                                   final HttpServletRequest request) throws StudentException {
        return new ResponseEntity<>(studentService.fetchStudent(studentRoll), HttpStatus.OK);
    }

    @PostMapping("/students")
    @RateLimiter(name = "studentsRateLimit")
    public ResponseEntity<Students> createStudents(@RequestBody final Students createStudentsRequest,
                                                   @RequestHeader final HttpHeaders requestHeaders,
                                                   final HttpServletRequest request) {

        return new ResponseEntity<>(studentService.createStudents(createStudentsRequest), HttpStatus.OK);

    }

    @PutMapping("/student")
    @RateLimiter(name = "studentsRateLimit")
    public ResponseEntity<StudentDetails> updateStudent(@RequestBody final StudentDetails updateStudentDetails,
                                                        @RequestHeader final HttpHeaders requestHeaders,
                                                        final HttpServletRequest request) throws StudentException {
        return new ResponseEntity<>(studentService.updateStudent(updateStudentDetails), HttpStatus.OK);
    }

    @DeleteMapping("/student/{roll}")
    @RateLimiter(name = "studentsRateLimit")
    public ResponseEntity<String> deleteStudent(@PathVariable("roll") final Integer studentRoll,
                                                @RequestHeader final HttpHeaders requestHeaders,
                                                final HttpServletRequest request) throws StudentException {
        studentService.deleteStudent(studentRoll);

        return new ResponseEntity<>("Student with roll " + studentRoll + " successfully deleted", HttpStatus.OK);

    }


}
