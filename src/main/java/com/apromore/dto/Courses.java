package com.apromore.dto;

import java.util.List;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
	List<CourseDetails> courses;
}
