package com.testforge.api;

import com.testforge.domain.AttendanceRecord;
import com.testforge.repo.AttendanceRecordRepository;
import com.testforge.repo.StudentProfileRepository;
import com.testforge.service.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceRecordRepository attendance;
    private final StudentProfileRepository students;
    public AttendanceController(AttendanceRecordRepository attendance, StudentProfileRepository students) { this.attendance = attendance; this.students = students; }
    public record AttendanceRequest(@NotBlank String rollNumber, @NotBlank String subject, @NotNull LocalDate attendanceDate, boolean present) { }
    @GetMapping("/me")
    public List<AttendanceRecord> myAttendance(HttpSession session) {
        SessionUser user = SessionUser.from(session);
        if (user.rollNumber() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Admin account has no student attendance");
        return attendance.findByRollNumberOrderByAttendanceDateDesc(user.rollNumber());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttendanceRecord mark(@Valid @RequestBody AttendanceRequest request, HttpSession session) {
        SessionUser.requireAdmin(session);
        if (students.findByRollNumber(request.rollNumber()).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        return attendance.save(new AttendanceRecord(request.rollNumber(), request.subject(), request.attendanceDate(), request.present()));
    }
}
