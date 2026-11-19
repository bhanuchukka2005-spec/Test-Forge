package com.testforge.api;

import com.testforge.domain.StudentProfile;
import com.testforge.repo.StudentProfileRepository;
import com.testforge.service.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentProfileRepository students;
    public StudentController(StudentProfileRepository students) { this.students = students; }
    public record StudentRequest(@NotBlank String rollNumber, @NotBlank String fullName,
                                 @Email @NotBlank String email, @NotBlank String department,
                                 @Min(1) @Max(12) int semester, @Min(0) @Max(100) int marks,
                                 @DecimalMin("0.0") @DecimalMax("100.0") double attendancePercent) { }
    public record StudentView(String rollNumber, String fullName, String email, String department,
                              int semester, int marks, double attendancePercent, String result) {
        static StudentView from(StudentProfile s) {
            return new StudentView(s.getRollNumber(), s.getFullName(), s.getEmail(), s.getDepartment(),
                    s.getSemester(), s.getMarks(), s.getAttendancePercent(),
                    com.testforge.service.ResultCalculator.calculate(s.getMarks(), s.getAttendancePercent()));
        }
    }
    @GetMapping
    public List<StudentView> all(HttpSession session) {
        SessionUser.requireAdmin(session);
        return students.findAll().stream().map(StudentView::from).toList();
    }
    @GetMapping("/me")
    public StudentView me(HttpSession session) {
        SessionUser user = SessionUser.from(session);
        if (user.rollNumber() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Admin account has no student profile");
        StudentProfile student = students.findByRollNumber(user.rollNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return StudentView.from(student);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentView create(@Valid @RequestBody StudentRequest request, HttpSession session) {
        SessionUser.requireAdmin(session);
        if (students.findByRollNumber(request.rollNumber()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Roll number already exists");
        if (students.findAll().stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(request.email()))) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        StudentProfile saved = students.save(new StudentProfile(request.rollNumber(), request.fullName(), request.email(),
                request.department(), request.semester(), request.marks(), request.attendancePercent()));
        return StudentView.from(saved);
    }
}
