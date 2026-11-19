package com.testforge.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResultCalculatorBvaEcpTest {

    @Test
    void marksBoundaryValues() {
        assertAll(
            () -> assertEquals("Invalid", ResultCalculator.calculate(-1, 80)),
            () -> assertEquals("Fail", ResultCalculator.calculate(0, 80)),
            () -> assertEquals("Fail", ResultCalculator.calculate(1, 80)),
            () -> assertEquals("Fail", ResultCalculator.calculate(39, 80)),
            () -> assertEquals("Pass", ResultCalculator.calculate(40, 75)),
            () -> assertEquals("Pass", ResultCalculator.calculate(41, 75)),
            () -> assertEquals("Pass", ResultCalculator.calculate(59, 75)),
            () -> assertEquals("Merit", ResultCalculator.calculate(60, 80)),
            () -> assertEquals("Merit", ResultCalculator.calculate(61, 80)),
            () -> assertEquals("Merit", ResultCalculator.calculate(79, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(80, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(81, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(99, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(100, 80)),
            () -> assertEquals("Invalid", ResultCalculator.calculate(101, 80))
        );
    }

    @Test
    void attendanceBoundaryValues() {
        assertAll(
            () -> assertEquals("Invalid", ResultCalculator.calculate(50, -0.01)),
            () -> assertEquals("Fail (attendance)", ResultCalculator.calculate(50, 0)),
            () -> assertEquals("Fail (attendance)", ResultCalculator.calculate(50, 74.99)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 75)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 75.01)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 100)),
            () -> assertEquals("Invalid", ResultCalculator.calculate(50, 100.01))
        );
    }

    @Test
    void marksEquivalenceClasses() {
        assertAll(
            () -> assertEquals("Invalid", ResultCalculator.calculate(-1, 80)),
            () -> assertEquals("Fail", ResultCalculator.calculate(20, 80)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 80)),
            () -> assertEquals("Fail (attendance)", ResultCalculator.calculate(50, 60)),
            () -> assertEquals("Merit", ResultCalculator.calculate(70, 80)),
            () -> assertEquals("Distinction", ResultCalculator.calculate(90, 80)),
            () -> assertEquals("Invalid", ResultCalculator.calculate(101, 80))
        );
    }

    @Test
    void attendanceEquivalenceClasses() {
        assertAll(
            () -> assertEquals("Invalid", ResultCalculator.calculate(50, -1)),
            () -> assertEquals("Fail (attendance)", ResultCalculator.calculate(50, 50)),
            () -> assertEquals("Pass", ResultCalculator.calculate(50, 80)),
            () -> assertEquals("Invalid", ResultCalculator.calculate(50, 101))
        );
    }
}