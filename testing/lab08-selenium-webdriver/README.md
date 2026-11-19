# Lab Sheet 8 - Selenium WebDriver

This lab uses a local Chrome browser controlled by Java Selenium WebDriver.

Executable test:
StudentPortalLocalSeleniumTest.java

The Java test is maintained in the shared Selenium Maven project:
../selenium-java/src/test/java/com/testforge/automation/

Run from the repository root:

.\mvnw.cmd -f ".\testing\selenium-java\pom.xml" "-Dtest=StudentPortalLocalSeleniumTest" "-Dtestforge.url=http://localhost:8080" test