# Lab Sheet 3 - Boundary Value Analysis

## Project
TestForge Student Portal

## Objective
Test the result calculation function at and around important boundaries.

## Actual validation rules
- Marks must be between 0 and 100 inclusive.
- Attendance must be between 0 and 100 inclusive.
- Marks below 40 produce Fail.
- Marks from 40 to 59 require at least 75% attendance.
- Marks from 60 to 79 produce Merit.
- Marks from 80 to 100 produce Distinction.

## Marks boundary values

| Boundary | Test values | Expected results |
|---|---|---|
| Minimum marks 0 | -1, 0, 1 | Invalid, Fail, Fail |
| Pass threshold 40 | 39, 40, 41 | Fail, Pass, Pass (attendance 75) |
| Merit threshold 60 | 59, 60, 61 | Pass, Merit, Merit (attendance 75) |
| Distinction threshold 80 | 79, 80, 81 | Merit, Distinction, Distinction |
| Maximum marks 100 | 99, 100, 101 | Distinction, Distinction, Invalid |

## Attendance boundary values

| Test value | Expected result with 50 marks |
|---:|---|
| -0.01 | Invalid |
| 0 | Fail (attendance) |
| 74.99 | Fail (attendance) |
| 75 | Pass |
| 75.01 | Pass |
| 100 | Pass |
| 100.01 | Invalid |

## Automation
Executable JUnit tests:
src/test/java/com/testforge/service/ResultCalculatorBvaEcpTest.java

## Conclusion
Boundary values are tested around the actual validation limits and grading thresholds.