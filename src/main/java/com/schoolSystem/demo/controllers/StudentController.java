package com.schoolSystem.demo.controllers;


import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.StudentDTO;
import com.schoolSystem.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final DataSource dataSource;

    public StudentController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Autowired
    private StudentService studentService;



    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable int id) {
        StudentDTO existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            studentService.removeStudent(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        StudentDTO existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(studentDTO.getName());
            existingStudent.setEmail(studentDTO.getEmail());
            studentService.updateStudent(existingStudent);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByStudentId(@PathVariable int studentId) {
        List<CourseDTO> courseDTO = studentService.getCoursesByStudentId(studentId);
        if (courseDTO != null) {
            return ResponseEntity.ok(courseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Rest of the controller code
}
