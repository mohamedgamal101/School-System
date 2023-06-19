package com.schoolSystem.demo.services;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.InstructorDTO;
import com.schoolSystem.demo.dtos.StudentDTO;

import java.util.List;

public interface InstructorService {
    InstructorDTO getInstructorById(int id);
    void addCourse(int instructorId, CourseDTO courseDTO);
    List<CourseDTO> getCoursesByInstructorId(int instructorId);

    List<InstructorDTO> getAllInstructors();

    void addInstructor(InstructorDTO instructorDTO);

    void removeInstructor(int id);

    void updateInstructor(InstructorDTO instructorDTO);


}
