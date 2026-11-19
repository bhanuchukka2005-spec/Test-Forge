# Selenium Grid experiment (Lab 9)

This experiment must use `RemoteWebDriver`, not `ChromeDriver`, to demonstrate Grid correctly.

1. Start the TestForge application at `http://localhost:8080`.
2. Start a Selenium Grid standalone node/server (requires Docker or a separate Grid host):

```powershell
docker run -d --name testforge-grid -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.52.0
```

3. Run the standalone Java Selenium project after changing the WebDriver factory to the `RemoteWebDriver` example in `RemoteGridLoginTest.java`.
4. Open `http://localhost:4444` to inspect Grid status.

On an 8 GB laptop, do not run Grid, Jenkins and SonarQube simultaneously. Use the local Chrome Selenium suite first.
