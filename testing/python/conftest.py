import os
import pytest

@pytest.fixture(scope="session")
def base_url():
    return os.getenv("TESTFORGE_URL", "http://localhost:8080").rstrip("/")
