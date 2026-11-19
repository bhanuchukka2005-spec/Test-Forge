
from pathlib import Path
import json

def login(username, password):
    if username == "admin" and password == "admin123":
        return "Login Successful"
    return "Login Failed"


test_cases = [
    {
        "id": "TC001",
        "username": "admin",
        "password": "admin123",
        "expected": "Login Successful"
    },
    {
        "id": "TC002",
        "username": "admin",
        "password": "wrongpass",
        "expected": "Login Successful"
    }
]

execution_results = []
defects = []

print("========== TEST CASE EXECUTION ==========\n")

for test in test_cases:
    actual = login(test["username"], test["password"])
    passed = actual == test["expected"]

    result = {
        "test_case_id": test["id"],
        "expected": test["expected"],
        "actual": actual,
        "status": "PASSED" if passed else "FAILED"
    }

    execution_results.append(result)

    print(f"Test Case ID: {test['id']}")
    print(f"Expected: {test['expected']}")
    print(f"Actual:   {actual}")
    print(f"Result:   {result['status']}")

    if not passed:
        defects.append({
            "defect_id": "DEF-001",
            "title": "Expected and actual login results do not match",
            "test_case_id": test["id"],
            "steps": "Enter admin and wrongpass, then click Login",
            "expected": test["expected"],
            "actual": actual,
            "priority": "High",
            "status": "New"
        })
        print("Defect logged: DEF-001")

    print()

report_dir = Path(__file__).parent / "reports"
report_dir.mkdir(exist_ok=True)

summary = {
    "total_test_cases": len(test_cases),
    "passed": sum(r["status"] == "PASSED" for r in execution_results),
    "failed": sum(r["status"] == "FAILED" for r in execution_results),
    "defects_logged": len(defects),
    "execution_results": execution_results
}

(report_dir / "execution_report.json").write_text(
    json.dumps(summary, indent=4),
    encoding="utf-8"
)

(report_dir / "defect_report.json").write_text(
    json.dumps(defects, indent=4),
    encoding="utf-8"
)

text_report = "\n".join(
    f"Defect ID: {d['defect_id']}\n"
    f"Title: {d['title']}\n"
    f"Test Case: {d['test_case_id']}\n"
    f"Expected: {d['expected']}\n"
    f"Actual: {d['actual']}\n"
    f"Priority: {d['priority']}\n"
    f"Status: {d['status']}\n"
    for d in defects
)

(report_dir / "defect_report.txt").write_text(
    text_report,
    encoding="utf-8"
)

print("========== EXECUTION SUMMARY ==========")
print(f"Total Test Cases : {summary['total_test_cases']}")
print(f"Passed           : {summary['passed']}")
print(f"Failed           : {summary['failed']}")
print(f"Defects Logged   : {summary['defects_logged']}")
print("=======================================")
print(f"\nReports saved in: {report_dir}")
