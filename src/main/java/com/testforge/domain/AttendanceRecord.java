package com.testforge.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_records")
public class AttendanceRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String rollNumber;
    @Column(nullable = false, length = 80)
    private String subject;
    @Column(nullable = false)
    private LocalDate attendanceDate;
    private boolean present;
    protected AttendanceRecord() { }
    public AttendanceRecord(String rollNumber, String subject, LocalDate date, boolean present) {
        this.rollNumber = rollNumber; this.subject = subject; this.attendanceDate = date; this.present = present;
    }
    public Long getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getSubject() { return subject; }
    public LocalDate getAttendanceDate() { return attendanceDate; }
    public boolean isPresent() { return present; }
}
