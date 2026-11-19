package com.testforge.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Basis-path examples: each distinct decision outcome is represented. */
class ResultCalculatorPathCoverageTest {
    @Test void path1InvalidInput() { assertEquals("Invalid", ResultCalculator.calculate(-1, 80)); }
    @Test void path2Fail() { assertEquals("Fail", ResultCalculator.calculate(30, 80)); }
    @Test void path3PassWithAttendance() { assertEquals("Pass", ResultCalculator.calculate(55, 75)); }
    @Test void path4AttendanceFailure() { assertEquals("Fail (attendance)", ResultCalculator.calculate(55, 74)); }
    @Test void path5Merit() { assertEquals("Merit", ResultCalculator.calculate(70, 80)); }
    @Test void path6Distinction() { assertEquals("Distinction", ResultCalculator.calculate(85, 80)); }
}
