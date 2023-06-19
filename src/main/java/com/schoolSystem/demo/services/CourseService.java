package com.schoolSystem.demo.services;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.StudentDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> searchCoursesByName(String courseName);
}
