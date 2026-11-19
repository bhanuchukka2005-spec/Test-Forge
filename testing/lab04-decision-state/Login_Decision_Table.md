# Lab Sheet 4 - Login Decision Table Testing

## Project
TestForge Student Portal

## Objective
Test login outcomes based on username validity, account state and password correctness.

## Actual implementation
Endpoint: POST /api/auth/login

Source:
src/main/java/com/testforge/api/AuthController.java

## Decision table

| Rule | Username supplied | Account exists | Account locked | Password correct | Expected outcome |
|---|---|---|---|---|---|
| R1 | No | - | - | - | Request rejected by validation |
| R2 | Yes | No | - | - | HTTP 401 - Invalid username or password |
| R3 | Yes | Yes | Yes | - | HTTP 423 - Account locked |
| R4 | Yes | Yes | No | No (first/second failure) | HTTP 401; failure counter increases |
| R5 | Yes | Yes | No | No (third failure) | HTTP 401; account becomes locked |
| R6 | Yes | Yes | Yes on next request | - | HTTP 423 - Account locked |
| R7 | Yes | Yes | No | Yes | HTTP 200; login succeeds and session is created |

A blank password is also rejected by the @NotBlank validation rule.

## Actions on successful login
- Failed-login counter is reset.
- Username is stored in the session.
- User role is stored in the session.
- Student roll number is stored in the session.

## Existing automated state-transition tests
src/test/java/com/testforge/domain/AccountLockoutStateTransitionTest.java

These verify the account state transitions directly.
They are domain-level tests, not full HTTP endpoint integration tests.

## Conclusion
Decision table testing checks login behavior for different combinations of input and account state.