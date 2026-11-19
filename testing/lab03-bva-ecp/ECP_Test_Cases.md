# Equivalence Class Partitioning - TestForge

## Marks partitions

| Class | Input range | Example | Expected result |
|---|---|---:|---|
| Invalid low | Below 0 | -1 | Invalid |
| Valid fail | 0-39 | 20 | Fail |
| Attendance-dependent pass/fail | 40-59 | 50 | Depends on attendance |
| Valid merit | 60-79 | 70 | Merit |
| Valid distinction | 80-100 | 90 | Distinction |
| Invalid high | Above 100 | 101 | Invalid |

For the 40-59 class, attendance is itself partitioned:
- Below 75%: Fail (attendance)
- 75%-100%: Pass

## Attendance partitions

| Class | Attendance range | Example with 50 marks | Expected result |
|---|---|---:|---|
| Invalid low | Below 0 | -1 | Invalid |
| Valid but insufficient | 0 to below 75 | 50 | Fail (attendance) |
| Valid and sufficient | 75-100 | 80 | Pass |
| Invalid high | Above 100 | 101 | Invalid |

## Automation
The equivalence classes are tested by:
ResultCalculatorBvaEcpTest.java

## Conclusion
Equivalence partitioning reduces the number of test inputs by selecting representatives from distinct input classes.