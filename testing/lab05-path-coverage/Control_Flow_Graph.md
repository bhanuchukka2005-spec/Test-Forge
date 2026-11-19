# Lab Sheet 5 - Control Flow Graph and Path Coverage

## Project
TestForge Student Portal

## Method under test
ResultCalculator.calculate(int marks, double attendancePercent)

Source:
src/main/java/com/testforge/service/ResultCalculator.java

## Control flow

The following graph expands both short-circuit OR conditions into their individual checks.

```mermaid
flowchart TD
    A([Start]) --> B{marks < 0?}
    B -->|Yes| I[Invalid]
    B -->|No| C{marks > 100?}
    C -->|Yes| I
    C -->|No| D{attendance < 0?}
    D -->|Yes| I
    D -->|No| E{attendance > 100?}
    E -->|Yes| I
    E -->|No| F{marks < 40?}
    F -->|Yes| G[Fail]
    F -->|No| H{marks < 60?}
    H -->|No| J{marks < 80?}
    H -->|Yes| K{attendance >= 75?}
    K -->|Yes| L[Pass]
    K -->|No| M[Fail - attendance]
    J -->|Yes| N[Merit]
    J -->|No| O[Distinction]
```

## Cyclomatic complexity

When the two OR expressions are expanded into separate short-circuit checks:
- Decision checks: 8
- Cyclomatic complexity: V(G) = 8 + 1 = 9

If a simplified graph treats each compound OR expression as one decision,
the count is 6 decisions and V(G) = 7. This document uses the expanded graph.

## Existing automated path tests

src/test/java/com/testforge/service/ResultCalculatorPathCoverageTest.java

The nine representative paths and expected outputs are listed in Path_Coverage.csv.

## Conclusion
Path coverage verifies representative routes through the result calculation logic, including invalid inputs, attendance conditions and grade outcomes.