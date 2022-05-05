package com.example.demo.Students;

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
    public List<Student> getAllStudent(){
        return students;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("{studentID}")
    public void deleteStudent(@PathVariable("studentID") Integer studentID){
        System.out.println(studentID);
    }

    @PutMapping("{studentID}")
    public void updateStudent(@PathVariable("studentID")Integer Id, @RequestBody Student student){
        System.out.println(student);
    }

}
