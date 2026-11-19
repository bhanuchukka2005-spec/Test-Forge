# Account Lockout - State Transition Testing

## Objective
Verify that repeated unsuccessful login attempts change the account state correctly.

## States
- ACTIVE: Account can attempt login.
- LOCKED: Account has reached three failed attempts.

## State transition table

| Current state | Event | Next state | Expected behavior |
|---|---|---|---|
| ACTIVE, 0 failures | Wrong password | ACTIVE, 1 failure | Login rejected |
| ACTIVE, 1 failure | Wrong password | ACTIVE, 2 failures | Login rejected |
| ACTIVE, 2 failures | Wrong password | LOCKED, 3 failures | Login rejected and account becomes locked |
| ACTIVE, 1 or 2 failures | Successful login | ACTIVE, 0 failures | Failure counter reset |
| LOCKED | Login attempt | LOCKED | HTTP 423 account locked |

## Existing JUnit test class
src/test/java/com/testforge/domain/AccountLockoutStateTransitionTest.java

## Covered test methods
1. initialStateIsActive
2. firstWrongAttemptKeepsAccountActive
3. secondWrongAttemptKeepsAccountActive
4. thirdWrongAttemptLocksAccount
5. successfulLoginResetsFailureCounter

## Important
The existing successful-login reset test verifies reset before the account becomes locked.
The current AuthController rejects an already locked account before checking the password.

## Conclusion
The test verifies valid state transitions and the account lockout rule.