package com.testforge.service;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/** Run separately with: mvn -Ptestng test */
public class ResultCalculatorTestNG {
    @Test public void testMeritBranch() { assertEquals(ResultCalculator.calculate(75, 60), "Merit"); }
    @Test public void testAttendanceBranch() { assertEquals(ResultCalculator.calculate(50, 70), "Fail (attendance)"); }
}
