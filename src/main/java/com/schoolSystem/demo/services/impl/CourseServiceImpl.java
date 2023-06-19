package com.schoolSystem.demo.services.impl;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.StudentDTO;
import com.schoolSystem.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private DataSource dataSource;


    @Override
    public List<CourseDTO> searchCoursesByName(String courseName) {
        List<CourseDTO> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setId(resultSet.getInt("id"));
                    courseDTO.setName(resultSet.getString("name"));
//                    courseDTO.setInstructorId(resultSet.getInt("instructorId"));
                    courses.add(courseDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}
