@echo publish to maven of bintray
cd /d .
gradlew build publishToMavenLocal bintrayUpload --warning-mode all
pause
