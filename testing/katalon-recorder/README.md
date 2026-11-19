# Katalon Recorder (Lab 7 alternate recorder)

Use the Chrome extension on `http://localhost:8080` and record these steps:

1. Open `/`.
2. Type `student` into `id=username`.
3. Type `Student@123` into `id=password`.
4. Click `css=button[type='submit']`.
5. Verify `id=dashboard` is visible.
6. Verify `id=studentName` contains `Aarav Sharma`.

The Selenium IDE `.side` project in `testing/selenium-ide/` is the importable recording-oriented artifact. Katalon Recorder is an alternate browser recorder, not a separate backend test framework.
