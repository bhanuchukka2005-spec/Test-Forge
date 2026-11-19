import requests


def test_health_endpoint(base_url):
    response = requests.get(f"{base_url}/api/health", timeout=10)
    assert response.status_code == 200
    assert response.json()["status"] == "UP"


def test_student_login_and_profile(base_url):
    session = requests.Session()
    login = session.post(f"{base_url}/api/auth/login", json={
        "username": "student", "password": "Student@123"
    }, timeout=10)
    assert login.status_code == 200, login.text
    profile = session.get(f"{base_url}/api/students/me", timeout=10)
    assert profile.status_code == 200, profile.text
    assert profile.json()["rollNumber"] == "STU001"


def test_invalid_login_is_rejected(base_url):
    session = requests.Session()
    response = session.post(f"{base_url}/api/auth/login", json={
        "username": "unknown_student", "password": "incorrect-password"
    }, timeout=10)
    assert response.status_code == 401


def test_student_cannot_list_all_students(base_url):
    session = requests.Session()
    login = session.post(f"{base_url}/api/auth/login", json={
        "username": "student", "password": "Student@123"
    }, timeout=10)
    assert login.status_code == 200, login.text
    response = session.get(f"{base_url}/api/students", timeout=10)
    assert response.status_code == 403
