package com.testforge.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultCalculatorJUnitLabTest {

    private int passingMarks;
    private double sufficientAttendance;

    @BeforeEach
    void setUp() {
        passingMarks = 50;
        sufficientAttendance = 80;
    }

    @Test
    @DisplayName("JUnit: Marks below 40 should produce Fail")
    void testFailResult() {
        assertEquals("Fail", ResultCalculator.calculate(30, 80));
    }

    @Test
    @DisplayName("JUnit: Sufficient attendance should produce Pass")
    void testPassResult() {
        assertEquals("Pass",
            ResultCalculator.calculate(passingMarks, sufficientAttendance));
    }

    @Test
    @DisplayName("JUnit: Insufficient attendance should produce attendance failure")
    void testAttendanceFailure() {
        assertEquals("Fail (attendance)",
            ResultCalculator.calculate(passingMarks, 70));
    }

    @Test
    @DisplayName("JUnit: Marks in merit range should produce Merit")
    void testMeritResult() {
        assertEquals("Merit", ResultCalculator.calculate(70, 80));
    }

    @Test
    @DisplayName("JUnit: Marks in distinction range should produce Distinction")
    void testDistinctionResult() {
        assertEquals("Distinction", ResultCalculator.calculate(85, 80));
    }

    @Test
    @DisplayName("JUnit: Invalid marks should be rejected")
    void testInvalidMarks() {
        assertEquals("Invalid", ResultCalculator.calculate(101, 80));
    }

    @Test
    @DisplayName("JUnit: Valid calculation should not throw an exception")
    void testCalculationDoesNotThrow() {
        assertDoesNotThrow(() -> ResultCalculator.calculate(75, 80));
    }

    @Test
    @DisplayName("JUnit: Multiple outcomes using assertAll")
    void testMultipleResults() {
        assertAll(
            () -> assertEquals("Fail", ResultCalculator.calculate(20, 80)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 80)),
            () -> assertEquals("Merit", ResultCalculator.calculate(70, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(90, 80))
        );
    }
}
