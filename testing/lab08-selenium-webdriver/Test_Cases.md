# Lab Sheet 8 - Selenium WebDriver Automation

## Project
TestForge Student Portal

## Objective
Develop automated browser test cases using Selenium WebDriver and Java.

## Tools
- Java 17
- Selenium WebDriver 4.52.0
- JUnit 5
- Maven
- Google Chrome

## Automation approach
ChromeDriver launches Chrome locally on the host machine.
The test interacts with TestForge through Selenium WebDriver.

## Test cases

### TC001 - Valid Student Login
1. Open TestForge.
2. Enter username: student.
3. Enter the configured demo password.
4. Click Login.
5. Wait for the dashboard.
6. Verify that the student name is Aarav.

Expected: Student dashboard is displayed.

### TC002 - Invalid Student Login
1. Open TestForge.
2. Enter an unknown username.
3. Enter an incorrect password.
4. Click Login.
5. Verify the error message.

Expected: Invalid username or password is displayed.

## Java source
testing/selenium-java/src/test/java/com/testforge/automation/StudentPortalLocalSeleniumTest.java

## Execution
From the repository root:

.\mvnw.cmd -f ".\testing\selenium-java\pom.xml" "-Dtest=StudentPortalLocalSeleniumTest" "-Dtestforge.url=http://localhost:8080" test

## Difference from Lab 9
Lab 8 uses ChromeDriver to control a local browser.
Lab 9 uses RemoteWebDriver to control a browser running in Selenium Grid.

## Conclusion
Selenium WebDriver automates browser interactions and validates the TestForge login functionality.