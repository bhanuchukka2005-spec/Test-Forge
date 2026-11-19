package com.testforge.config;

import com.testforge.domain.*;
import com.testforge.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Configuration
public class DemoData {
    @Bean
    CommandLineRunner seedDemoData(UserAccountRepository users, StudentProfileRepository students,
                                   AttendanceRecordRepository attendance, PasswordEncoder encoder) {
        return args -> {
            if (users.count() == 0) {
                users.save(new UserAccount("admin", encoder.encode("Admin@123"), Role.ADMIN, null));
                users.save(new UserAccount("student", encoder.encode("Student@123"), Role.STUDENT, "STU001"));
            }
            if (students.count() == 0) {
                students.save(new StudentProfile("STU001", "Aarav Sharma", "aarav@example.test", "Computer Science", 5, 82, 86.5));
                students.save(new StudentProfile("STU002", "Meera Nair", "meera@example.test", "Computer Science", 5, 56, 68.0));
            }
            if (attendance.count() == 0) {
                attendance.save(new AttendanceRecord("STU001", "Software Testing", LocalDate.now().minusDays(1), true));
                attendance.save(new AttendanceRecord("STU001", "Operating Systems", LocalDate.now().minusDays(2), true));
                attendance.save(new AttendanceRecord("STU001", "Database Systems", LocalDate.now().minusDays(3), false));
                attendance.save(new AttendanceRecord("STU002", "Software Testing", LocalDate.now().minusDays(1), false));
            }
        };
    }
}
