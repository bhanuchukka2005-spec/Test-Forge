package com.testforge.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record SessionUser(String username, String role, String rollNumber) {
    public static SessionUser from(HttpSession session) {
        Object username = session.getAttribute("username");
        Object role = session.getAttribute("role");
        Object roll = session.getAttribute("rollNumber");
        if (username == null || role == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please log in");
        return new SessionUser(username.toString(), role.toString(), roll == null ? null : roll.toString());
    }
    public static SessionUser requireAdmin(HttpSession session) {
        SessionUser user = from(session);
        if (!"ADMIN".equals(user.role())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        return user;
    }
}
