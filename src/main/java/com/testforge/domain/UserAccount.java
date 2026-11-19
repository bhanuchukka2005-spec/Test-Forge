package com.testforge.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    @Column(nullable = false, length = 100)
    private String passwordHash;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private Role role;
    @Column(length = 20)
    private String studentRollNumber;
    private int failedLoginAttempts;
    private boolean locked;

    protected UserAccount() { }
    public UserAccount(String username, String passwordHash, Role role, String studentRollNumber) {
        this.username = username; this.passwordHash = passwordHash; this.role = role;
        this.studentRollNumber = studentRollNumber;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
    public String getStudentRollNumber() { return studentRollNumber; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public boolean isLocked() { return locked; }
    public void recordFailure() { failedLoginAttempts++; if (failedLoginAttempts >= 3) locked = true; }
    public void resetFailures() { failedLoginAttempts = 0; }
}
