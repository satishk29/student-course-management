package com.apromore.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.apromore.entity.Student;
import com.apromore.exception.StudentException;
import com.apromore.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apromore.dto.StudentDetails;
import com.apromore.dto.Students;
import com.apromore.entity.Course;

import lombok.extern.slf4j.Slf4j;
import com.apromore.repository.StudentRepository;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseService courseService;
	

	public Students createStudents(Students studentsRequest) {
		
		log.debug("Processing create students request");

		List<Student> studentsToBeCreatedInDb = studentsRequest.getStudents().stream().map(studentDetails -> {
			List<Course> courses = getCourses(studentDetails);
			return Student.builder()
					.studentName(studentDetails.getStudentName())
					.studentAge(studentDetails.getStudentAge())
					.courses(courses)
					.build();
		}).collect(Collectors.toList());

		List<Student> studentsCreatedInDb = studentRepository.saveAll(studentsToBeCreatedInDb);

		//Converting Domain model to DTO. This helps in cases where not all fields from DB need to be passed to clients
		List<StudentDetails> studentDetails = studentsCreatedInDb.stream().map(student -> StudentDetails.builder()
				.studentRoll(student.getStudentRoll())
				.studentName(student.getStudentName())
				.studentAge(student.getStudentAge())
				.courses(student.getCourses().stream().map(Course::getCourseName).collect(Collectors.toList()))
       			.build()).collect(Collectors.toList());

		return Students.builder().students(studentDetails).build();
	}

	public Students fetchStudents() {
		List<StudentDetails> studentDetails = studentRepository.findAll().stream()
			.map(student -> {
				List<Course> courses = student.getCourses();
				List<String> courseNames = courses.stream().map(Course::getCourseName).collect(Collectors.toList());
				return StudentDetails.builder()
					.studentRoll(student.getStudentRoll())
					.studentName(student.getStudentName())
					.studentAge(student.getStudentAge())
					.courses(courseNames)
					.build();
				}).collect(Collectors.toList());

		//Converting domain model to DTO
		return Students.builder().students(studentDetails).build();
	}

	public StudentDetails fetchStudent(Integer studentRoll) throws StudentException {
		Optional<Student> student = studentRepository.findById(studentRoll);

		if (student.isPresent())
		{		return StudentDetails.builder()
				.studentRoll(student.get().getStudentRoll())
				.studentName(student.get().getStudentName())
				.studentAge(student.get().getStudentAge())
				.courses(student.get().getCourses().stream().map(Course::getCourseName).collect(Collectors.toList()))
				.build();
		} else {
			throw new StudentException("Student does not exist with roll " + studentRoll);
		}
	}

	public StudentDetails updateStudent(StudentDetails studentDetailsToBeUpdated) throws StudentException {
		if (studentRepository.findById(studentDetailsToBeUpdated.getStudentRoll()).isPresent()) {

			Student studentToBeUpdatedInDb = Student.builder()
					.studentRoll(studentDetailsToBeUpdated.getStudentRoll())
					.studentName(studentDetailsToBeUpdated.getStudentName())
					.studentAge(studentDetailsToBeUpdated.getStudentAge())
					.courses(getCourses(studentDetailsToBeUpdated))
					.build();
			studentRepository.save(studentToBeUpdatedInDb);

			return studentDetailsToBeUpdated;
		} else {
			throw new StudentException("Student does not exist with roll " + studentDetailsToBeUpdated.getStudentRoll());
		}
	}

	public void deleteStudent(Integer studentRoll) throws StudentException {
		if (studentRepository.findById(studentRoll).isPresent()) {

			studentRepository.deleteById(studentRoll);

		} else {
			throw new StudentException("Student does not exist with roll " + studentRoll);
		}
	}

	private List<Course> getCourses(StudentDetails studentDetails) {
		return  studentDetails.getCourses() == null || studentDetails.getCourses().isEmpty()  ? Collections.emptyList() :
				studentDetails.getCourses().stream()
				.map(course -> courseRepository.findByCourseName(course))
				.filter(Objects::nonNull).collect(Collectors.toList());
	}

}
