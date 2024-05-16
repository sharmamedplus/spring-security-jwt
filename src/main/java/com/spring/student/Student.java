package com.spring.student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private Integer studentId;
    private String studentName;
}
