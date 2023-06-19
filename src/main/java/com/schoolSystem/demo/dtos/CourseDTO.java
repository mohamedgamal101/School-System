package com.schoolSystem.demo.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class CourseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
//    private int instructorId;
//    private int studentId; // New field for student ID


//    private InstructorDTO instructor;
//    private List<StudentDTO> enrolledStudents;
//    private double rating;

//    public InstructorDTO getInstructor() {
//        return instructor;
//    }
//
//    public void setInstructor(InstructorDTO instructor) {
//        this.instructor = instructor;
//    }
//
//    public List<StudentDTO> getEnrolledStudents() {
//        return enrolledStudents;
//    }
//
//    public void setEnrolledStudents(List<StudentDTO> enrolledStudents) {
//        this.enrolledStudents = enrolledStudents;
//    }

    public CourseDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getInstructorId() {
//        return instructorId;
//    }
//
//    public void setInstructorId(int instructorId) {
//        this.instructorId = instructorId;
//    }
//
//    public int getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", instructorId=" + instructorId +
//                ", studentId=" + studentId +
//                ", instructor=" + instructor +
//                ", enrolledStudents=" + enrolledStudents +
                '}';
    }

}
