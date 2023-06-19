@RestController
@RequestMapping("/students")
public class StudentController {
    private final JdbcTemplate jdbcTemplate;

    public StudentController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        String sql = "SELECT * FROM Student WHERE id = ?";
        StudentDTO studentDTO = jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        String sql = "INSERT INTO Student (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail());
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        String sql = "UPDATE Student SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, studentDTO.getName(), studentDTO.getEmail(), id);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        String sql = "DELETE FROM Student WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.noContent().build();
    }
}
