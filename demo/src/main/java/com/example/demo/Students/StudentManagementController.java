package com.example.demo.Students;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> students = Arrays.asList(
            new Student(1,"waleed"),
            new Student(2,"mohammad")
    );

    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public List<Student> getAllStudent(){
        return  students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("{studentID}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentID") Integer studentID){
        System.out.println(studentID);
    }

    @PutMapping("{studentID}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentID")Integer Id, @RequestBody Student student){
        System.out.println(student);
    }

}
