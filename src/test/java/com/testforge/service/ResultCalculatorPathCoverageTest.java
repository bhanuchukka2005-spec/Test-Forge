
package com.testforge.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResultCalculatorPathCoverageTest {

    // Path 1: Marks below minimum
    @Test
    void path1InvalidMarksBelowMinimum() {
        assertEquals("Invalid",
            ResultCalculator.calculate(-1, 80));
    }

    // Path 2: Marks above maximum
    @Test
    void path2InvalidMarksAboveMaximum() {
        assertEquals("Invalid",
            ResultCalculator.calculate(101, 80));
    }

    // Path 3: Attendance below minimum
    @Test
    void path3InvalidAttendanceBelowMinimum() {
        assertEquals("Invalid",
            ResultCalculator.calculate(70, -1));
    }

    // Path 4: Attendance above maximum
    @Test
    void path4InvalidAttendanceAboveMaximum() {
        assertEquals("Invalid",
            ResultCalculator.calculate(70, 101));
    }

    // Path 5: Marks below 40
    @Test
    void path5Fail() {
        assertEquals("Fail",
            ResultCalculator.calculate(30, 80));
    }

    // Path 6: Marks 40-59 with sufficient attendance
    @Test
    void path6Pass() {
        assertEquals("Pass",
            ResultCalculator.calculate(55, 75));
    }

    // Path 7: Marks 40-59 with insufficient attendance
    @Test
    void path7AttendanceFailure() {
        assertEquals("Fail (attendance)",
            ResultCalculator.calculate(55, 74));
    }

    // Path 8: Merit
    @Test
    void path8Merit() {
        assertEquals("Merit",
            ResultCalculator.calculate(70, 80));
    }

    // Path 9: Distinction
    @Test
    void path9Distinction() {
        assertEquals("Distinction",
            ResultCalculator.calculate(85, 80));
    }
}
