package com.testforge.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String rollNumber;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, length = 2000)
    private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private RequestStatus status;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    protected ServiceRequest() { }
    public ServiceRequest(String rollNumber, String title, String description) {
        this.rollNumber = rollNumber; this.title = title; this.description = description;
        this.status = RequestStatus.OPEN; this.createdAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public RequestStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setStatus(RequestStatus status) { this.status = status; }
}
