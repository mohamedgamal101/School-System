package com.schoolSystem.demo.services.impl;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.InstructorDTO;
import com.schoolSystem.demo.dtos.StudentDTO;
import com.schoolSystem.demo.services.InstructorService;
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
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<InstructorDTO> getAllInstructors() {
        List<InstructorDTO> instructors = new ArrayList<>();
        String sql = "SELECT * FROM instructor";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                InstructorDTO instructorDTO = new InstructorDTO();
                instructorDTO.setId(resultSet.getInt("id"));
                instructorDTO.setName(resultSet.getString("name"));
                instructorDTO.setEmail(resultSet.getString("email"));
                instructors.add(instructorDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }


    public void addInstructor(InstructorDTO instructorDTO) {
        String sql = "INSERT INTO instructor (id, name, email) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, instructorDTO.getId());
            statement.setString(2, instructorDTO.getName());
            statement.setString(3, instructorDTO.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeInstructor(int id) {
        String sql = "DELETE FROM instructor WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(InstructorDTO instructorDTO) {
        String sql = "UPDATE instructor SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, instructorDTO.getName());
            statement.setString(2, instructorDTO.getEmail());
            statement.setInt(3, instructorDTO.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InstructorDTO getInstructorById(int id) {
        String sql = "SELECT * FROM instructor WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                InstructorDTO instructorDTO = new InstructorDTO();
                instructorDTO.setId(resultSet.getInt("id"));
                instructorDTO.setName(resultSet.getString("name"));
                instructorDTO.setEmail(resultSet.getString("email"));
                return instructorDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void addCourse(int instructorId, CourseDTO courseDTO) {
        String sql = "INSERT INTO course (id, name, instructorId) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseDTO.getId());
            statement.setString(2, courseDTO.getName());
            statement.setInt(3, instructorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CourseDTO> getCoursesByInstructorId(int InstructorId) {
        List<CourseDTO> courses = new ArrayList<>();
        String sql = "SELECT id, name FROM course WHERE instructorId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setInt(1, InstructorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setId(resultSet.getInt("id"));
                courseDTO.setName(resultSet.getString("name"));
                courses.add(courseDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}
