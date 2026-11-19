# Lab Sheet 6 - JUnit Testing

## Project
TestForge Student Portal

## Objective
Perform unit testing using JUnit 5 on the TestForge ResultCalculator.

## Class under test
src/main/java/com/testforge/service/ResultCalculator.java

## Test classes
1. ResultCalculatorTest.java
2. ResultCalculatorJUnitLabTest.java

## JUnit annotations
- @Test: Identifies a test method.
- @BeforeEach: Runs setup before every test method.
- @DisplayName: Gives a readable name to a test.

## Assertions used
- assertEquals(): Compares expected and actual results.
- assertAll(): Executes multiple assertions together.
- assertDoesNotThrow(): Checks that an operation completes without throwing an exception.

## Test scenarios
- Fail result
- Pass result
- Attendance failure
- Merit result
- Distinction result
- Invalid marks
- Valid calculation without exception
- Multiple result assertions

## Execution command
.\mvnw.cmd "-Dtest=ResultCalculatorJUnitLabTest,ResultCalculatorTest" test

## Conclusion
JUnit 5 automates the verification of individual TestForge result calculation scenarios.
