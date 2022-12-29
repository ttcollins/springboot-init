package com.collins.student.controllers;

import com.collins.student.exceptions.ValidationFailedException;
import com.collins.student.models.Student;
import com.collins.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        try {
            studentService.addNewStudent(student);
        } catch (ValidationFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        try {
            studentService.deleteStudent(studentId);
        } catch (ValidationFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        try {
            studentService.updateStudent(studentId, name, email);
        } catch (ValidationFailedException e) {
            throw new RuntimeException(e);
        }
    }

}
