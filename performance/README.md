# Performance testing

## JMeter (Lab 13)
1. Start TestForge (`run.bat` or `./run.sh`).
2. Open `performance/jmeter/TestForge-Health-Load.jmx` in Apache JMeter.
3. Start with the included 10 virtual users, 10-second ramp-up and 2 loops (20 requests total).
4. Save `.jtl` output under `reports/generated/` and use JMeter's HTML dashboard for analysis.
5. Do not claim performance pass/fail using invented threshold values. Establish an SLA, run the test, then compare measured percentiles, error rate and throughput.

CLI example:

```bash
jmeter -n -t performance/jmeter/TestForge-Health-Load.jmx -l reports/generated/jmeter.jtl -e -o reports/generated/jmeter-html
```

## Gatling
Run `mvn -f performance/gatling/pom.xml gatling:test`. Inspect the generated Gatling report. Keep load tests low on an 8 GB machine.
