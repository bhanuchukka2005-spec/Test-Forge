package com.testforge.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountLockoutStateTransitionTest {

    private UserAccount createAccount() {
        return new UserAccount(
            "qa-transition",
            "test-hash",
            Role.STUDENT,
            "STU999"
        );
    }

    @Test
    void initialStateIsActive() {
        UserAccount account = createAccount();

        assertFalse(account.isLocked());
        assertEquals(0, account.getFailedLoginAttempts());
    }

    @Test
    void firstWrongAttemptKeepsAccountActive() {
        UserAccount account = createAccount();

        account.recordFailure();

        assertFalse(account.isLocked());
        assertEquals(1, account.getFailedLoginAttempts());
    }

    @Test
    void secondWrongAttemptKeepsAccountActive() {
        UserAccount account = createAccount();

        account.recordFailure();
        account.recordFailure();

        assertFalse(account.isLocked());
        assertEquals(2, account.getFailedLoginAttempts());
    }

    @Test
    void thirdWrongAttemptLocksAccount() {
        UserAccount account = createAccount();

        account.recordFailure();
        account.recordFailure();
        account.recordFailure();

        assertTrue(account.isLocked());
        assertEquals(3, account.getFailedLoginAttempts());
    }

    @Test
    void successfulLoginResetsFailureCounter() {
        UserAccount account = createAccount();

        account.recordFailure();
        account.recordFailure();
        account.resetFailures();

        assertFalse(account.isLocked());
        assertEquals(0, account.getFailedLoginAttempts());
    }
}