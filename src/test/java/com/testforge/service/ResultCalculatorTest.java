package com.testforge.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResultCalculatorTest {
    @Test void invalidMarksBelowMinimum() { assertEquals("Invalid", ResultCalculator.calculate(-1, 80)); }
    @Test void invalidMarksAboveMaximum() { assertEquals("Invalid", ResultCalculator.calculate(101, 80)); }
    @Test void invalidAttendance() { assertEquals("Invalid", ResultCalculator.calculate(70, 101)); }
    @Test void marksBelowFortyFail() { assertEquals("Fail", ResultCalculator.calculate(39, 90)); }
    @Test void boundaryFortyWithAttendancePasses() { assertEquals("Pass", ResultCalculator.calculate(40, 75)); }
    @Test void fiftyNineWithLowAttendanceFails() { assertEquals("Fail (attendance)", ResultCalculator.calculate(59, 74.99)); }
    @Test void sixtyIsMerit() { assertEquals("Merit", ResultCalculator.calculate(60, 50)); }
    @Test void seventyNineIsMerit() { assertEquals("Merit", ResultCalculator.calculate(79, 50)); }
    @Test void eightyIsDistinction() { assertEquals("Distinction", ResultCalculator.calculate(80, 0)); }
    @Test void oneHundredIsDistinction() { assertEquals("Distinction", ResultCalculator.calculate(100, 100)); }
    @Test
    void invalidAttendanceBelowMinimum() {
        assertEquals("Invalid",
            ResultCalculator.calculate(70, -0.01));
    }

    @Test
    void zeroMarksIsValidButFail() {
        assertEquals("Fail",
            ResultCalculator.calculate(0, 80));
    }

    @Test
    void oneMarkIsFail() {
        assertEquals("Fail",
            ResultCalculator.calculate(1, 80));
    }

    @Test
    void ninetyNineMarksIsDistinction() {
        assertEquals("Distinction",
            ResultCalculator.calculate(99, 80));
    }
}
