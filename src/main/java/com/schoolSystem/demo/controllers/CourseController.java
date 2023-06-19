package com.schoolSystem.demo.controllers;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final DataSource dataSource;

    public CourseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<CourseDTO>> searchCoursesByName(@RequestParam String courseName) {
        List<CourseDTO> matchingCourses = courseService.searchCoursesByName(courseName);
        if (!matchingCourses.isEmpty()) {
            return ResponseEntity.ok(matchingCourses);
        }
        return ResponseEntity.notFound().build();
    }

}
