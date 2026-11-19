package com.testforge.api;

import com.testforge.domain.UserAccount;
import com.testforge.repo.UserAccountRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserAccountRepository users;
    private final PasswordEncoder encoder;
    public AuthController(UserAccountRepository users, PasswordEncoder encoder) { this.users = users; this.encoder = encoder; }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) { }

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        UserAccount account = users.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));
        if (account.isLocked()) throw new ResponseStatusException(HttpStatus.LOCKED, "Account locked after three failed attempts");
        if (!encoder.matches(request.password(), account.getPasswordHash())) {
            account.recordFailure(); users.save(account);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        account.resetFailures(); users.save(account);
        session.setAttribute("username", account.getUsername());
        session.setAttribute("role", account.getRole().name());
        session.setAttribute("rollNumber", account.getStudentRollNumber());
        return Map.of("message", "Login successful", "username", account.getUsername(), "role", account.getRole().name());
    }

    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();
        return Map.of("message", "Logged out");
    }

    @GetMapping("/me")
    public Map<String, Object> me(HttpSession session) {
        Object username = session.getAttribute("username");
        if (username == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please log in");
        return Map.of("username", username, "role", session.getAttribute("role"));
    }
}
