package com.apromore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apromore.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, String>{

		Course findByCourseName(String courseName);
	
}