@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final JdbcTemplate jdbcTemplate;

    public InstructorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/{instructorId}/courses")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable int instructorId, @RequestBody CourseDTO courseDTO) {
        String sql = "INSERT INTO Course (id, name, instructorId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, courseDTO.getId(), courseDTO.getName(), instructorId);
        return ResponseEntity.ok(courseDTO);
    }

    @PutMapping("/{instructorId}/courses/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int instructorId, @PathVariable int courseId, @RequestBody CourseDTO courseDTO) {
        String sql = "UPDATE Course SET name = ? WHERE id = ? AND instructorId = ?";
        jdbcTemplate.update(sql, courseDTO.getName(), courseId, instructorId);
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping("/{instructorId}/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int instructorId, @PathVariable int courseId) {
        String sql = "DELETE FROM Course WHERE id = ? AND instructorId = ?";
        jdbcTemplate.update(sql, courseId, instructorId);
        return ResponseEntity.noContent().build();
    }
}
