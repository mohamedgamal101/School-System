package com.schoolSystem.demo.mappers;

import com.schoolSystem.demo.dtos.StudentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<StudentDTO> {
    @Override
    public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(rs.getInt("id"));
        studentDTO.setName(rs.getString("name"));
        studentDTO.setEmail(rs.getString("email"));
        return studentDTO;
    }
}
