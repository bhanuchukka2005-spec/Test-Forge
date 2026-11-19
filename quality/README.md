# Code quality and coverage map

- **JaCoCo:** configured in the root Maven build; `mvn test` creates `target/site/jacoco/index.html`.
- **Clover:** optional profile; requires a license. See `clover/README.md`.
- **Coverage.py:** run `python -m coverage run -m pytest testing/python/test_result_logic.py` then `python -m coverage report`.
- **SonarQube:** project properties are provided in `sonar-project.properties`; SonarQube server/token must be configured separately.
- **PMD:** `mvn pmd:pmd` / `mvn pmd:check`.
- **SpotBugs:** `mvn spotbugs:check`.
- Static analysis reports are evidence to review, not automatic proof that software is defect-free.
