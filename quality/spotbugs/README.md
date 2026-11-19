# SpotBugs

Run from the repository root:

```bash
mvn spotbugs:check
```

The Maven plugin is configured not to fail the build on findings initially. Review findings, confirm whether they are real defects, then tighten the gate when the project is stable.
