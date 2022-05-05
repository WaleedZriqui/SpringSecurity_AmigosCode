package com.example.demo.Students;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class StudentController {
    private static final List<Student> students = Arrays.asList(
            new Student(1,"waleed"),
            new Student(2,"mohammad")
    );

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Integer id){
        return students.stream()
                .filter(student ->id.equals(student.getId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("not found"));
    }
}
