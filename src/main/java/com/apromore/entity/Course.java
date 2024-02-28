package com.apromore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
	
	@Id
	@Column(name="course_name")
	private String courseName;
	@Column(name="course_code")
	private String courseCode;
	@Column(name="course_description")
	private String courseDescription;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Student> students = new ArrayList<>();
}
