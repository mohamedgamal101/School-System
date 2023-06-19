package com.schoolSystem.demo.services.impl;

import com.schoolSystem.demo.dtos.CourseDTO;
import com.schoolSystem.demo.dtos.StudentDTO;
import com.schoolSystem.demo.services.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private DataSource dataSource;

    @Override
    public StudentDTO getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setName(resultSet.getString("name"));
                studentDTO.setEmail(resultSet.getString("email"));
                return studentDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setName(resultSet.getString("name"));
                studentDTO.setEmail(resultSet.getString("email"));
                students.add(studentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<CourseDTO> getCoursesByStudentId(int studentId) {
        List<CourseDTO> courses = new ArrayList<>();
        String sql = "SELECT id, name FROM course WHERE studentId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
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

    public void addStudent(StudentDTO studentDTO) {
        String sql = "INSERT INTO student (id, name, email) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentDTO.getId());
            statement.setString(2, studentDTO.getName());
            statement.setString(3, studentDTO.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(StudentDTO studentDTO) {
        String sql = "UPDATE student SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentDTO.getName());
            statement.setString(2, studentDTO.getEmail());
            statement.setInt(3, studentDTO.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
