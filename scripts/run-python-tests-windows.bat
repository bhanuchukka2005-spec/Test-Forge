@echo off
setlocal
cd /d "%~dp0.."
py -3 -m venv .venv
call .venv\Scripts\activate.bat
python -m pip install -r testing\python\requirements.txt
python -m pytest testing\python\test_result_logic.py -q
