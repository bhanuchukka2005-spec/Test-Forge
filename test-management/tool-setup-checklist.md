# External tool setup checklist

Use this list to record which optional tools you actually configured. A source-code template is not evidence that a remote tool was installed or executed.

| Tool | Required external setup | Evidence to save | Status |
|---|---|---|---|
| TestLink | Hosted TestLink + project + test plan | Test execution export/screenshots | Not configured |
| Bugzilla | Bugzilla REST host + product/component + API key | Created bug ID + retrieval response | Not configured |
| MantisBT | MantisBT REST host + project/category + API token | Issue ID + retrieval response | Not configured |
| Selenium IDE | Browser extension | `.side` execution result | Not run |
| Katalon Recorder | Browser extension | Recording + replay result | Not run |
| Selenium Grid | Grid server + reachable app URL | Grid session + run result | Not configured |
| NUnit | .NET 10 LTS SDK | `dotnet test` output | Not run |
| Appium | Appium server + Android device/emulator + APK | Test log + device details | Blocked: no APK |
| Jenkins | Jenkins server + JDK17/Maven tool definitions | Build number + test report | Not configured |
| GitHub Actions | Push repository to GitHub | Successful workflow run | Not configured |
| JMeter | JMeter installed | `.jtl` + HTML dashboard | Not run |
| Gatling | Gatling Maven project dependencies | Gatling report | Not run |
| JaCoCo | Root Maven build | `target/site/jacoco/index.html` | Not run |
| Clover | Clover installation/license | Clover coverage report | Blocked: license required |
| Coverage.py | Python dependencies | coverage HTML report | Not run |
| SonarQube | SonarQube server + token | project dashboard/quality gate | Not configured |
| PMD | Root Maven build | PMD report | Not run |
| SpotBugs | Root Maven build | SpotBugs report | Not run |
| Browsershots | Publicly reachable deployment URL | Screenshot set | Not configured |

Do not put API keys, tokens or passwords in this file or commit them to Git.
