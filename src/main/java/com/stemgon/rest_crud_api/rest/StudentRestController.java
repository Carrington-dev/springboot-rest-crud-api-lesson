package com.stemgon.rest_crud_api.rest;

import com.stemgon.rest_crud_api.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> students;


    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();
        students.add(new Student("Carrington", "Muleya"));
        students.add(new Student("Hardlife", "Mokwena"));
        students.add(new Student("Mpho", "Mudau"));
        students.add(new Student("Thuso", "Mbedzi"));
        students.add(new Student("Harry", "Moyo"));
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello There";
    }


    @GetMapping("/students")
    public List<Student> getAllStudents(){

        return students;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        return students.get(studentId);
    }
}
