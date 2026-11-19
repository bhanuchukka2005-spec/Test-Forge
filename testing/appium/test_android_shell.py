"""Starter Appium test. Requires Appium server + Android emulator/device + a mobile app build.
This project currently provides a responsive web app, not an Android APK. This file is a
place-holder experiment for a future Android WebView/native client of the same portal."""
import os
from appium import webdriver
from appium.options.android import UiAutomator2Options


def create_driver():
    options = UiAutomator2Options()
    options.platform_name = "Android"
    options.automation_name = "UiAutomator2"
    options.device_name = os.getenv("ANDROID_DEVICE_NAME", "Android Emulator")
    options.app = os.getenv("ANDROID_APP_PATH", "")
    if not options.app:
        raise RuntimeError("Set ANDROID_APP_PATH to an Android APK before running Appium.")
    return webdriver.Remote(os.getenv("APPIUM_URL", "http://127.0.0.1:4723"), options=options)
