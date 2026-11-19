"""Optional Lab 11 connector. No URL, tokens, or credentials are bundled.
Set BUGZILLA_REST_URL + BUGZILLA_API_KEY or MANTIS_REST_URL + MANTIS_API_TOKEN.
"""
import os
import requests


def create_bugzilla_bug(summary: str, description: str, product: str, component: str):
    base = os.environ["BUGZILLA_REST_URL"].rstrip("/")
    key = os.environ["BUGZILLA_API_KEY"]
    payload = {"product": product, "component": component, "summary": summary,
               "version": "unspecified", "description": description,
               "op_sys": "All", "platform": "All", "api_key": key}
    response = requests.post(f"{base}/bug", json=payload, timeout=20)
    response.raise_for_status()
    return response.json()


def get_bugzilla_bug(bug_id: int):
    base = os.environ["BUGZILLA_REST_URL"].rstrip("/")
    key = os.environ["BUGZILLA_API_KEY"]
    response = requests.get(f"{base}/bug/{bug_id}", params={"api_key": key}, timeout=20)
    response.raise_for_status()
    return response.json()


def create_mantis_issue(summary: str, description: str, project_name: str, category_name: str = "General"):
    base = os.environ["MANTIS_REST_URL"].rstrip("/")
    token = os.environ["MANTIS_API_TOKEN"]
    headers = {"Authorization": token, "Content-Type": "application/json"}
    payload = {"summary": summary, "description": description,
               "project": {"name": project_name}, "category": {"name": category_name}}
    response = requests.post(f"{base}/issues/", headers=headers, json=payload, timeout=20)
    response.raise_for_status()
    return response.json()


def get_mantis_issue(issue_id: int):
    base = os.environ["MANTIS_REST_URL"].rstrip("/")
    token = os.environ["MANTIS_API_TOKEN"]
    response = requests.get(f"{base}/issues/{issue_id}", headers={"Authorization": token}, timeout=20)
    response.raise_for_status()
    return response.json()


if __name__ == "__main__":
    print("Configure the relevant environment variables before calling a connector.")
