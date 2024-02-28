package com.apromore.service;

import com.apromore.dto.StudentDetails;
import com.apromore.dto.Students;
import com.apromore.entity.Course;
import com.apromore.entity.Student;
import com.apromore.exception.StudentException;
import com.apromore.repository.CourseRepository;
import com.apromore.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createStudents_givenValidStudentDetails_dbCreationIsSuccessful() {
        StudentDetails studentDetails = StudentDetails.builder().studentRoll(1).studentName("test1").studentAge(10).build();
        Students students = Students.builder().students(List.of(studentDetails)).build();

        List<Student> studentsCreatedInDb = new ArrayList<>();
        studentsCreatedInDb.add(Student.builder().studentRoll(1).studentName("test1").studentAge(10)
                        .courses(List.of(Course.builder().courseName("course1").courseCode("course1").courseDescription("course1").build()))
                .build());

        when(studentRepository.saveAll(any())).thenReturn(studentsCreatedInDb);

        Students students1 = studentService.createStudents(students);

        assertEquals("test1", students1.getStudents().get(0).getStudentName());
        assertEquals(1, students1.getStudents().get(0).getStudentRoll());
        assertEquals(10, students1.getStudents().get(0).getStudentAge());
    }

    @Test
    void fetchStudent_givenValidStudentRoll_returnsStudentDetails() throws StudentException {

        Student student = Student.builder().studentRoll(1).studentName("test1").studentAge(10)
                .courses(List.of(Course.builder().courseName("course1").courseCode("course1").courseDescription("course1").build()))
                .build();

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        StudentDetails studentDetails = studentService.fetchStudent(1);

        assertEquals("test1", student.getStudentName());
        assertEquals(1, student.getStudentRoll());
        assertEquals(10, student.getStudentAge());
    }

    @Test
    void fetchStudent_givenInvalidValidStudentRoll_returnsException() throws StudentException {

        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        StudentException exception = assertThrows(StudentException.class, () -> studentService.fetchStudent(1));

        assertEquals("Student does not exist with roll 1", exception.getMessage());
    }

}
