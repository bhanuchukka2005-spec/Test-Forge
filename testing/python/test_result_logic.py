# Independent Python mirror used only for the Pytest experiment.
def calculate_result(marks, attendance):
    if marks < 0 or marks > 100 or attendance < 0 or attendance > 100:
        return "Invalid"
    if marks < 40:
        return "Fail"
    if marks < 60:
        return "Pass" if attendance >= 75 else "Fail (attendance)"
    if marks < 80:
        return "Merit"
    return "Distinction"


def test_bva_minimum_marks():
    assert calculate_result(0, 75) == "Fail"
    assert calculate_result(1, 75) == "Fail"


def test_grade_boundaries():
    assert calculate_result(39, 75) == "Fail"
    assert calculate_result(40, 75) == "Pass"
    assert calculate_result(59, 75) == "Pass"
    assert calculate_result(60, 10) == "Merit"
    assert calculate_result(79, 10) == "Merit"
    assert calculate_result(80, 10) == "Distinction"
