@echo build release apk
cd /d .
@REM gradlew clean assembleRelease --warning-mode all
gradlew clean resguardRelease --warning-mode all
pause
