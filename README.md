# TestForge — End-to-End Software Quality Engineering Project

**A Java Spring Boot campus portal used as the Application Under Test (AUT), plus the lab's test-management, automation, defect, performance, coverage, quality and CI experiments.**

> Educational starter project. It is not production-ready. Demo credentials are public, CSRF is disabled for the lab's JSON/session exercises, and no real student data should be entered.

## 1. What is included

- Working Spring Boot REST application with a responsive browser UI.
- Student and admin demo roles; BCrypt password hashing; session-based login; failed-login lockout after three attempts.
- Student profile, result calculation, attendance list and service-request workflows. The sample result rule uses a 75% attendance threshold as a project assumption; the lab manual itself treats attendance as an attended/not-attended condition, so change this constant to match your faculty requirements.
- JUnit 5 tests for result rules, boundaries and independent paths.
- Separate TestNG experiment profile.
- Selenium WebDriver tests and Selenium IDE `.side` project; Katalon Recorder runbook.
- Selenium Grid RemoteWebDriver example.
- Pytest API and result-rule tests; Coverage.py config.
- NUnit API test project (separate .NET 10 LTS test project).
- Appium starter (requires an Android application/APK before it can run; Python client 6.0.6).
- Bugzilla and MantisBT API create/read connector functions (requires your own server, project/product and API token).
- JMeter `.jmx` plan and Gatling sample.
- JaCoCo, PMD, SpotBugs and SonarQube configuration; optional Clover profile.
- GitHub Actions workflow and Jenkins pipeline.
- Test plan, case catalogue, manual execution sheet and test-summary template.

## 2. Prerequisites

- JDK 17 or newer. Spring Boot 4.1.2 requires Java 17+.
- Internet access for the first build to download Maven dependencies. The included `mvnw.cmd` / `mvnw` bootstrap downloads Maven 3.10.1 into your user profile (no administrator permission needed).
- Python 3.10+ for Pytest experiments.
- Chrome for local Selenium tests; Selenium Manager downloads the matching driver automatically when network access allows.
- Optional tools are listed in the tool matrix below.

Check Java and Maven:

```powershell
java -version
.\mvnw.cmd -version
```

## 3. Run the application on Windows

1. Extract the ZIP to a normal writable folder, for example `C:/Users/YOUR_USER/Desktop/TestForge`.
2. Open PowerShell in the `TestForge` folder.
3. Run:

```powershell
.\mvnw.cmd spring-boot:run
```

Or double-click `run.bat`. The first run downloads Maven into your user profile and then downloads project dependencies.
4. Open `http://localhost:8080`.

### Demo accounts

| Role | Username | Password |
|---|---|---|
| Student | `student` | `Student@123` |
| Admin | `admin` | `Admin@123` |

These credentials are for local demo use only. Change them before any shared deployment. The H2 database is created in `./data/`. To reset all demo data and clear the three-attempt account lockout, stop the app and delete the `data` folder; the next start recreates the seed records.

### Useful endpoints

| Method | Endpoint | Access |
|---|---|---|
| GET | `/api/health` | Public health check |
| POST | `/api/auth/login` | Public; creates a session |
| GET | `/api/auth/me` | Signed-in user |
| POST | `/api/auth/logout` | Signed-in session |
| GET | `/api/students/me` | Student's own record |
| GET | `/api/students` | Admin only |
| POST | `/api/students` | Admin only |
| GET | `/api/attendance/me` | Student's own attendance |
| POST | `/api/attendance` | Admin only |
| GET | `/api/results/{rollNumber}` | Admin or matching student |
| GET/POST | `/api/requests` | Signed-in user |
| PATCH | `/api/requests/{id}/status` | Admin only |

For API testing, login first and preserve the `JSESSIONID` cookie. Example with curl:

```bash
curl http://localhost:8080/api/health
curl.exe -i -c cookies.txt -H "Content-Type: application/json" -d '{"username":"student","password":"Student@123"}' http://localhost:8080/api/auth/login
curl -b cookies.txt http://localhost:8080/api/students/me
```

## 4. Run the Java unit tests

In the root folder:

```powershell
.\mvnw.cmd clean test
```

Build and verify:

```powershell
.\mvnw.cmd clean verify
```

JaCoCo HTML report: `target/site/jacoco/index.html`.

Run the separate TestNG examples:

```powershell
.\mvnw.cmd -Ptestng test
```

## 5. Run Pytest and Coverage.py

Start the application in one terminal, then in a second terminal:

```powershell
py -3 -m venv .venv
.venv\Scripts\Activate.ps1
python -m pip install -r testing/python/requirements.txt
python -m pytest testing/python/test_result_logic.py -q
python -m pytest testing/python -q
```

The API tests expect the app at `http://localhost:8080`. To change it:

```powershell
$env:TESTFORGE_URL = "http://localhost:8080"
```

Coverage.py (for the standalone Python test logic):

```powershell
python -m coverage run -m pytest testing/python/test_result_logic.py
python -m coverage report
python -m coverage html
```

## 6. Selenium WebDriver and IDE

Start the application first. Then:

```powershell
cd testing/selenium-java
.\mvnw.cmd test
```

To run headless:

```powershell
.\mvnw.cmd test -Dheadless=true
```

To use a different app address:

```powershell
.\mvnw.cmd test -Dtestforge.url=http://localhost:8080
```

Selenium Manager handles local driver discovery in current Selenium versions. Import `testing/selenium-ide/TestForge-Login.side` into Selenium IDE. Katalon Recorder steps are in `testing/katalon-recorder/README.md`.

## 7. Selenium Grid

Grid is an optional experiment. Start a Grid host and run the `RemoteGridLoginTest.java` reference with a RemoteWebDriver session. On Docker Desktop for Windows, the app URL as seen from a browser container is usually `http://host.docker.internal:8080`, not `localhost`. Docker is not needed for the main app.

## 8. JMeter and Gatling

Open `performance/jmeter/TestForge-Health-Load.jmx` in JMeter. Start with the low preset and export the actual `.jtl` and HTML dashboard into `reports/generated/`.

For Gatling:

```powershell
.\mvnw.cmd -f performance/gatling/pom.xml gatling:test
```

Only load-test systems you own or have permission to test. Do not use high concurrency on the college network or public endpoints.

## 9. NUnit

Requires .NET 10 SDK (LTS):

```powershell
cd testing/nunit
dotnet test
```

Set `TESTFORGE_URL` if the application is not on localhost.

## 10. Bugzilla / MantisBT

The client helper is at `testing/defect-tracking/bugzilla_mantis_client.py`. It includes create and retrieve functions for both systems, and it does not invent a server URL or key. Configure your own values as environment variables; never commit tokens:

```powershell
$env:BUGZILLA_REST_URL = "https://your-bugzilla-host/rest"
$env:BUGZILLA_API_KEY = "YOUR_KEY"
$env:MANTIS_REST_URL = "https://your-mantis-host/api/rest"
$env:MANTIS_API_TOKEN = "YOUR_TOKEN"
```

Bugzilla needs an existing product/component/version. MantisBT needs a project/category that exists. Confirm the API path/version against the server you are using before creating real issues.

## 11. Other lab tools mapping

| Lab tool | Project artifact / how to use |
|---|---|
| TestLink | `test-management/testlink/README.md`; host separately and enter cases from CSV |
| Bugzilla | `testing/defect-tracking/bugzilla_mantis_client.py`; API server and key required |
| MantisBT | Same helper; API server and token required |
| JUnit | Root Java tests; `.\mvnw.cmd test` |
| TestNG | Root Maven profile; `.\mvnw.cmd -Ptestng test` |
| Pytest | `testing/python`; `python -m pytest` |
| NUnit | `testing/nunit`; requires .NET 10 LTS SDK; `dotnet test` |
| Selenium IDE | Import `.side` project |
| Selenium WebDriver | `testing/selenium-java`; Maven test |
| Katalon Recorder | Record steps in its Chrome extension using README locators |
| Appium | Starter in `testing/appium`; requires a separately built Android app/APK and Appium server |
| Jenkins | `Jenkinsfile`; configure JDK17 and Maven3 tools in Jenkins |
| GitHub Actions | `.github/workflows/ci.yml`; runs Java unit tests and Python deterministic tests |
| JMeter | `performance/jmeter/TestForge-Health-Load.jmx` |
| Gatling | `performance/gatling` Maven project |
| JaCoCo | Root Maven build; HTML report in `target/site/jacoco` |
| Clover | Optional Maven profile; commercial license required |
| Coverage.py | `testing/python/.coveragerc` |
| SonarQube | `quality/sonar-project.properties`; server/token required |
| PMD | `.\mvnw.cmd pmd:pmd` |
| SpotBugs | `.\mvnw.cmd spotbugs:spotbugs` |
| Selenium Grid | RemoteWebDriver reference under `testing/selenium-grid` |
| Browsershots | Manually submit an accessible deployment URL; do not upload internal/private student data |

## 12. Lab manual corrections applied

- The Lab 9 example in the manual instantiates a local `ChromeDriver`; a true Grid experiment must use a remote Grid endpoint and `RemoteWebDriver`.
- The Lab 10 description says TestNG, but its sample code uses JUnit imports. This repository includes a separate TestNG profile/test.
- Lab 12's intentional failing expected-result example is treated as a demonstration, not a valid expected result for a well-designed test case.
- JMeter example values in the manual are sample output only; record actual measurements from your run.

## 13. What is not pre-connected

The ZIP cannot contain your college's Bugzilla/Mantis credentials, hosted TestLink instance, Jenkins server, SonarQube server, Clover license, Android APK/device, or a remote browser farm. Their setup/configuration notes and starter artifacts are included. Appium is not runnable until a mobile client exists. TestLink and Browsershots require separate hosted services. The project does not claim these experiments have already passed.

## 14. Suggested order for your lab/viva

1. Run the web application and explain the architecture.
2. Demonstrate manual cases, BVA/ECP, decision table, state transition and path coverage.
3. Run JUnit and TestNG; explain assertions and coverage.
4. Run Selenium IDE, Selenium WebDriver and, if infrastructure permits, Grid.
5. Demonstrate Pytest/NUnit API tests.
6. Record a defect from a deliberately reproduced mismatch in Bugzilla or MantisBT.
7. Run JMeter/Gatling with a small, controlled workload.
8. Inspect static analysis and coverage output.
9. Show CI and complete the test summary with actual metrics.

## References

- Spring Boot system requirements: https://docs.spring.io/spring-boot/system-requirements.html
- Selenium downloads and Selenium Manager: https://www.selenium.dev/downloads/
- Apache JMeter: https://jmeter.apache.org/
