package com.testforge.service;

/** Pure decision logic used for BVA, equivalence partitioning and basis-path tests. */
public final class ResultCalculator {
    public static final double MINIMUM_ATTENDANCE = 75.0;
    private ResultCalculator() { }

    public static String calculate(int marks, double attendancePercent) {
        if (marks < 0 || marks > 100) return "Invalid";
        if (attendancePercent < 0 || attendancePercent > 100) return "Invalid";
        if (marks < 40) return "Fail";
        if (marks < 60) {
            if (attendancePercent >= MINIMUM_ATTENDANCE) return "Pass";
            return "Fail (attendance)";
        }
        if (marks < 80) return "Merit";
        return "Distinction";
    }
}
