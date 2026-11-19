package com.testforge.api;

import com.testforge.domain.StudentProfile;
import com.testforge.repo.StudentProfileRepository;
import com.testforge.service.ResultCalculator;
import com.testforge.service.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final StudentProfileRepository students;
    public ResultController(StudentProfileRepository students) { this.students = students; }
    @GetMapping("/{rollNumber}")
    public Map<String, Object> result(@PathVariable String rollNumber, HttpSession session) {
        SessionUser user = SessionUser.from(session);
        if (!"ADMIN".equals(user.role()) && !rollNumber.equals(user.rollNumber())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only view your own result");
        StudentProfile student = students.findByRollNumber(rollNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return Map.of("rollNumber", rollNumber, "marks", student.getMarks(), "attendancePercent", student.getAttendancePercent(),
                "result", ResultCalculator.calculate(student.getMarks(), student.getAttendancePercent()));
    }
}
