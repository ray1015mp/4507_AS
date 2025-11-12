@echo off
echo Compiling MEMS System...
javac *.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo.
echo Running MEMS System...
echo.
java Main
pause