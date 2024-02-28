package com.apromore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

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
	
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
	private List<Course> courses = new ArrayList<>();
}
