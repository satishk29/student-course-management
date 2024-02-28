package com.apromore.service;

import java.util.List;
import java.util.stream.Collectors;

import com.apromore.dto.CourseDetails;
import com.apromore.dto.Courses;
import com.apromore.dto.StudentDetails;
import com.apromore.dto.Students;
import com.apromore.entity.Student;
import com.apromore.exception.CourseException;
import com.apromore.exception.StudentException;
import com.apromore.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apromore.entity.Course;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;

	public Courses createCourses(Courses createCoursesRequest) {
		log.debug("Processing create courses request");

		List<Course> coursesToBeCreatedInDb = createCoursesRequest.getCourses().stream().map(courseDetails -> {
			return Course.builder()
					.courseName(courseDetails.getCourseName())
					.courseCode(courseDetails.getCourseCode())
					.courseDescription(courseDetails.getCourseDescription())
					.build();
		}).collect(Collectors.toList());

		List<Course> coursesCreatedInDb = courseRepository.saveAll(coursesToBeCreatedInDb);

		List<CourseDetails> courseDetails = coursesCreatedInDb.stream().map(course -> CourseDetails.builder()
				.courseName(course.getCourseName())
				.courseCode(course.getCourseCode())
				.courseDescription(course.getCourseDescription())
				.build()).collect(Collectors.toList());

		return Courses.builder().courses(courseDetails).build();

	}
	public Courses fetchCourses() {
		List<Course> courseList =  courseRepository.findAll();

		List<CourseDetails> courseDetailsList = courseList.stream().map(course -> {
			return CourseDetails.builder().courseName(course.getCourseName()).courseCode(course.getCourseCode())
					.courseDescription(course.getCourseDescription()).build();
		}).collect(Collectors.toList());

		return Courses.builder().courses(courseDetailsList).build();
	}

	public CourseDetails fetchCourse(String courseName) {

		Course course =  courseRepository.findByCourseName(courseName);

		return CourseDetails.builder()
				.courseName(course.getCourseName())
				.courseCode(course.getCourseCode())
				.courseDescription(course.getCourseDescription())
				.build();
	}

	public CourseDetails updateCourse(CourseDetails courseDetailsToBeUpdated) throws CourseException {
		if (courseRepository.findById(courseDetailsToBeUpdated.getCourseName()) != null) {

			Course courseToBeUpdatedInDb = Course.builder()
					.courseName(courseDetailsToBeUpdated.getCourseName())
					.courseCode(courseDetailsToBeUpdated.getCourseCode())
					.courseDescription(courseDetailsToBeUpdated.getCourseDescription())
					.build();

			Course courseUpdatedInDb = courseRepository.save(courseToBeUpdatedInDb);

			return CourseDetails.builder().courseName(courseUpdatedInDb.getCourseName())
					.courseCode(courseUpdatedInDb.getCourseCode())
					.courseDescription(courseUpdatedInDb.getCourseDescription())
					.build();

		} else {
			throw new CourseException("Course does not exist with name " + courseDetailsToBeUpdated.getCourseName());
		}
	}

	public void deleteCourse(String courseName) throws CourseException {
		if (courseRepository.findByCourseName(courseName) != null) {

			courseRepository.deleteById(courseName);

		} else {
			throw new CourseException("Course does not exist with name " + courseName);
		}
	}
}
