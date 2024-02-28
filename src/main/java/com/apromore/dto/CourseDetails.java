package com.apromore.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {
	private String courseName;
	private String courseCode;
	private String courseDescription;	
}
