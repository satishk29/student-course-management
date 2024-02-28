package com.apromore.dto;

import java.util.List;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Students {
	List<StudentDetails> students;
}
