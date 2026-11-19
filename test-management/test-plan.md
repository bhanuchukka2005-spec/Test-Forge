# Test Plan — TestForge Student Portal

## 1. Objective
Verify authentication, student profile visibility, academic result calculation, attendance records and service-request workflows. Exercise functional and non-functional testing techniques from the Software Testing Lab Manual.

## 2. Scope
**In scope:** login/logout, role checks, student profile, marks/result rules, attendance listing, service request create/list/status transition, validation, API response behaviour, browser workflows and performance of the health endpoint.

**Out of scope:** production identity management, real email/SMS, payment, real student records, native Android application, production-grade recovery and external institution integrations.

## 3. Test design assumptions
The sample result calculator uses 75% as the minimum attendance threshold. This is a project assumption selected to make BVA and state-based tests concrete; the lab manual describes the attendance branch as a boolean condition and does not specify a numeric threshold. Confirm or change it to match the faculty-provided rule before submission.

## 4. Test environment
- Java 17+, Maven 3.6.3+
- Spring Boot application at `http://localhost:8080`
- H2 file database for local development
- Chrome for Selenium local automation
- Python 3.10+ for Pytest helper suite
- Optional: Selenium Grid, Appium, Jenkins, SonarQube, Bugzilla and MantisBT

## 5. Entry criteria
Application starts; database is reachable; demo records are present; base URL is available; test data is reset or known.

## 6. Exit criteria
All critical test cases executed; no open critical/high defects; unit tests pass; automation and performance findings are attached; coverage and static-analysis reports reviewed. Release readiness is a decision based on evidence, not merely a green build.

## 7. Risks and mitigations
- External bug trackers may require an administrator-provisioned project and API token: use mock/manual defect records until configured.
- Grid/Appium need additional infrastructure: keep them as optional, separately executed experiments.
- Demo credentials are intentionally public: never reuse these accounts in a real environment.
- Performance tests can overload a service: only load-test the local app or systems you own, start with a small thread count, and increase gradually.

## 8. Deliverables
Test case catalogue, execution log, JUnit/Pytest/TestNG/NUnit results, Selenium evidence, defect records, JMeter/Gatling output, coverage/static-analysis reports, CI result and test summary report.
