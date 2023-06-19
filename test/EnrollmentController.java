@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final JdbcTemplate jdbcTemplate;

    public EnrollmentController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enrollStudent(@RequestBody EnrollmentDTO enrollmentDTO) {
        String sql = "INSERT INTO Enrollment (id, studentId, courseId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, enrollmentDTO.getId(), enrollmentDTO.getStudentId(), enrollmentDTO.getCourseId());
        return ResponseEntity.ok(enrollmentDTO);
    }
}
