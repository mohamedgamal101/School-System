package com.schoolSystem.demo.controllers;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.InstructorDTO;
import com.schoolSystem.demo.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final DataSource dataSource;

    public InstructorController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private InstructorService instructorService;


    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
        List<InstructorDTO> instructors = instructorService.getAllInstructors();
        return Optional.of(instructors)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addInstructor(@RequestBody InstructorDTO instructorDTO) {
        instructorService.addInstructor(instructorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeInstructor(@PathVariable int id) {
        InstructorDTO existingInstructor = instructorService.getInstructorById(id);
        return Optional.ofNullable(existingInstructor)
                .map(instructor -> {
                    instructorService.removeInstructor(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInstructor(@PathVariable int id, @RequestBody InstructorDTO instructorDTO) {
        InstructorDTO existingInstructor = instructorService.getInstructorById(id);
        return Optional.ofNullable(existingInstructor)
                .map(instructor -> {
                    instructor.setName(instructorDTO.getName());
                    instructor.setEmail(instructorDTO.getEmail());
                    instructorService.updateInstructor(instructor);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{instructorId}/courses")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable int instructorId, @RequestBody CourseDTO courseDTO) {
        instructorService.addCourse(instructorId, courseDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/{instructorId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructorId(@PathVariable int instructorId) {
        List<CourseDTO> courseDTO = instructorService.getCoursesByInstructorId(instructorId);
        return Optional.ofNullable(courseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Rest of the controller code


}
