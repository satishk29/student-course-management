package com.apromore.controller;

import com.apromore.dto.CourseDetails;
import com.apromore.dto.Courses;
import com.apromore.exception.CourseException;
import com.apromore.service.CourseService;
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
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    @RateLimiter(name = "coursesRateLimit")
    public ResponseEntity<Courses> getAllCourses(@RequestHeader final HttpHeaders requestHeaders,
                                                             final HttpServletRequest request) {
        return new ResponseEntity<>(courseService.fetchCourses(), HttpStatus.OK);
    }

    @GetMapping("/courses/{name}")
    @RateLimiter(name = "coursesRateLimit")
    public ResponseEntity<CourseDetails> getCourse(@PathVariable("name") final String courseName,
                                                    @RequestHeader final HttpHeaders requestHeaders,
                                                   final HttpServletRequest request) {
        return new ResponseEntity<>(courseService.fetchCourse(courseName), HttpStatus.OK);
    }

    @PostMapping("/courses")
    @RateLimiter(name = "coursesRateLimit")
    public ResponseEntity<Courses> createCourses(@RequestBody final Courses createCourseRequest,
                                                 @RequestHeader final HttpHeaders requestHeaders,
                                                 final HttpServletRequest request) {

        return new ResponseEntity<>(courseService.createCourses(createCourseRequest), HttpStatus.OK);

    }

    @PutMapping("/course")
    @RateLimiter(name = "coursesRateLimit")
    public ResponseEntity<CourseDetails> updateCourse(@RequestBody final CourseDetails updateCourseDetails,
                                                        @RequestHeader final HttpHeaders requestHeaders,
                                                        final HttpServletRequest request) throws CourseException {
        return new ResponseEntity<>(courseService.updateCourse(updateCourseDetails), HttpStatus.OK);
    }

    @DeleteMapping("/course/{name}")
    @RateLimiter(name = "coursesRateLimit")
    public ResponseEntity<String> deleteCourse(@PathVariable("name") final String courseName,
                                                @RequestHeader final HttpHeaders requestHeaders,
                                                final HttpServletRequest request) throws CourseException {
        courseService.deleteCourse(courseName);

        return new ResponseEntity<>("Course with name " + courseName + " successfully deleted", HttpStatus.OK);

    }


}
