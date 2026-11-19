# Lab Sheet 4 - Decision Table and State Transition Testing

This lab connects login decision rules and account lockout transitions to the actual TestForge authentication implementation.

Existing automated test:
src/test/java/com/testforge/domain/AccountLockoutStateTransitionTest.java

Run from the repository root:

.\mvnw.cmd "-Dtest=AccountLockoutStateTransitionTest" test