call runcrud.bat
if "%ERRORLEVEL%"=="0" goto startbrowser
echo.
echo Cannot start script RUNCRUD
goto fail

:startbrowser
start opera http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished
