package com.testforge.api;

import com.testforge.domain.RequestStatus;
import com.testforge.domain.ServiceRequest;
import com.testforge.repo.ServiceRequestRepository;
import com.testforge.service.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {
    private final ServiceRequestRepository requests;
    public ServiceRequestController(ServiceRequestRepository requests) { this.requests = requests; }
    public record CreateRequest(@NotBlank String title, @NotBlank String description) { }
    public record StatusRequest(RequestStatus status) { }
    @GetMapping
    public List<ServiceRequest> list(HttpSession session) {
        SessionUser user = SessionUser.from(session);
        return "ADMIN".equals(user.role()) ? requests.findAll() : requests.findByRollNumberOrderByCreatedAtDesc(user.rollNumber());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRequest create(@Valid @RequestBody CreateRequest request, HttpSession session) {
        SessionUser user = SessionUser.from(session);
        if (user.rollNumber() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only student accounts can create requests");
        return requests.save(new ServiceRequest(user.rollNumber(), request.title(), request.description()));
    }
    @PatchMapping("/{id}/status")
    public ServiceRequest update(@PathVariable Long id, @RequestBody StatusRequest body, HttpSession session) {
        SessionUser.requireAdmin(session);
        if (body.status() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
        ServiceRequest request = requests.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        if (request.getStatus() == RequestStatus.RESOLVED || request.getStatus() == RequestStatus.REJECTED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Closed requests cannot be changed");
        }
        request.setStatus(body.status());
        return requests.save(request);
    }
}
