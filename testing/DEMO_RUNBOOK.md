# TestForge - Faculty Demonstration Guide

## 1. Start the Application

Open PowerShell in the repository root.

If the application is not already running:

```powershell
.\mvnw.cmd spring-boot:run
```

Open:

http://localhost:8080

## 2. Demonstrate the Main Application

Show:
- Student login
- Valid login and dashboard
- Invalid login
- Student profile
- Attendance and results
- Service request workflow
- Admin functionality

Use the configured demo account only.

## 3. Demonstrate Labs 1 and 2

Open:
- testing/lab01-introduction/SDLC_STLC.md
- testing/lab01-introduction/TestForge_Test_Cases.csv
- testing/lab02-manual-testing/Functional_Test_Cases.csv
- testing/lab02-manual-testing/NonFunctional_Test_Cases.csv

Explain the difference between functional and non-functional testing.

## 4. Demonstrate Labs 3, 4 and 5

Show:
- BVA and ECP test cases
- Login decision table
- Account lockout state transitions
- ResultCalculator control flow graph
- Path coverage test methods

Run the combined Maven command listed in testing/README.md.

## 5. Demonstrate Lab 6

Run the JUnit tests.

Explain:
- @Test
- @BeforeEach
- @DisplayName
- assertEquals
- assertAll
- assertDoesNotThrow

Show the generated JaCoCo report.

## 6. Demonstrate Lab 7

Open Selenium IDE.

Load:

testing/lab07-selenium-ide/test.side

Run the saved test and show its execution result.

## 7. Demonstrate Lab 8

Run StudentPortalLocalSeleniumTest.

Explain that ChromeDriver launches and controls Chrome locally.

Show valid and invalid login scenarios.

## 8. Demonstrate Labs 9 and 10

Open Docker Desktop and show the testforge-grid container running.

Open:
- http://localhost:4444/ui
- http://localhost:7900

Run the Selenium Maven tests.

Explain:

"TestForge runs on my Windows host, while Selenium controls Chrome remotely inside the Docker Selenium Grid container."

## 9. Labs 11 to 14

Lab 11: Explain that the Bugzilla/Mantis integration exercise remains pending. Do not claim a live API integration.

Lab 12: Run the Python defect-tracking simulation and show its JSON and text reports. Explain that one failure is intentional test data.

Lab 13: Open the JMeter plan, verify the target URL, execute it, and show the Summary Report.

Lab 14: Present Test_Plan.md, Test_Suite.csv and Test_Summary_Report.md.

## 10. Final Explanation

Explain how the same TestForge application is used to demonstrate different software testing techniques.

Emphasize the distinction between:
- Manual testing
- Test design techniques
- Unit testing
- Local browser automation
- Remote browser automation
- Defect tracking
- Performance testing

Be clear about which experiments were executed successfully and which remain pending.