package com.apromore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_roll")
	private Integer studentRoll;
	@Column(name="student_name")
	private String studentName;
	@Column(name="student_age")
	private Integer studentAge;


	@ManyToMany(fetch = LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "studentRoll" , referencedColumnName = "student_roll"),
			inverseJoinColumns = @JoinColumn(name = "courseName",  referencedColumnName = "course_name"))
	private List<Course> courses = new ArrayList<>();
}
