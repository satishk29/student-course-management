package com.apromore.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetails {
	@JsonInclude(Include.NON_NULL)
	private Integer studentRoll;
	private String studentName;
	private Integer studentAge;
	private List<String> courses = new ArrayList<>();
}
