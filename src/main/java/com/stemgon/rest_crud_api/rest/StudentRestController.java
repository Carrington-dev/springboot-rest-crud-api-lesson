package com.stemgon.rest_crud_api.rest;

import com.stemgon.rest_crud_api.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if( (studentId >= students.size() ) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }


    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){
        // create a student error response
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception){
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
