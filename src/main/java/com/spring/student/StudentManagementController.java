package com.spring.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
            new Student(2, "Maria Jones"), new Student(3, "Anna Smith"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudents(){
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewSrudent(@RequestBody Student student){
        System.out.println("Registering student :"+ student.getStudentName());
    }

    @DeleteMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void removeStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("Deleting Student : "+ studentId);
    }

    @PutMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("Updating student "+ studentId);
    }
}
