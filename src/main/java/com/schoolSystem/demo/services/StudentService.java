package com.schoolSystem.demo.services;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO getStudentById(int id);

    List<StudentDTO> getAllStudents();


    List<CourseDTO> getCoursesByStudentId(int studentId);

    void addStudent(StudentDTO studentDTO);

    void removeStudent(int id);

    void updateStudent(StudentDTO studentDTO);
}
