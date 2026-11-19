# TestForge - Software Testing Lab Portfolio

## Project Overview

TestForge is a Student Portal used as the application under test for the Software Testing Laboratory.

The repository combines manual testing, test design techniques, Java unit testing, browser automation, Selenium Grid, defect tracking and performance testing.

Application URL: http://localhost:8080

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application and test implementation |
| Spring Boot | Student Portal backend |
| Maven | Build and test execution |
| JUnit 5 | Unit testing |
| TestNG | Alternative testing framework |
| Selenium IDE | Record-and-playback automation |
| Selenium WebDriver | Browser automation |
| Selenium Grid | Remote browser execution |
| Docker | Selenium Grid environment |
| JaCoCo | Code coverage reporting |
| Python | Defect tracking demonstration |
| Apache JMeter | Performance testing |
| Git and GitHub | Version control and project submission |

## Lab Sheet Index and Execution Status

| Lab | Experiment | Project implementation | Execution status |
|---|---|---|---|
| 1 | Introduction to Software Testing | SDLC, STLC and TestForge test cases | Documentation created |
| 2 | Manual Testing | Functional and non-functional test cases | Documentation created; reverify cases |
| 3 | BVA and ECP | ResultCalculator boundary and equivalence tests | 32-test combined run passed |
| 4 | Decision Table and State Transition | Login decision table and account lockout tests | Included in 32-test combined run |
| 5 | Path Coverage | ResultCalculator control flow and path tests | Included in 32-test combined run |
| 6 | JUnit Testing | JUnit 5 assertions and test lifecycle | 22 tests passed |
| 7 | Selenium IDE | test.side | Project saved; final playback needs verification |
| 8 | Selenium WebDriver | Local ChromeDriver automation | 2 tests passed |
| 9 | Selenium Grid | RemoteWebDriver with Docker Chrome | 2 tests passed in previous run |
| 10 | Selenium with JUnit/TestNG | Maven Selenium and JUnit integration; TestNG profile | 2 remote Selenium tests passed; TestNG profile configured |
| 11 | Bug Reporting and Tracking | Bugzilla/Mantis exercise | Not completed |
| 12 | Test Execution and Defect Tracking | Python execution and defect report simulation | 1 pass and 1 intentional failure |
| 13 | Performance Testing | Apache JMeter | Previous 20-request run reported successful; rerun against current port before presentation |
| 14 | Test Plan and Summary Report | Test plan, test suite and summary | Documentation created |

## Important Source Files

### Application
- src/main/java/com/testforge/service/ResultCalculator.java
- src/main/java/com/testforge/api/AuthController.java

### Unit Testing
- src/test/java/com/testforge/service/ResultCalculatorTest.java
- src/test/java/com/testforge/service/ResultCalculatorBvaEcpTest.java
- src/test/java/com/testforge/service/ResultCalculatorPathCoverageTest.java
- src/test/java/com/testforge/service/ResultCalculatorJUnitLabTest.java
- src/test/java/com/testforge/domain/AccountLockoutStateTransitionTest.java
- src/test/java/com/testforge/service/ResultCalculatorTestNG.java

### Selenium
- testing/lab07-selenium-ide/test.side
- testing/selenium-java/src/test/java/com/testforge/automation/StudentPortalLocalSeleniumTest.java
- testing/selenium-java/src/test/java/com/testforge/automation/StudentPortalSeleniumTest.java
- testing/selenium-grid/RemoteGridLoginTest.java

## Execution Commands

Run commands from the repository root.

### Labs 3, 4 and 5 - Unit Tests

```powershell
.\mvnw.cmd "-Dtest=ResultCalculatorBvaEcpTest,ResultCalculatorTest,ResultCalculatorPathCoverageTest,AccountLockoutStateTransitionTest" test
```

### Lab 6 - JUnit

```powershell
.\mvnw.cmd "-Dtest=ResultCalculatorJUnitLabTest,ResultCalculatorTest" test
```

### Lab 6 - TestNG profile

```powershell
.\mvnw.cmd -Ptestng test
```

### Lab 8 - Local Selenium WebDriver

```powershell
.\mvnw.cmd -f ".\testing\selenium-java\pom.xml" "-Dtest=StudentPortalLocalSeleniumTest" "-Dtestforge.url=http://localhost:8080" test
```

### Labs 9 and 10 - Selenium Grid

```powershell
.\mvnw.cmd -f ".\testing\selenium-java\pom.xml" test
```

The Selenium Grid tests use RemoteWebDriver and the configured host.docker.internal:8080 application URL.

### Lab 12 - Defect Tracking Simulation

```powershell
python .\testing\lab12\lab12_defect_tracking.py
```

### Lab 13 - JMeter

Open the saved JMeter `.jmx` test plan in Apache JMeter, verify its HTTP target is `localhost:8080`, and run the test plan.

## Important Reports

### JaCoCo
The root Maven build generates coverage reports under:

target/site/jacoco/index.html

### Lab 12
Reports are stored under:

testing/lab12/reports/

### Lab 14
Documentation is stored under:

testing/lab14/

- Test_Plan.md
- Test_Suite.csv
- Test_Summary_Report.md

## Notes for Reproducibility

1. Start the TestForge application before executing browser tests.
2. Verify the demo account credentials on the presentation machine.
3. Start the Docker Selenium Grid container before executing remote tests.
4. Run Selenium IDE playback separately.
5. Rerun JMeter with the correct application URL before quoting performance results.
6. Do not describe Lab 11 as completed until a real bug-tracking exercise has been performed.
7. Do not treat the intentional failure in Lab 12 as a defect discovered in the live TestForge application.