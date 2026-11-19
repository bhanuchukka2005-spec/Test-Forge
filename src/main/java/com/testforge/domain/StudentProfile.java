package com.testforge.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String rollNumber;
    @Column(nullable = false, length = 100)
    private String fullName;
    @Column(nullable = false, unique = true, length = 120)
    private String email;
    @Column(nullable = false, length = 80)
    private String department;
    private int semester;
    private int marks;
    private double attendancePercent;

    protected StudentProfile() { }
    public StudentProfile(String rollNumber, String fullName, String email, String department,
                          int semester, int marks, double attendancePercent) {
        this.rollNumber = rollNumber; this.fullName = fullName; this.email = email;
        this.department = department; this.semester = semester; this.marks = marks;
        this.attendancePercent = attendancePercent;
    }
    public Long getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public int getSemester() { return semester; }
    public int getMarks() { return marks; }
    public double getAttendancePercent() { return attendancePercent; }
    public void updateAcademicData(int newMarks, double newAttendance) {
        this.marks = newMarks; this.attendancePercent = newAttendance;
    }
}
